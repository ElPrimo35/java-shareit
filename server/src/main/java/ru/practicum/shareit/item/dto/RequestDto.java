package ru.practicum.shareit.item.dto;

import lombok.Data;

@Data
public class RequestDto {
    private Integer itemId;
    private String name;
    private Integer ownerId;
}
