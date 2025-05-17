package com.eucl.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "eucl",
                        email = "bateteangenadette@gmail.com"
//                        url = "https://www.quantaLedger.com"
                ),
                description = " backend apis for eucl application",
                title = "ecul apis",
                version = "1.0",
                license = @License(
                        name = "eucl"
//                        url = "https://www.quantaLedger.com"
                ),
                termsOfService = "Feel free to clone and adapt this code for your own Spring Boot 3 project."
        ), servers = {
        @Server(
                description = "Local deployment  environment",
                url = "http://localhost:8081"
        ),
}
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Secure your API with a JWT  token  for user authorization",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
