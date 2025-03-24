package ru.practicum.shareit.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean available;
    private Integer request;
}
