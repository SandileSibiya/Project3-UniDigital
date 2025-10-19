package za.ac.cputdigitalcard.service;

import za.ac.cputdigitalcard.domain.Booking;

import java.util.List;

public interface IBooking extends IService <Booking, Long> {
    List<Booking> getBookings();
    Booking searchByBookingId(Long bookingId);
}
