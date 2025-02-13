package ru.practicum.shareit.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EmailException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final UserRepository repository;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (repository.existsByEmail(userDto.getEmail())) {
            throw new EmailException("Email already exists");
        }
        User user = repository.save(userMapper.toUser(userDto));
        return userMapper.toDto(user);

    }

    @Override
    public UserDto getUser(Integer userId) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        user.setId(userId);
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        repository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        repository.deleteById(userId);
    }
}
