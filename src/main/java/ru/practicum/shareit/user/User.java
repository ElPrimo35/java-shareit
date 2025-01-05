package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {
    private Integer id;
    private String name;
    @Email
    private String email;
}
