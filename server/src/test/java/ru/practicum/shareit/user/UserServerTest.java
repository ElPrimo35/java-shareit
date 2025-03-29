package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class UserServerTest {
    private final UserService userService;

    UserDto getUser() {
        UserDto user = new UserDto();
        user.setId(8);
        user.setName("Test");
        user.setEmail("Test@Test199");
        return user;
    }

    UserDto getUser1() {
        UserDto user = new UserDto();
        user.setId(9);
        user.setName("Test");
        user.setEmail("Test@Test111");
        return user;
    }

    UserDto getUser2() {
        UserDto user = new UserDto();
        user.setId(9);
        user.setName("Test");
        user.setEmail("Test@Test122");
        return user;
    }

    UserDto getUser3() {
        UserDto user = new UserDto();
        user.setName("Test");
        user.setEmail("Test@Test123");
        return user;
    }

    UserDto getUpdatedUser() {
        UserDto user = new UserDto();
        user.setId(9);
        user.setName("TestName");
        user.setEmail("Test@Test122");
        return user;
    }

    @Test
    void createUserTest() {
        UserDto user = getUser();
        UserDto user1 = userService.createUser(getUser());
        Assertions.assertEquals(user, user1);
    }

    @Test
    void getUserTest() {
        UserDto user1 = userService.createUser(getUser1());
        Assertions.assertEquals(getUser1(), userService.getUser(getUser1().getId()));
    }

    @Test
    void updateUserTest() {
        UserDto user1 = userService.createUser(getUser2());
        user1.setName("TestName");
        UserDto user = userService.updateUser(user1, user1.getId());
        Assertions.assertEquals(user, getUpdatedUser());
    }

    @Test
    void deleteUserTest() {
        UserDto user = userService.createUser(getUser3());
        userService.deleteUser(user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> userService.getUser(user.getId()));
    }

}
