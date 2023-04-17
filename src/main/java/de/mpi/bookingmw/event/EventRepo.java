package de.mpi.bookingmw.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepo extends CrudRepository<Event, UUID> {
    Iterable<Event> findByOrderByIdAsc();
}
