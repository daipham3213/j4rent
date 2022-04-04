package io.tomcode.j4rent.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${app.cloudinary.name}")
    private  String cloudinary_name;

    @Value("${app.cloudinary.api_key}")
    private  String api_key;

    @Value("${app.cloudinary.api_secret}")
    private  String api_secret;

    private  Map<String, String> valuesMap = new HashMap<>();

    @Bean(name = "cloudinary")
    public  Cloudinary cloudinary() {
        valuesMap.put("cloud_name", cloudinary_name);
        valuesMap.put("api_key", api_key);
        valuesMap.put("api_secret", api_secret);
        Cloudinary cloudinary = new Cloudinary(valuesMap);
        return cloudinary;
    }

}
