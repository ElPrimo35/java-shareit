package ru.practicum.shareit.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    User getUser1() {
        User user = new User();
        user.setId(1);
        user.setName("Test");
        user.setEmail("Test@Test");
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

    Comment getComment() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setText("Test");
        comment.setItem(getItem1());
        comment.setAuthor(getUser1());
        comment.setCreated(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        return comment;
    }

    @Test
    void findAllByItem_Id() {
        List<Comment> comments = new ArrayList<>();
        comments.add(getComment());
        List<Comment> commentList = commentRepository.findAllByItem_Id(1);
        Assertions.assertEquals(commentList, comments);
    }
}
