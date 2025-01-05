package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequestStorage;
import ru.practicum.shareit.user.UserStorage;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ItemStorageImp implements ItemStorage {
    private final UserStorage userStorage;
    private final ItemRequestStorage itemRequestStorage;
    private final Map<Integer, Item> items = new HashMap<>();
    private Integer itemIdGenerator = 1;

    private Item toItem(ItemDto itemDto, Integer userId, Integer itemId) {
        Item item = new Item();
        item.setId(itemId);
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(userStorage.getUser(userId));
        item.setRequest(itemDto.getRequest() == null ? null : itemRequestStorage.getRequest(itemDto.getRequest()));
        return item;
    }

    @Override
    public Item createItem(ItemDto itemDto, Integer userId) {
        Item item = toItem(itemDto, userId, itemIdGenerator);
        items.put(itemIdGenerator++, item);
        return item;
    }

    @Override
    public Item updateItem(ItemDto itemDto, Integer itemId, Integer userId) {
        if (items.get(itemId) == null) {
            throw new NotFoundException("Item not found");
        }
        Item item = toItem(itemDto, userId, itemId);
        items.put(itemId, item);
        return item;
    }

    @Override
    public Item getItem(Integer itemId, Integer userId) {
        return items.get(itemId);
    }

    @Override
    public List<Item> getAllItems(Integer userId) {
        return items.values().stream()
                .filter(item -> Objects.equals(item.getOwner().getId(), userId))
                .toList();
    }

    @Override
    public List<Item> getByDescription(String text, Integer userId) {
        List<Item> itemList = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getName() != null && item.getDescription() != null &&
                    (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                            item.getDescription().toLowerCase().contains(text.toLowerCase())) && item.getAvailable()) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}
