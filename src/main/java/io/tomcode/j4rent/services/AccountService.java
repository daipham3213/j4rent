package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.core.repositories.AccountRepository;
import io.tomcode.j4rent.core.repositories.RoleRepository;
import io.tomcode.j4rent.core.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Service("accountService")
public class AccountService implements IAccountService , UserDetailsService {
//    public class AccountService implements IAccountService  {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Iterable<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        Account newAccount = popularAccount(account);
        return accountRepository.save(newAccount);
    }

    //Hash Pass_Word
    private Account  popularAccount(Account account){
        Account popular = new Account();
        popular.setUsername(account.getUsername());
        popular.setPassword(passwordEncoder.encode(account.getPassword()));
        popular.setDob(account.getDob());
        popular.setEmail(account.getEmail());
        popular.setFirstName(account.getFirstName());
        popular.setLastName(account.getLastName());
        popular.setIdCard(account.getIdCard());
        popular.setGender(account.getGender());
        popular.setVerify(false);
        popular.setAdmin(false);
        popular.setRole(roleRepository.findRoleByName("USER"));
        return popular;
    }
//
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
