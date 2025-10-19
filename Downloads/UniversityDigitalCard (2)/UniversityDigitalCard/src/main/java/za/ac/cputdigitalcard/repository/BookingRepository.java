package za.ac.cputdigitalcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cputdigitalcard.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingId(Long bookingId);
    Booking findByStudentId(Long studentId);
}
