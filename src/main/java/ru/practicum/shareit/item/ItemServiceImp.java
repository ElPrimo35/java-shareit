package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemCommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.request.ItemRequestStorage;
import ru.practicum.shareit.request.RequestRepository;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {
    private final ItemMapper itemMapper;
    private final ItemRequestStorage itemRequestStorage;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ItemDto createItem(ItemDto itemDto, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        ItemRequest request = itemRequestStorage.getRequest(itemDto.getRequest());
        Item item = itemRepository.save(itemMapper.toItem(itemDto, user, request));
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, Integer itemId, Integer userId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        ItemRequest request = itemRequestStorage.getRequest(itemDto.getRequest());
        if (itemDto.getName() != null) {
            item.setName(itemDto.getName());
        }
        if (itemDto.getDescription() != null) {
            item.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }
        item.setRequest(request);
        itemRepository.save(item);
        return itemMapper.toDto(item);
    }

    @Override
    public ItemCommentDto getItem(Integer itemId, Integer userId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<Comment> comments = commentRepository.findAllByItem_Id(itemId);
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> itemBookings = bookingRepository.findBookingsByItem_Id(itemId, newestFirst).stream()
                .toList();
        Booking lastBooking;
        Booking nextBooking;
        if (itemBookings.size() > 1) {
            List<Booking> pastBookings = itemBookings.stream()
                    .filter(bookingSecondDto -> LocalDateTime.now().isAfter(bookingSecondDto.getEnd()))
                    .toList();
            List<Booking> futureBookings = itemBookings.stream()
                    .filter(bookingSecondDto -> LocalDateTime.now().isBefore(bookingSecondDto.getStart()))
                    .toList();
            if (!pastBookings.isEmpty()) {
                lastBooking = pastBookings.getLast();
            } else {
                lastBooking = null;
            }
            if (!futureBookings.isEmpty()) {
                nextBooking = futureBookings.getFirst();
            } else {
                nextBooking = null;
            }
        } else {
            lastBooking = null;
            nextBooking = null;
        }

        return itemMapper.toCommentDto(item, lastBooking, nextBooking, comments);
    }

    @Override
    public List<ItemDto> getAllItems(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return itemRepository.findByUserId(userId).stream()
                .map(itemMapper::toDto)
                .toList();
    }

    @Override
    public List<ItemDto> getByDescription(String text, Integer userId) {
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        return itemRepository.find(text)
                .stream()
                .map(itemMapper::toDto)
                .toList();
    }

    @Override
    public List<CommentDto> getItemComments(Integer itemId, Integer userId) {
        List<Comment> comments = commentRepository.findAllByItem_Id(itemId);
        return comments.stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public CommentDto createComment(Integer itemId, CommentDto commentDto, Integer userId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookings = bookingRepository.findBookingsByBooker_Id(userId, newestFirst);
//        List<Booking> bookings = bookingRepository.findBookingsByUserIdOrderByStartDesc(userId);
        if (bookings.isEmpty()) {
            throw new RuntimeException("This user cannot comment");
        }
        Comment comment = commentMapper.toComment(commentDto, item, user, LocalDateTime.now());
        if (bookings.getFirst().getEnd().isAfter(comment.getCreated())) {
            throw new RuntimeException("This user cannot comment");
        }
        return commentMapper.toDto(commentRepository.save(comment));
    }
}
