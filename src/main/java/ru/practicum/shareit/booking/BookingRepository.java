package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("select b from Booking b where b.booker.id = ?1 order by b.start desc")
    List<Booking> findBookingsByUserIdOrderByStartDesc(Integer userId);

    @Query("select b from Booking b where b.item.id = ?1 order by b.start desc")
    List<Booking> findBookingsByItemIdOrderByStartDesc(Integer itemId);

    @Query("select b from Booking b where b.item.id in ?1 order by b.start desc")
    List<Booking> findBookingsByItemIdsOrderByStartDesc(List<Integer> itemIds);
}
