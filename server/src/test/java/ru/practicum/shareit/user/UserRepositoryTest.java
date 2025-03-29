package ru.practicum.shareit.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User getUser() {
        User user = new User();
        user.setName("Test");
        user.setEmail("Test@Test");
        return user;
    }

    @Test
    void existsByEmail() {
        boolean b = userRepository.existsByEmail(getUser().getEmail());
        Assertions.assertTrue(b);
    }
}
