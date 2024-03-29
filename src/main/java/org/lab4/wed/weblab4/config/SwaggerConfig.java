package org.lab4.wed.weblab4.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Operation;

@Configuration
@SecurityScheme(
  name = "Bearer Authorization",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class SwaggerConfig {
    
    @Bean
    public OperationCustomizer customGlobalHeaders() {

        return (Operation operation, HandlerMethod handlerMethod) -> {

            // Parameter missingParam1 = new Parameter()
            //         .in(ParameterIn.HEADER.toString())
            //         .schema(new StringSchema())
            //         .name("Authorization")
            //         .description("header Authorization")
            //         .required(true);
                    
            // Parameter missingParam2 = new Parameter()
            //         .in(ParameterIn.HEADER.toString())
            //         .schema(new StringSchema())
            //         .name("refreshToken")
            //         .description("header description2")
            //         .required(true);

            // // operation.addParametersItem(missingParam1);
            // operation.addParametersItem(missingParam2);

            return operation;
        };
    }
}
