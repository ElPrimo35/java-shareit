package ru.practicum.shareit.booking;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findBookingsByBooker_Id(Integer bookerId, Sort sort);

    List<Booking> findBookingsByItem_Id(Integer itemId, Sort sort);

    @Query("select b from Booking b where b.item.id in ?1")
    List<Booking> findBookingsByItemIds(List<Integer> itemIds, Sort sort);

    Booking findBookingByIdAndBooker_Id(Integer bookingId, Integer bookerId);

    @Query("select b from Booking b where b.start < :currentDate and b.end > :currentDate")
    List<Booking> findBookingsByStartBeforeAndEndAfter(LocalDateTime currentDate, Sort sort);

    @Query("select b from Booking b join b.item i where b.id = ?1 and i.owner.id = ?2")
    Booking findBookingByIdAndOwnerId(Integer bookingId, Integer ownerId);

    List<Booking> findBookingsByEndBefore(LocalDateTime currentDate, Sort sort);

    List<Booking> findBookingsByStartAfter(LocalDateTime currentDate, Sort sort);


    List<Booking> findBookingsByStatus(Status status, Sort sort);
}
