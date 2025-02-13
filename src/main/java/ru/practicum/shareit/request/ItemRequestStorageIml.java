package ru.practicum.shareit.request;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemRequestStorageIml implements ItemRequestStorage {
    private final Map<Integer, ItemRequest> requests = new HashMap<>();
    private Integer requestIdGenerator = 1;

    @Override
    public ItemRequest createRequest(ItemRequest itemRequest) {
        itemRequest.setId(requestIdGenerator);
        requests.put(requestIdGenerator++, itemRequest);
        return itemRequest;
    }

    @Override
    public ItemRequest getRequest(Integer requestId) {
        return requests.get(requestId);
    }

    @Override
    public ItemRequest updateRequest(ItemRequest itemRequest, Integer requestId) {
        if (requests.get(requestId) == null) {
            throw new NotFoundException("Request not found");
        }
        requests.put(requestId, itemRequest);
        return itemRequest;
    }

    @Override
    public void deleteRequest(Integer requestId) {
        if (requests.get(requestId) == null) {
            throw new NotFoundException("Request not found");
        }
        ItemRequest itemRequest = requests.get(requestId);
        requests.remove(requestId);
    }
}
