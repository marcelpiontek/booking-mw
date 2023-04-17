package de.mpi.bookingmw.booking;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingController {

    private final BookingRepo bookingRepo;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Booking savedBooking = bookingRepo.save(booking);
            return ResponseEntity.ok(savedBooking);
        }
    }

    @GetMapping
    public ResponseEntity<?> readAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookingRepo.findByOrderByIdAsc().forEach(bookings::add);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> readBookingById(@PathVariable UUID id) {
        Optional<Booking> bookingOptional = bookingRepo.findById(id);
        return bookingOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingById(@PathVariable UUID id) {
        Optional<Booking> bookingOptional = bookingRepo.findById(id);
        if (bookingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            bookingRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
