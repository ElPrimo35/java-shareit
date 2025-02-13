package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/requests")
@AllArgsConstructor
public class ItemRequestController {
    private final ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequest createRequest(@RequestBody ItemRequest itemRequest) {
        return itemRequestService.createRequest(itemRequest);
    }

    @GetMapping("/{requestId}")
    public ItemRequest getRequest(@PathVariable Integer requestId) {
        return itemRequestService.getRequest(requestId);
    }

    @PatchMapping("/{requestId}")
    public ItemRequest updateRequest(@RequestBody ItemRequest itemRequest, @PathVariable Integer requestId) {
        return itemRequestService.updateRequest(itemRequest, requestId);
    }

    @DeleteMapping("/{requestId}")
    public void deleteRequest(@PathVariable Integer requestId) {
        itemRequestService.deleteRequest(requestId);
    }
}
