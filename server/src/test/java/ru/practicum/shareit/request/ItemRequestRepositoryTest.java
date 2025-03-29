package ru.practicum.shareit.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class ItemRequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;

    User getUser1() {
        User user = new User();
        user.setId(1);
        user.setName("Test");
        user.setEmail("Test@Test");
        return user;
    }

    User getUser2() {
        User user = new User();
        user.setId(2);
        user.setName("Test1");
        user.setEmail("Test1@Test");
        return user;
    }


    ItemRequest getRequest() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(1);
        itemRequest.setDescription("Test");
        itemRequest.setRequestor(getUser1());
        itemRequest.setCreated(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        return itemRequest;
    }

    void itemRequestComparator(ItemRequest itemRequest, ItemRequest itemRequest1) {
        Assertions.assertEquals(itemRequest.getDescription(), itemRequest1.getDescription());
        Assertions.assertEquals(itemRequest.getRequestor().getId(), itemRequest1.getRequestor().getId());
        Assertions.assertEquals(itemRequest.getCreated(), itemRequest1.getCreated());
    }

    void itemRequestComparator(List<ItemRequest> itemRequests, List<ItemRequest> itemRequests1) {
        Assertions.assertEquals(itemRequests.size(), itemRequests1.size());
        for (int i = 0; i < itemRequests.size(); i++) {
            itemRequestComparator(itemRequests.get(i), itemRequests1.get(i));
        }
    }

    @Test
    void findByRequestor_Id() {
        List<ItemRequest> itemRequestList = new ArrayList<>();
        itemRequestList.add(getRequest());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "created");
        List<ItemRequest> itemRequests = requestRepository.findByRequestor_Id(1, newestFirst);
        itemRequestComparator(itemRequestList, itemRequests);
    }

    @Test
    void findAllExceptUser() {
        List<ItemRequest> itemRequestList = new ArrayList<>();
        itemRequestList.add(getRequest());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "created");
        List<ItemRequest> itemRequests = requestRepository.findAllExceptUser(getUser2().getId(), newestFirst);
        itemRequestComparator(itemRequestList, itemRequests);
    }
}
