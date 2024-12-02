package main;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    public void testReservationConstructorAndGetters() {
        LocalDate checkInDate = LocalDate.of(2024, 12, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 12, 5);
        Reservation reservation = new Reservation(
            1, 
            100, 
            200, 
            checkInDate, 
            checkOutDate, 
            500.0, 
            Reservation.Status.BOOKED, 
            true
        );

        assertEquals(1, reservation.getResId(), "Reservation ID should match.");
        assertEquals(100, reservation.getGuestId(), "Guest ID should match.");
        assertEquals(200, reservation.getRoomId(), "Room ID should match.");
        assertEquals(checkInDate, reservation.getCheckInDate(), "Check-in date should match.");
        assertEquals(checkOutDate, reservation.getCheckOutDate(), "Check-out date should match.");
        assertEquals(500.0, reservation.getTotalCost(), 0.01, "Total cost should match.");
        assertEquals(Reservation.Status.BOOKED, reservation.getStatus(), "Status should match.");
        assertTrue(reservation.getPayment(), "Payment status should match.");
    }

    @Test
    public void testReservationSetters() {
        LocalDate checkInDate = LocalDate.of(2024, 12, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 12, 5);
        Reservation reservation = new Reservation(
            1, 
            100, 
            200, 
            checkInDate, 
            checkOutDate, 
            500.0, 
            Reservation.Status.BOOKED, 
            true
        );

        reservation.setResId(2);
        reservation.setGuestId(101);
        reservation.setRoomId(201);
        reservation.setCheckInDate(LocalDate.of(2024, 12, 2));
        reservation.setCheckOutDate(LocalDate.of(2024, 12, 6));
        reservation.setTotalCost(600.0);
        reservation.setStatus(Reservation.Status.CHECKEDIN);
        reservation.setPayment(false);

        assertEquals(2, reservation.getResId(), "Reservation ID should match after update.");
        assertEquals(101, reservation.getGuestId(), "Guest ID should match after update.");
        assertEquals(201, reservation.getRoomId(), "Room ID should match after update.");
        assertEquals(LocalDate.of(2024, 12, 2), reservation.getCheckInDate(), "Check-in date should match after update.");
        assertEquals(LocalDate.of(2024, 12, 6), reservation.getCheckOutDate(), "Check-out date should match after update.");
        assertEquals(600.0, reservation.getTotalCost(), 0.01, "Total cost should match after update.");
        assertEquals(Reservation.Status.CHECKEDIN, reservation.getStatus(), "Status should match after update.");
        assertFalse(reservation.getPayment(), "Payment status should match after update.");
    }

    @Test
    public void testIsPaid() {
        Reservation reservation = new Reservation(
            1, 
            100, 
            200, 
            LocalDate.of(2024, 12, 1), 
            LocalDate.of(2024, 12, 5), 
            500.0, 
            Reservation.Status.BOOKED, 
            true
        );
        assertTrue(reservation.isPaid(), "Reservation should be paid.");

        reservation.setPayment(false);
        assertFalse(reservation.isPaid(), "Reservation should not be paid.");
    }

    @Test
    public void testToString() {
        Reservation reservation = new Reservation(
            1, 
            100, 
            200, 
            LocalDate.of(2024, 12, 1), 
            LocalDate.of(2024, 12, 5), 
            500.0, 
            Reservation.Status.BOOKED, 
            true
        );

        String expectedString = "Reservation{" +
                "reservationId=1" +
                ", guestId=100" +
                ", roomId=200" +
                ", checkInDate=2024-12-01" +
                ", checkOutDate=2024-12-05" +
                ", totalCost=500.0" +
                ", status='BOOKED'" +
                '}';

        assertEquals(expectedString, reservation.toString(), "toString output should match.");
    }
}
