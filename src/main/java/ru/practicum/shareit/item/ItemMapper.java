package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequestStorage;
import ru.practicum.shareit.user.UserStorage;

@AllArgsConstructor
@Component
public class ItemMapper {
    private final UserStorage userStorage;
    private final ItemRequestStorage itemRequestStorage;

    public ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    public Item toItem(ItemDto itemDto, Integer userId, Integer itemId) {
        Item item = new Item();
        item.setId(itemId);
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(userStorage.getUser(userId));
        item.setRequest(itemDto.getRequest() == null ? null : itemRequestStorage.getRequest(itemDto.getRequest()));
        return item;
    }
}
