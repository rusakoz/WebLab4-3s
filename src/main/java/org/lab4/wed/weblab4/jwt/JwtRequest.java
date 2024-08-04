package org.lab4.wed.weblab4.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class JwtRequest {
    private String userLogin;
    private String userPassword;
}
