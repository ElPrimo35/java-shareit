package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class ItemServiceTest {
    private final ItemService itemService;

    BookingDto getBookingDto5() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(5);
        bookingDto.setStart(LocalDateTime.of(2022, 12, 12, 0, 0, 0));
        bookingDto.setEnd(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        bookingDto.setItemId(null);
        return bookingDto;
    }

    BookingDto getBookingDto6() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(6);
        bookingDto.setStart(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        bookingDto.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        bookingDto.setItemId(null);
        return bookingDto;
    }

    ItemCommentDto getItem4() {
        ItemCommentDto item = new ItemCommentDto();
        item.setId(4);
        item.setName("Test3");
        item.setDescription("Test3");
        item.setAvailable(true);
        item.setLastBooking(getBookingDto5());
        item.setNextBooking(getBookingDto6());
        item.setRequest(null);
        item.setComments(List.of());
        return item;
    }

    List<ItemDto> getItemDtoList1() {
        List<ItemDto> itemDtos = new ArrayList<>();
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1);
        itemDto.setName("Test");
        itemDto.setDescription("Test");
        itemDto.setAvailable(true);
        itemDto.setRequestId(1);
        itemDtos.add(itemDto);
        return itemDtos;
    }

    List<ItemDto> getItemDtoList2() {
        List<ItemDto> itemDtos = new ArrayList<>();
        ItemDto itemDto = new ItemDto();
        itemDto.setId(2);
        itemDto.setName("Test1");
        itemDto.setDescription("Test1");
        itemDto.setAvailable(true);
        itemDto.setRequestId(2);
        itemDtos.add(itemDto);
        return itemDtos;
    }

    ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(5);
        itemDto.setName("Test5");
        itemDto.setDescription("Test5");
        itemDto.setAvailable(true);
        itemDto.setRequestId(null);
        return itemDto;
    }

    ItemDto getItemDtoUpdated() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(5);
        itemDto.setName("Test5Update");
        itemDto.setDescription("Test5");
        itemDto.setAvailable(true);
        itemDto.setRequestId(1);
        return itemDto;
    }

    CommentDto getCommentDto() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(4);
        commentDto.setText("Text");
        commentDto.setAuthorName("Test");
        commentDto.setCreated(LocalDateTime.of(2025, 4, 1, 0, 0, 0));
        return commentDto;
    }

    CommentDto getCommentDto2() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(2);
        commentDto.setText("Test");
        commentDto.setAuthorName("Test1");
        commentDto.setCreated(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        return commentDto;
    }

    CommentDto getCommentDto3() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(3);
        commentDto.setText("Test");
        commentDto.setAuthorName("Test1");
        commentDto.setCreated(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        return commentDto;
    }

    @Test
    void getItemTest() {
        ItemCommentDto itemDto = getItem4();
        ItemCommentDto itemDto1 = itemService.getItem(4, 7);
        Assertions.assertEquals(itemDto, itemDto1);
    }

    @Test
    void getItemManyBookingsTest() {
        ItemCommentDto itemDto = getItem4();
        ItemCommentDto itemDto1 = itemService.getItem(4, 1);
        Assertions.assertEquals(itemDto, itemDto1);
    }

    @Test
    void createItemTest() {
        ItemDto itemDto1 = getItemDto();
        ItemDto itemDto = itemService.createItem(getItemDto(), 7);
        Assertions.assertEquals(itemDto1, itemDto);
    }

    @Test
    void updateItemTest() {
        ItemDto itemDto = itemService.createItem(getItemDto(), 7);
        ItemDto itemDto1 = itemService.updateItem(getItemDtoUpdated(), itemDto.getId(), 7);
        Assertions.assertEquals("Test5Update", itemDto1.getName());
    }

    @Test
    void createCommentTest() {
        CommentDto commentDto = getCommentDto();
        CommentDto commentDto1 = itemService.createComment(1, commentDto, 1);
        Assertions.assertEquals(commentDto.getText(), commentDto1.getText());
        Assertions.assertEquals(commentDto.getAuthorName(), commentDto1.getAuthorName());
    }

    @Test
    void getAllItemsTest() {
        List<ItemDto> itemDtosTest = getItemDtoList1();
        List<ItemDto> itemDtos = itemService.getAllItems(1);
        Assertions.assertEquals(itemDtosTest, itemDtos);
    }

    @Test
    void getByDescriptionTest() {
        List<ItemDto> itemDtosTest = getItemDtoList2();
        List<ItemDto> itemDtos = itemService.getByDescription("Test1", 1);
        Assertions.assertEquals(itemDtosTest, itemDtos);

        List<ItemDto> itemDtoEmptyListTest = new ArrayList<>();
        List<ItemDto> itemDtoEmptyList = itemService.getByDescription("", 1);
        Assertions.assertEquals(itemDtoEmptyListTest, itemDtoEmptyList);
    }

    @Test
    void getItemCommentsTest() {
        List<CommentDto> commentDtoListTest = new ArrayList<>();
        commentDtoListTest.add(getCommentDto2());
        commentDtoListTest.add(getCommentDto3());
        List<CommentDto> commentDtoList = itemService.getItemComments(2, 1);
        Assertions.assertEquals(commentDtoListTest, commentDtoList);
    }
}
