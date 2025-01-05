package ru.practicum.shareit.request;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Data
public class ItemRequest {
    private Integer id;
    private String description;
    private Integer requestorId;
    private LocalDateTime created;
}
