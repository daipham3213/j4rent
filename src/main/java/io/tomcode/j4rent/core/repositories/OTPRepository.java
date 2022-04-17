package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.OTP;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OTPRepository extends BaseRepository<OTP, UUID> {
    OTP findOTPByOtp(int otp);

    List<OTP> findOTPSByOtp(int otp);

    void deleteAllByDocumentId(UUID uuid);

    void deleteOTPByDocumentId(UUID uuid);

}