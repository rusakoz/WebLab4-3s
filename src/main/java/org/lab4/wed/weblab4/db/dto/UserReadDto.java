package org.lab4.wed.weblab4.db.dto;

import java.time.LocalDate;

public record UserReadDto(Long id, String name, String password, LocalDate dateOfCreation) {
    
}
