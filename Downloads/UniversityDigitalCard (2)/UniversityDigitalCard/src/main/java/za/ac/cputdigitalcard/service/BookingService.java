package za.ac.cputdigitalcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cputdigitalcard.domain.Booking;
import za.ac.cputdigitalcard.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService implements IBooking {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Booking booking) {
        if (bookingRepository.findByBookingId(booking.getBookingId()) != null) {
            throw new RuntimeException("BookingId or studentId already exists");
        }

        Booking createdBooking =  this.bookingRepository.save(booking);
        System.out.println("Booking created : " + createdBooking.getBookingId());
        return createdBooking;
    }

    @Override
    public Booking read(Long aLong) {
        if (aLong == null & bookingRepository.findByBookingId(aLong) == null & bookingRepository.findByBookingId(aLong) == null) {
            throw new RuntimeException("Booking not found");
        }
        System.out.println("Booking found: ");
        return bookingRepository.findByBookingId(aLong);
    }


    @Override
    public Booking update(Booking booking) {
        Booking existingBooking = bookingRepository.findByBookingId(booking.getBookingId());

        if (existingBooking == null) {
            throw new RuntimeException("Booking not found for update");
        }

        System.out.println("Booking found for update: " + booking.getBookingId());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking delete(Long aLong) {
        Booking studentBooking = bookingRepository.findByStudentId(aLong);
        if (aLong == null & studentBooking == null) {
            throw new RuntimeException("Booking not found for deletion");
        }
        bookingRepository.delete(studentBooking);
        return studentBooking;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking searchByBookingId(Long bookingId) {
        if (bookingId == null) {
            throw new RuntimeException("Booking ID cannot be null");
        }

        Booking booking = bookingRepository.findByBookingId(bookingId);
        if (booking == null) {
            throw new RuntimeException("Booking not found for ID: " + bookingId);
        }

        return booking;
    }

    // Method to search booking by studentId
    public Booking searchByStudentId(Long studentId) {
        if (studentId == null) {
            throw new RuntimeException("Student ID cannot be null");
        }
        return bookingRepository.findByStudentId(studentId);
    }
}
