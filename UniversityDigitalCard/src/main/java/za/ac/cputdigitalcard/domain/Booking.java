package za.ac.cputdigitalcard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private Long studentId;
    private String bookingDate;
    private String bookingTime;

    protected Booking() {
    }

    public Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.studentId = builder.studentId;
        this.bookingDate = builder.bookingDate;
        this.bookingTime = builder.bookingTime;
    }

    // Getters
    public Long getBookingId() {
        return bookingId;
    }
    public Long getStudentId() {
        return studentId;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public String getBookingTime() {
        return bookingTime;
    }

    // ToString method
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", studentId=" + studentId +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                '}';
    }

    // Builder Class
    public static class Builder {
        private Long bookingId;
        private Long studentId;
        private String bookingDate;
        private String bookingTime;

        public Builder setBookingId(Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder setStudentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder setBookingTime(String bookingTime) {
            this.bookingTime = bookingTime;
            return this;
        }

        // Copy method
        public Builder copy(Booking booking) {
            this.bookingId = booking.bookingId;
            this.studentId = booking.studentId;
            this.bookingDate = booking.bookingDate;
            this.bookingTime = booking.bookingTime;
            return this;
        }
        // Build method
        public Booking build() {
            return new Booking(this);
        }
    }
}
