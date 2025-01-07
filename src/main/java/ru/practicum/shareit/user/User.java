package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    @Email
    @NotNull
    private String email;
}
