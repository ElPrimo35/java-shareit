package ru.practicum.shareit.item.dto;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDto {
    private final Integer id;
    private final String name;
    private final String description;
    private final Boolean available;
    private final Integer request;
}
