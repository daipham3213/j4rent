package io.tomcode.j4rent.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tomcode.j4rent.configuration.SecurityConfig;
import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.core.repositories.AccountRepository;
import io.tomcode.j4rent.core.services.*;
import io.tomcode.j4rent.exception.EmailExistsException;
import io.tomcode.j4rent.exception.InvalidOTPException;
import io.tomcode.j4rent.exception.PhoneNumberExistsException;
import io.tomcode.j4rent.exception.UsernameExistsException;
import io.tomcode.j4rent.mapper.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service("accountService")
public class AccountService implements IAccountService, UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final IDocumentService documentService;
    private final IOTPService otpService;
    private final IEmailService emailService;
    private final IJwtService jwtService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final IRoleService roleService;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, IDocumentService documentService, IOTPService otpService, IEmailService emailService, IJwtService jwtService, ModelMapper modelMapper, ObjectMapper objectMapper, IRoleService roleService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.documentService = documentService;
        this.otpService = otpService;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.roleService = roleService;
    }

    @Override
    public Iterable<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public OTP register(Register account) {
        Document document = documentService.createDocument("account", account);
        OTP otp = otpService.createOTP(document.getId());
        emailService.sendEmail(account.getEmail(), "This is the otp for authentication", " OTP : " + otp.getOtp());
        return otp;
    }

    @Override
    public void verify(int otp) throws InvalidOTPException {
        OTP verifyOTP = otpService.getOTP(otp);
        if (verifyOTP == null) {
            throw new InvalidOTPException();
        }
    }

    @Override
    public UserInfo createAccount(CreateAccount account) throws PhoneNumberExistsException, UsernameExistsException, EmailExistsException, InvalidOTPException {
        OTP otp = otpService.getOTP(account.getOtp());
        Document document = documentService.getDocument(otp.getDocumentId());
        Register register = objectMapper.convertValue(document.getData(), Register.class);
        Account info = modelMapper.map(account, Account.class);
        info.setUsername(register.getUsername());
        info.setPhoneNumber(register.getPhoneNumber());
        info.setEmail(register.getEmail());
        checkAccountExists(register);
        populateAccount(info);
        return  new UserInfo(info.getUsername(), info.getFirstName(),info.getLastName(),info.getDob(),info.getIdCard(),info.getGender(),info.getPhoneNumber(),info.getEmail());



    }

    @Override
    public Boolean checkAccountExists(Register register) throws UsernameExistsException, PhoneNumberExistsException, EmailExistsException {
        if (accountRepository.findAccountByUsername(register.getUsername()) != null) {
            throw new UsernameExistsException();
        } else {
            if (accountRepository.findAccountByEmail(register.getEmail()) != null) {
                throw new PhoneNumberExistsException();
            } else if (accountRepository.findAccountByPhoneNumber(register.getPhoneNumber()) != null)
                throw new EmailExistsException();
        }
        return false;
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.getById(id);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsernameEquals(username);
    }

    @Override
    public JwtResponse authenticate(Login login) {
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(login.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        if (passwordEncoder.matches(login.getPassword(), userDetails.getPassword())) {
            Account account = getAccountByUsername(userDetails.getUsername());
            Map<String, String> claims = new HashMap<>();
            claims.put("username", userDetails.getUsername());

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            claims.put(SecurityConfig.AUTHORITIES_CLAIM_NAME, authorities);
            claims.put("userId", account.getId().toString());

            String jwt = jwtService.createJwtForClaims(userDetails.getUsername(), claims);
            return new JwtResponse(jwt);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }

    private Account populateAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setVerify(true);
        account.setAdmin(false);
        if(roleService.getRoleByName("USER")==null)
            roleService.createRole("USER");
        account.setRole(roleService.getRoleByName("USER"));
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Role roles = account.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(roles.getName()));

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), grantedAuthorities);
    }


}
