package ru.practicum.shareit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.user.dto.UserDto;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    UserDto getUser10() {
        UserDto user = new UserDto();
        user.setId(10);
        user.setName("Test10");
        user.setEmail("Test10@Test");
        return user;
    }

    UserDto getUser2() {
        UserDto user = new UserDto();
        user.setId(2);
        user.setName("Test1");
        user.setEmail("Test1@Test");
        return user;
    }

    UserDto getUser1() {
        UserDto user = new UserDto();
        user.setId(11);
        user.setName("Test18");
        user.setEmail("Test10@Test");
        return user;
    }

    UserDto getUser() {
        UserDto user = new UserDto();
        user.setName("Test18");
        return user;
    }

    @Test
    void createUserTest() throws Exception {

        UserDto userDto = getUser10();
        String userJson = new ObjectMapper().writeValueAsString(userDto);


        when(userService.createUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));


        verify(userService, times(1)).createUser(userDto);

    }

    @Test
    void updateUserTest() throws Exception {

        UserDto userDto = getUser1();
        UserDto userDto1 = getUser();
        String userJson = new ObjectMapper().writeValueAsString(userDto1);

        when(userService.updateUser(userDto1, 11)).thenReturn(userDto1);

        mockMvc.perform(patch("/users/{userId}", 11)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));


        verify(userService, times(1)).updateUser(userDto1, 11);
    }


    @Test
    void getUserTest() throws Exception {
        UserDto userDto = getUser2();
        String userJson = new ObjectMapper().writeValueAsString(userDto);
        Integer userId = userDto.getId();
        when(userService.getUser(userId)).thenReturn(userDto);


        mockMvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));


        verify(userService, times(1)).getUser(userId);
    }

    @Test
    void deleteUserTest() throws Exception {
        UserDto userDto = getUser2();
        String userJson = new ObjectMapper().writeValueAsString(userDto);
        Integer userId = userDto.getId();

        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(userId);
    }
}
