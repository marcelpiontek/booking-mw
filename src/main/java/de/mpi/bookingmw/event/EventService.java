package de.mpi.bookingmw.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Component
public class EventService {
    private final EventRepo eventRepo;

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventRepo.findByOrderByIdAsc().forEach(events::add);
        return events;
    }

    public Optional<Event> getEventById(UUID id) {
        return eventRepo.findById(id);
    }

}
