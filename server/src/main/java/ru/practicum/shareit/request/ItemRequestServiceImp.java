package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.dto.RequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemRequestServiceImp implements ItemRequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemRequestDto createRequest(ItemRequestDto itemRequestDto, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        ItemRequest itemRequest = requestRepository.save(requestMapper.toRequest(itemRequestDto, user, LocalDateTime.now()));
        return requestMapper.toDto(itemRequest, itemRequest.getRequestor().getId(), LocalDateTime.now());
    }

    @Override
    public List<ItemRequestDto> getUserRequests(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "created");
        List<ItemRequest> itemRequestList = requestRepository.findByRequestor_Id(userId, newestFirst);
        return itemRequestList.stream()
                .map(request -> requestMapper.toRequestDto(request, userId, request.getCreated(),
                        itemRepository.findByRequestId(request.getId()).stream().map(itemMapper::toItemRequestDto).toList()))
                .toList();
    }

    @Override
    public ItemRequestDto getRequestById(Integer requestId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        ItemRequest itemRequest = requestRepository.findById(requestId).orElseThrow(() -> new NotFoundException("Request not found"));
        List<RequestDto> items = itemRepository.findByRequestId(requestId).stream().map(itemMapper::toItemRequestDto).toList();
        return requestMapper.toRequestDto(itemRequest, itemRequest.getRequestor().getId(), itemRequest.getCreated(), items);
    }

    @Override
    public List<ItemRequestDto> getAllUsersRequests(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "created");
        List<ItemRequest> itemRequestList = requestRepository.findAllExceptUser(userId, newestFirst);
        return itemRequestList.stream()
                .map(itemRequest -> requestMapper.toDto(itemRequest, userId, itemRequest.getCreated()))
                .toList();
    }
}
