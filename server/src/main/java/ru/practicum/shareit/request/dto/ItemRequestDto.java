package ru.practicum.shareit.request.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.shareit.item.dto.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemRequestDto {
    private Integer id;
    private String description;
    private Integer requestorId;
    private LocalDateTime created;
    private List<RequestDto> items;
}
