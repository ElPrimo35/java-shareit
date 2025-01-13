package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EmailException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserStorage userStorage;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (!userStorage.isUnique(userDto.getEmail())) {
            throw new EmailException("Email already exists");
        }
        return userMapper.toDto(userStorage.createUser(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto getUser(Integer userId) {
        return userMapper.toDto(userStorage.getUser(userId));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        if (userStorage.getUser(userId) == null) {
            throw new NotFoundException("User not found");
        }
        if (!userStorage.isUnique(userDto.getEmail())) {
            throw new EmailException("Email already exists");
        }
        userDto.setId(userId);
        return userMapper.toDto(userStorage.updateUser(userMapper.toUser(userDto)));
    }

    @Override
    public void deleteUser(Integer userId) {
        userStorage.deleteUser(userId);
    }
}
