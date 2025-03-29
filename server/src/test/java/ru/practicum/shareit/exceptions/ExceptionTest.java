package ru.practicum.shareit.exceptions;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.exception.EmailException;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.dto.UserDto;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class ExceptionTest {
    private final UserService userService;

    UserDto getUser() {
        UserDto user = new UserDto();
        user.setId(8);
        user.setName("Test");
        user.setEmail("Test@Test1");
        return user;
    }

    UserDto getUser1() {
        UserDto user = new UserDto();
        user.setId(9);
        user.setName("Test");
        user.setEmail("Test@Test1");
        return user;
    }

    @Test
    void emailExceptionTest() {
        userService.createUser(getUser());
        Assertions.assertThrows(EmailException.class, () -> userService.createUser(getUser1()));
    }


}
