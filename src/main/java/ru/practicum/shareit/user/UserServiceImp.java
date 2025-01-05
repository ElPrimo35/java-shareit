package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserStorage userStorage;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        return userMapper.toDto(userStorage.createUser(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto getUser(Integer userId) {
        return userMapper.toDto(userStorage.getUser(userId));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        return userMapper.toDto(userStorage.updateUser(userMapper.toUser(userDto), userId));
    }

    @Override
    public UserDto deleteUser(Integer userId) {
        return userMapper.toDto(userStorage.deleteUser(userId));
    }
}
