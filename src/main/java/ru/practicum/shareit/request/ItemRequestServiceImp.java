package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;

@Service
@AllArgsConstructor
public class ItemRequestServiceImp implements ItemRequestService {
    private final ItemRequestStorage itemStorage;
    private final RequestRepository requestRepository;

    @Override
    public ItemRequest createRequest(ItemRequest itemRequest) {
//        return itemStorage.createRequest(itemRequest);
        return requestRepository.save(itemRequest);
    }

    @Override
    public ItemRequest getRequest(Integer requestId) {
//        return itemStorage.getRequest(requestId);
        return requestRepository.findById(requestId).orElseThrow(() -> new NotFoundException("request not found"));
    }

    @Override
    public ItemRequest updateRequest(ItemRequest itemRequest, Integer requestId) {
//        return itemStorage.updateRequest(itemRequest, requestId);
        ItemRequest request = requestRepository.findById(requestId).orElseThrow(() -> new NotFoundException("request not found"));
        request.setDescription(itemRequest.getDescription());
        request.setRequestorId(itemRequest.getRequestorId());
        request.setCreated(itemRequest.getCreated());
        return request;
    }

    @Override
    public void deleteRequest(Integer requestId) {
//        return itemStorage.deleteRequest(requestId);
        requestRepository.deleteById(requestId);
    }
}
