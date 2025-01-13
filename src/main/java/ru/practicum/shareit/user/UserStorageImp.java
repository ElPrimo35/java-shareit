package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class UserStorageImp implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer userIdGenerator = 1;

    @Override
    public boolean isUnique(String email) {
        return users.values().stream()
                .noneMatch(value -> value.getEmail().equals(email));
    }

    @Override
    public User createUser(User user) {
        user.setId(userIdGenerator++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User getUser(Integer userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        User user1 = users.get(user.getId());
        if (user.getEmail() != null) {
            user1.setEmail(user.getEmail());
        }
        if (user.getName() != null) {
            user1.setName(user.getName());
        }
        users.put(user.getId(), user1);
        return user1;
    }

    @Override
    public void deleteUser(Integer userId) {
        if (users.get(userId) == null) {
            throw new NotFoundException("User not found");
        }
        users.remove(userId);
    }
}
