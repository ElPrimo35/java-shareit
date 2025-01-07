package ru.practicum.shareit.user;


import ru.practicum.shareit.user.dto.UserDto;

public interface UserStorage {
    User createUser(UserDto user);

    User getUser(Integer userId);

    User updateUser(User user);

    void deleteUser(Integer userId);
}
