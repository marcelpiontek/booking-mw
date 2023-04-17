package de.mpi.bookingmw.event;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @PostConstruct
    public void initDummyEvent() {
        Event event = new Event();
        event.setTitle("INVisionDay 2023");
        event.setDescription("Der INVisionDay ist eine ganztägige Veranstaltung der Fakultät Informatik für die " +
                             "Studierenden der Fachrichtungen Informatik, Medieninformatik und Wirtschaftsinformatik. " +
                             "Ziel des INVisionDay ist, dass die Studierenden – jenseits der Lehrinhalte aus den " +
                             "Vorlesungen und Übungen – einen Einblick in aktuelle IT-Themen aus der Forschung und der " +
                             "Unternehmenspraxis erhalten.");
        event.setDate(new Date());
        event.setLocation("Technische Hochschule Nürnberg, Keßlerplatz 12, 90489 Nürnberg");
        event.setInfo("Indiana Codes und der heilige Gral der Softwareentwicklung - Workshop");
        event.setMaxParticipants(30);
        event.setReplyAddress("marcel.piontek@quanos.com");
        event.setTags(Arrays.asList("code", "workshop"));
        eventRepo.save(event);
    }

}
