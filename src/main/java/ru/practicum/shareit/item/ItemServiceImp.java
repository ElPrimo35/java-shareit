package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.ItemRequestStorage;
import ru.practicum.shareit.user.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {
    private final ItemStorage itemStorage;
    private final ItemMapper itemMapper;
    private final UserStorage userStorage;
    private final ItemRequestStorage itemRequestStorage;


    @Override
    public ItemDto createItem(ItemDto itemDto, Integer userId) {
        return itemMapper.toDto(itemStorage.createItem(itemMapper.toItem(itemDto, userStorage.getUser(userId),
                itemRequestStorage.getRequest(itemDto.getRequest()))));
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, Integer itemId, Integer userId) {
        if (itemStorage.getItem(itemId) == null) {
            throw new NotFoundException("Item not found");
        }
        itemDto.setId(itemId);
        return itemMapper.toDto(itemStorage.updateItem(itemMapper.toItem(itemDto, userStorage.getUser(userId),
                itemRequestStorage.getRequest(itemDto.getRequest())), itemId));
    }

    @Override
    public ItemDto getItem(Integer itemId, Integer userId) {
        userStorage.getUser(userId);
        return itemMapper.toDto(itemStorage.getItem(itemId));
    }

    @Override
    public List<ItemDto> getAllItems(Integer userId) {
        userStorage.getUser(userId);
        return itemStorage.getAllItems(userId).stream()
                .map(itemMapper::toDto)
                .toList();
    }

    @Override
    public List<ItemDto> getByDescription(String text, Integer userId) {
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        return itemStorage.getByDescription(text).stream()
                .map(itemMapper::toDto)
                .toList();
    }
}
