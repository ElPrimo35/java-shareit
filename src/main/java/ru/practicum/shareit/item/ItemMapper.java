package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.dto.ItemCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

import java.util.List;

@Component
public class ItemMapper {

    public ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setAvailable(item.getAvailable());
        itemDto.setRequest(item.getRequest() != null ? item.getRequest().getId() : null);
        return itemDto;
    }

    public Item toItem(ItemDto itemDto, User user, ItemRequest itemRequest) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(user);
        item.setRequest(itemDto.getRequest() == null ? null : itemRequest);
        return item;
    }

    public ItemCommentDto toCommentDto(Item item, Booking lastBooking, Booking nextBooking, List<Comment> comments) {
        ItemCommentDto itemCommentDto = new ItemCommentDto();
        itemCommentDto.setId(item.getId());
        itemCommentDto.setName(item.getName());
        itemCommentDto.setDescription(item.getDescription());
        itemCommentDto.setAvailable(item.getAvailable());
        itemCommentDto.setRequest(item.getRequest() != null ? item.getRequest().getId() : null);
        itemCommentDto.setLastBooking(lastBooking);
        itemCommentDto.setNextBooking(nextBooking);
        itemCommentDto.setComments(comments);
        return itemCommentDto;
    }
}
