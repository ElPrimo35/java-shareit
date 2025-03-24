package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto createItem(@Valid @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.createItem(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@Valid @RequestBody ItemDto itemDto, @PathVariable Integer itemId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.updateItem(itemDto, itemId, userId);
    }

    @GetMapping("/{itemId}")
    public ItemCommentDto getItem(@PathVariable Integer itemId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.getItem(itemId, userId);
    }

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.getAllItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> getByDescription(@Valid @RequestParam String text, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.getByDescription(text, userId);
    }

    @GetMapping("{itemId}/comment")
    public List<CommentDto> getItemComments(@PathVariable Integer itemId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.getItemComments(itemId, userId);
    }

    @PostMapping("{itemId}/comment")
    public CommentDto createComment(@PathVariable Integer itemId, @RequestBody CommentDto commentDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemService.createComment(itemId, commentDto, userId);
    }
}
