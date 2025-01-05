package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.RequestException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImp implements ItemService {
    private final ItemStorage itemStorage;
    private final ItemMapper itemMapper;

    @Override
    public ItemDto createItem(ItemDto itemDto, Integer userId) {
        if (itemDto.getName().isEmpty() || itemDto.getDescription().isEmpty() || itemDto.getAvailable() == null) {
            throw new RequestException("Bad request");
        }
        return itemMapper.toDto(itemStorage.createItem(itemDto, userId));
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, Integer itemId, Integer userId) {
        return itemMapper.toDto(itemStorage.updateItem(itemDto, itemId, userId));
    }

    @Override
    public ItemDto getItem(Integer itemId, Integer userId) {
        return itemMapper.toDto(itemStorage.getItem(itemId, userId));
    }

    @Override
    public List<ItemDto> getAllItems(Integer userId) {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemStorage.getAllItems(userId)) {
            itemDtos.add(itemMapper.toDto(item));
        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> getByDescription(String text, Integer userId) {
        List<ItemDto> itemDtos = new ArrayList<>();
        if (text.isEmpty()) {
            return itemDtos;
        }
        for (Item item : itemStorage.getByDescription(text, userId)) {
            itemDtos.add(itemMapper.toDto(item));
        }
        return itemDtos;
    }
}
