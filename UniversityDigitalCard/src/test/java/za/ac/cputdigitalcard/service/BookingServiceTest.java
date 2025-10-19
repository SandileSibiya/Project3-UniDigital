package za.ac.cputdigitalcard.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import za.ac.cputdigitalcard.domain.Booking;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    private Booking booking =  new Booking.Builder()
         //   .setBookingId(1001L)
            .setStudentId(200975623L)
            .setBookingDate("2024-10-10")
            .setBookingTime("10:10")
            .build();

    @Test
    @Order(1)
    void create() {
        Booking savedBooking = bookingService.create(booking);
        assertNotNull(savedBooking);
        System.out.println("Saved Booking: " + savedBooking);
    }

    @Test
    @Order(2)
    void read() {
        Booking readBooking = bookingService.read(booking.getBookingId());
        assertNotNull(readBooking);
        System.out.println("Read Booking: " + readBooking);
    }

    @Test
    @Order(3)
    void update() {
        Booking updatedBooking = new Booking.Builder()
                .copy(booking)
                .setBookingTime("12:00")
                .build();
        Booking result = bookingService.update(updatedBooking);
        assertNotNull(result);
        System.out.println("Updated Booking: " + result);
    }

//    @Test
//    @Order(4)
//    void delete() {
//        Booking deletedBooking = bookingService.delete(booking.getStudentId());
//        assertNotNull(deletedBooking);
//        System.out.println("Deleted Booking: " + deletedBooking);
//    }

    @Test
    @Order(5)
    void getBookings() {
        System.out.println("All Bookings: ");
        System.out.println(bookingService.getBookings());
    }

    @Test
    @Order(6)
    void searchByBookingId() {
        Long bookingId = 1001L;

        Booking foundBooking = bookingService.searchByBookingId(booking.getBookingId());
        assertNotNull(foundBooking);
        System.out.println("Found Booking by ID: " + foundBooking);
    }

    @Test
    @Order(6)
    void searchByStudentId() {
        Long studentId = 230975623L;

        Booking foundBooking = bookingService.searchByStudentId(studentId);
        assertNotNull(foundBooking);
        System.out.println("Found Booking by ID: " + foundBooking);
    }
}