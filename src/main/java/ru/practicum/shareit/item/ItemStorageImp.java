package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ItemStorageImp implements ItemStorage {
    private final Map<Integer, Item> items = new HashMap<>();
    private Integer itemIdGenerator = 1;

    @Override
    public Item createItem(Item item, Integer userId) {
        item.setId(itemIdGenerator++);
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item updateItem(Item item, Integer itemId, Integer userId) {
        items.put(itemId, item);
        return item;
    }

    @Override
    public Item getItem(Integer itemId) {
        Item item = items.get(itemId);
        if (item == null) {
            throw new NotFoundException("Item not found");
        }
        return item;
    }

    @Override
    public List<Item> getAllItems(Integer userId) {
        return items.values().stream()
                .filter(item -> Objects.equals(item.getOwner().getId(), userId))
                .toList();
    }

    @Override
    public List<Item> getByDescription(String text, Integer userId) {
        return items.values().stream()
                .filter(item -> item.getName() != null && item.getDescription() != null &&
                        (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                                item.getDescription().toLowerCase().contains(text.toLowerCase())) && item.getAvailable())
                .toList();
    }
}
