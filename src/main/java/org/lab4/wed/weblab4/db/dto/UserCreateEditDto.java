package org.lab4.wed.weblab4.db.dto;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserCreateEditDto {
    private String name;
    private String password;
    private LocalDate dateOfCreation;
}
