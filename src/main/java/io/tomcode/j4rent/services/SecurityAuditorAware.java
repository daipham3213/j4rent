package io.tomcode.j4rent.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("auditorAware")
public class SecurityAuditorAware implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        try {
            Jwt obj = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.of(UUID.fromString(obj.getClaim("userId")));
        } catch (Exception e) {
            return null;
        }
    }
}
