package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item createItem(Item item, Integer userId);

    Item updateItem(Item item, Integer itemId, Integer userId);

    Item getItem(Integer itemId);

    List<Item> getAllItems(Integer userId);

    List<Item> getByDescription(String text, Integer userId);
}
