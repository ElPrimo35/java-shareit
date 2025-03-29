package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Integer id;
    @NotBlank
    private String text;
    @NotBlank
    private String authorName;
    private LocalDateTime created;
}
