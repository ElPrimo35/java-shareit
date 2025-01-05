package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item createItem(ItemDto itemDto, Integer userId);

    Item updateItem(ItemDto itemDto, Integer itemId, Integer userId);

    Item getItem(Integer itemId, Integer userId);

    List<Item> getAllItems(Integer userId);

    List<Item> getByDescription(String text, Integer userId);
}
