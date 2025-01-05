package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUser(Integer userId);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto deleteUser(Integer userId);
}
