package ru.practicum.shareit.user;


public interface UserStorage {
    boolean isUnique(String email);

    User createUser(User user);

    User getUser(Integer userId);

    User updateUser(User user);

    void deleteUser(Integer userId);
}
