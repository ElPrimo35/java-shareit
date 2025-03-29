package ru.practicum.shareit.request;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<ItemRequest, Integer> {
    List<ItemRequest> findByRequestor_Id(Integer userId, Sort newestFirst);

    @Query("select i from ItemRequest i " +
            "where i.requestor.id != ?1")
    List<ItemRequest> findAllExceptUser(Integer userId, Sort newestFirst);
}
