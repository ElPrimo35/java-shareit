package ru.practicum.shareit.request.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.dto.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ItemRequestDto {
    private Integer id;
    private String description;
    private Integer requestorId;
    private LocalDateTime created;
    private List<RequestDto> requestDtoList;
}
