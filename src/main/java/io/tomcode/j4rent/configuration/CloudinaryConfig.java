package io.tomcode.j4rent.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final Map<String, String> valuesMap = new HashMap<>();
    @Value("${app.cloudinary.name}")
    private String cloudinaryName;
    @Value("${app.cloudinary.api_key}")
    private String apiKey;
    @Value("${app.cloudinary.api_secret}")
    private String apiSecret;

    @Bean(name = "cloudinary")
    public Cloudinary cloudinary() {
        valuesMap.put("cloud_name", cloudinaryName);
        valuesMap.put("api_key", apiKey);
        valuesMap.put("api_secret", apiSecret);
        return new Cloudinary(valuesMap);
    }

}
