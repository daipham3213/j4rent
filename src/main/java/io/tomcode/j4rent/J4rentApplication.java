package io.tomcode.j4rent;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@SpringBootApplication
@ComponentScan(basePackages = {"io.tomcode.j4rent.configuration"})
public class J4rentApplication {

    public static void main(String[] args) {
        SpringApplication.run(J4rentApplication.class, args);
    }


}
