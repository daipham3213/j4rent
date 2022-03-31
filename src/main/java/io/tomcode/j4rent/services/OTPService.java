package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.repositories.OTPRepository;
import io.tomcode.j4rent.core.services.IOTPService;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.exception.InvalidOTPException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service("otpService")
public class OTPService implements IOTPService {
    private final OTPRepository otpRepository;

    public OTPService(OTPRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public OTP createOTP(UUID documentId) {
        OTP obj = new OTP(generateOTP(), documentId);
        return otpRepository.save(obj);
    }

    @Override
    public int generateOTP() {
        Random random = new SecureRandom();
        int otp = 100000 + random.nextInt(999999);
        if (otpRepository.findOTPSByOtp(otp).size() > 0) {
            otp = generateOTP();
        }

        return otp;
    }

    @Override
    public void cleanOTP(int otp) {
        otpRepository.delete(otpRepository.findOTPByOtp(otp));
    }

    @Override
    public OTP getOTP(int otp) throws InvalidOTPException {
        OTP value = otpRepository.findOTPByOtp(otp);
        if (value == null) {
            throw new InvalidOTPException();
        }
        return value;
    }

}
