package io.tomcode.j4rent.core.services;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface IJwtService {
    String createJwtForClaims(String subject, Map<String, String> claims);
}
