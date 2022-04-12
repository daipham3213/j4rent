package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.exception.InvalidOTPException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface IOTPService {
    OTP createOTP(UUID documentId);

    int generateOTP();

    void cleanOTP(UUID documentCode);

    OTP getOTP(int OTP) throws InvalidOTPException;



}
