package ru.practicum.shareit.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
public class ItemControllerTest {
    @MockBean
    private ItemService itemService;

    @Autowired
    private MockMvc mockMvc;


    List<CommentDto> getComments() {
        List<CommentDto> comments = new ArrayList<>();
        CommentDto comment = new CommentDto();
        comment.setId(1);
        comment.setText("Test");
        comment.setAuthorName("Test");
        comment.setCreated(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        comments.add(comment);
        return comments;
    }


    ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(3);
        itemDto.setName("Test2");
        itemDto.setDescription("Test2");
        itemDto.setAvailable(true);
        itemDto.setRequestId(null);
        return itemDto;
    }

    ItemCommentDto getItemCommentDto() {
        ItemCommentDto item = new ItemCommentDto();
        item.setId(1);
        item.setName("Test");
        item.setDescription("Test");
        item.setAvailable(true);
        item.setRequest(1);
        item.setComments(getComments());
        return item;
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }


    @Test
    void createItemTest() throws Exception {
        Integer userId = 5;
        ItemDto itemDto = getItemDto();
        String itemDtoJson = objectMapper().writeValueAsString(itemDto);

        when(itemService.createItem(any(ItemDto.class), eq(userId))).thenReturn(itemDto);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemDtoJson)
                        .header("X-Sharer-User-Id", userId))
                .andExpect(status().isOk());

        verify(itemService, times(1)).createItem(any(ItemDto.class), eq(userId));
    }

    @Test
    void updateItemTest() throws Exception {
        Integer itemId = 1;
        Integer userId = 5;

        ItemDto inputItemDto = getItemDto();
        ItemDto updatedItemDto = getItemDto();
        updatedItemDto.setName("Updated Name");

        String inputJson = objectMapper().writeValueAsString(inputItemDto);

        when(itemService.updateItem(eq(inputItemDto), eq(itemId), eq(userId)))
                .thenReturn(updatedItemDto);

        mockMvc.perform(patch("/items/{itemId}", itemId)
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(itemService, times(1))
                .updateItem(eq(inputItemDto), eq(itemId), eq(userId));
    }

    @Test
    void getItemTest() throws Exception {
        Integer itemId = 1;
        Integer userId = 5;

        ItemCommentDto itemCommentDto = getItemCommentDto();

        when(itemService.getItem(eq(itemId), eq(userId)))
                .thenReturn(itemCommentDto);

        mockMvc.perform(get("/items/{itemId}", itemId)
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemService, times(1)).getItem(eq(itemId), eq(userId));
    }


    @Test
    void getAllItemsTest() throws Exception {
        Integer userId = 5;

        List<ItemDto> itemDtos = List.of(
                getItemDto(),
                getItemDto()
        );
        itemDtos.get(1).setId(4);
        itemDtos.get(1).setName("Modified Item");


        when(itemService.getAllItems(eq(userId)))
                .thenReturn(itemDtos);

        mockMvc.perform(get("/items")
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemService, times(1)).getAllItems(eq(userId));
    }

    @Test
    void getByDescriptionTest() throws Exception {
        String searchText = "test";
        Integer userId = 5;

        List<ItemDto> expectedItems = List.of(
                getItemDto(),
                getItemDto()
        );
        expectedItems.get(1).setName("Another Test");

        when(itemService.getByDescription(eq(searchText), eq(userId)))
                .thenReturn(expectedItems);

        mockMvc.perform(get("/items/search")
                        .header("X-Sharer-User-Id", userId)
                        .param("text", searchText)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemService, times(1)).getByDescription(eq(searchText), eq(userId));
    }

    @Test
    void getItemCommentsTest() throws Exception {
        Integer itemId = 1;
        Integer userId = 5;

        List<CommentDto> expectedComments = getComments();

        when(itemService.getItemComments(eq(itemId), eq(userId)))
                .thenReturn(expectedComments);

        mockMvc.perform(get("/items/{itemId}/comment", itemId)
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemService, times(1)).getItemComments(eq(itemId), eq(userId));
    }


    @Test
    void createCommentTest() throws Exception {
        Integer itemId = 1;
        Integer userId = 5;
        CommentDto inputComment = new CommentDto();
        inputComment.setText("Text");

        CommentDto expectedComment = new CommentDto();
        expectedComment.setId(1);
        expectedComment.setText(inputComment.getText());
        expectedComment.setAuthorName("Text");
        expectedComment.setCreated(LocalDateTime.now());

        String inputJson = objectMapper().writeValueAsString(inputComment);

        when(itemService.createComment(eq(itemId), any(CommentDto.class), eq(userId)))
                .thenReturn(expectedComment);

        mockMvc.perform(post("/items/{itemId}/comment", itemId)
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(itemService, times(1))
                .createComment(eq(itemId), any(CommentDto.class), eq(userId));
    }
}
