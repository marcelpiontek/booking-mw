package de.mpi.bookingmw.booking;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepo extends CrudRepository<Booking, UUID> {
    Iterable<Booking> findByOrderByIdAsc();

    Iterable<Booking> findByEventIdOrderByIdAsc(String eventId);
}
