package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemClient itemClient;

    @PostMapping
    public ResponseEntity<Object> createItem(@Valid @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.createItem(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> updateItem(@Valid @RequestBody ItemDto itemDto, @PathVariable Integer itemId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.updateItem(itemDto, itemId, userId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItem(@PathVariable Integer itemId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.getItem(itemId, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllItems(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.getAllItems(userId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getByDescription(@Valid @RequestParam String text, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.getByDescription(text, userId);
    }

    @GetMapping("{itemId}/comment")
    public ResponseEntity<Object> getItemComments(@PathVariable Integer itemId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.getItemComments(itemId, userId);
    }

    @PostMapping("{itemId}/comment")
    public ResponseEntity<Object> createComment(@PathVariable Integer itemId, @RequestBody CommentDto commentDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemClient.createComment(itemId, commentDto, userId);
    }
}
