package ru.practicum.shareit.user;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EmailException;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;


@Component
public class UserStorageImp implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer userIdGenerator = 1;

    private boolean isUnique(String email) {
        return users.values().stream()
                .noneMatch(value -> value.getEmail().equals(email));
    }

    @Override
    public User createUser(User user) {
        if (user.getEmail() == null) {
            throw new EmailException("User should have an email");
        }
        if (isUnique(user.getEmail())) {
            user.setId(userIdGenerator);
            users.put(userIdGenerator++, user);
            return user;
        }
        throw new EmailException("Email already exists");
    }

    @Override
    public User getUser(Integer userId) {
        if (users.get(userId) == null) {
            throw new NotFoundException("User not found");
        }
        return users.get(userId);
    }

    @Override
    public User updateUser(User user, Integer userId) {
        if (users.get(userId) == null) {
            throw new NotFoundException("User not found");
        }
        users.remove(userId);
        if (!isUnique(user.getEmail())) {
            throw new EmailException("Email already exists");
        }
        user.setId(userId);
        users.put(userId, user);
        return user;
    }

    @Override
    public User deleteUser(Integer userId) {
        if (users.get(userId) == null) {
            throw new NotFoundException("User not found");
        }
        User user = users.get(userId);
        users.remove(userId);
        return user;
    }
}
