package ru.practicum.shareit.user;


public interface UserStorage {
    User createUser(User user);

    User getUser(Integer userId);

    User updateUser(User user, Integer userId);

    User deleteUser(Integer userId);
}
