package de.mpi.bookingmw.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private Date date;

    private String location;

    @Column(columnDefinition="TEXT")
    private String info;

    private int maxParticipants;

    private String replyAddress;

    @ElementCollection
    private List<String> tags;
}
