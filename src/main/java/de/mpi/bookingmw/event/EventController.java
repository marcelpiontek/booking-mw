package de.mpi.bookingmw.event;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {

    private final EventRepo eventRepo;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        if (event == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Event savedEvent = eventRepo.save(event);
            return ResponseEntity.ok(savedEvent);
        }
    }

    @GetMapping
    public ResponseEntity<?> readAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> readEventById(@PathVariable UUID id) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        return eventOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable UUID id) {
        Optional<Event> eventOptional = eventRepo.findById(id);
        if (eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            eventRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
