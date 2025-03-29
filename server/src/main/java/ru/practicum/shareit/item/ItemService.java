package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto, Integer userId);

    ItemDto updateItem(ItemDto itemDto, Integer itemId, Integer userId);

    ItemCommentDto getItem(Integer itemId, Integer userId);

    List<ItemDto> getAllItems(Integer userId);

    List<ItemDto> getByDescription(String text, Integer userId);

    List<CommentDto> getItemComments(Integer itemId, Integer userId);

    CommentDto createComment(Integer itemId, CommentDto commentDto, Integer userId);
}
