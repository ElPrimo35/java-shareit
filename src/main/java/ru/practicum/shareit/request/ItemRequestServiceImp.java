package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemRequestServiceImp implements ItemRequestService {
    private final ItemRequestStorage itemStorage;

    @Override
    public ItemRequest createRequest(ItemRequest itemRequest) {
        return itemStorage.createRequest(itemRequest);
    }

    @Override
    public ItemRequest getRequest(Integer requestId) {
        return itemStorage.getRequest(requestId);
    }

    @Override
    public ItemRequest updateRequest(ItemRequest itemRequest, Integer requestId) {
        return itemStorage.updateRequest(itemRequest, requestId);
    }

    @Override
    public ItemRequest deleteRequest(Integer requestId) {
        return itemStorage.deleteRequest(requestId);
    }
}
