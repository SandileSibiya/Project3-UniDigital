package za.ac.cputdigitalcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cputdigitalcard.domain.Booking;
import za.ac.cputdigitalcard.repository.BookingRepository;
import za.ac.cputdigitalcard.service.BookingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cputdigitalcard.com")

public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
    }

    // Method to add booking to the database
    @PostMapping("/Book")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
        try {
            Booking searchbooking = bookingService.searchByStudentId(booking.getStudentId());
            if (searchbooking != null) {
                return ResponseEntity.badRequest().body("Booking already exists for the student");
            }
            Booking newBooking = bookingService.create(booking);
            return ResponseEntity.ok(newBooking);

        } catch (RuntimeException e) {
            System.out.println("Error saving booking: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error saving booking: " + e.getMessage());
        }
    }
}
