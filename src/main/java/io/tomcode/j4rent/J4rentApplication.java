package io.tomcode.j4rent;



import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SecurityScheme(name = "4rent", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer",in= SecuritySchemeIn.HEADER)
@Log4j2
@SpringBootApplication
@ComponentScan(basePackages = {"io.tomcode.j4rent.configuration"})
public class J4rentApplication {
    public static void main(String[] args) {
        SpringApplication.run(J4rentApplication.class, args);
    }
}
