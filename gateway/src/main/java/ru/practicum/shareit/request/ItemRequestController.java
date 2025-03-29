package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;

@RestController
@RequestMapping(path = "/requests")
@AllArgsConstructor
public class ItemRequestController {
    private final ItemRequestClient itemRequestClient;

    @PostMapping
    public ResponseEntity<Object> createRequest(@RequestBody ItemRequestDto itemRequestDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemRequestClient.createRequest(itemRequestDto, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getUserRequests(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemRequestClient.getUserRequests(userId);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequest(@PathVariable Integer requestId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemRequestClient.getRequestById(requestId, userId);
    }

    @GetMapping("/all")
    private ResponseEntity<Object> getAllUsersRequests(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return itemRequestClient.getAllUsersRequests(userId);
    }
}
