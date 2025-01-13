package ru.practicum.shareit.request;

public interface ItemRequestStorage {
    ItemRequest createRequest(ItemRequest itemRequest);

    ItemRequest getRequest(Integer requestId);

    ItemRequest updateRequest(ItemRequest itemRequest, Integer requestId);

    ItemRequest deleteRequest(Integer requestId);
}
