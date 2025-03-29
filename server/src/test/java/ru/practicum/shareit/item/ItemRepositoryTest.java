package ru.practicum.shareit.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    User getUser3() {
        User user = new User();
        user.setId(3);
        user.setName("Test2");
        user.setEmail("Test2@Test");
        return user;
    }

    User getUser1() {
        User user = new User();
        user.setId(1);
        user.setName("Test");
        user.setEmail("Test@Test");
        return user;
    }

    Item getItem() {
        Item item = new Item();
        item.setId(3);
        item.setName("Test2");
        item.setDescription("Test2");
        item.setAvailable(true);
        item.setOwner(getUser3());
        item.setRequest(null);
        return item;
    }

    ItemRequest getRequest() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(1);
        itemRequest.setDescription("Test");
        itemRequest.setRequestor(getUser1());
        itemRequest.setCreated(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        return itemRequest;
    }

    Item getItem1() {
        Item item = new Item();
        item.setId(1);
        item.setName("Test");
        item.setDescription("Test");
        item.setAvailable(true);
        item.setOwner(getUser1());
        item.setRequest(getRequest());
        return item;
    }

    @Test
    void findTest() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(getItem());
        List<Item> items = itemRepository.find("t2");
        Assertions.assertEquals(itemList, items);
    }


    @Test
    void findByUserIdTest() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(getItem());
        List<Item> items = itemRepository.findByUserId(3);
        Assertions.assertEquals(itemList, items);
    }

    @Test
    void findByRequestId() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(getItem1());
        List<Item> items = itemRepository.findByRequestId(1);
        Assertions.assertEquals(itemList, items);
    }
}
