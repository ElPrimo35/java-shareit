package ru.practicum.shareit.booking;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findBookingsByBooker_Id(Integer bookerId, Sort sort);

    List<Booking> findBookingsByItem_Id(Integer itemId, Sort sort);

    @Query("select b from Booking b join b.item i where b.id = ?1 and i.owner.id = ?2")
    Optional<Booking> findBookingByIdAndOwnerId(Integer bookingId, Integer ownerId);

    List<Booking> findByItem_Owner_Id(Integer userId, Sort newestFirst);

    List<Booking> findByItem_Owner_IdAndEndIsBefore(Integer userId, LocalDateTime currentTime, Sort newestFirst);

    List<Booking> findByItem_Owner_IdAndStartIsAfter(Integer userId, LocalDateTime currentTime, Sort newestFirst);

    List<Booking> findByBooker_IdAndEndIsBefore(Integer userId, LocalDateTime currentTime, Sort newestFirst);

    List<Booking> findByBooker_IdAndStartIsAfter(Integer userId, LocalDateTime currentTime, Sort newestFirst);

    @Query("select b from Booking b join b.item i " +
            "where b.start < ?2 " +
            "and b.end > ?2 " +
            "and i.owner.id = ?1")
    List<Booking> findByItemOwnerIdAndStartBeforeAndEndAfter(Integer ownerId, LocalDateTime currentDate, Sort newestFirst);

    @Query("select b from Booking b " +
            "where b.start < ?2 " +
            "and b.end > ?2 " +
            "and b.booker.id = ?1")
    List<Booking> findByBookerIdAndStartBeforeAndEndAfter(Integer bookerId, LocalDateTime currentDate, Sort newestFirst);

    List<Booking> findByItem_Owner_IdAndStatus(Integer userId, Status status, Sort newestFirst);

    List<Booking> findByBooker_IdAndStatus(Integer userId, Status status, Sort newestFirst);
}
