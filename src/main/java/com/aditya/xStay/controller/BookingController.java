package com.aditya.xStay.controller;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.exception.ResourceNotFoundException;
import com.aditya.xStay.model.User;
import com.aditya.xStay.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // Endpoint to book a room
    @PostMapping("/{hotelId}/book")
    public ResponseEntity<?> bookRoom(@PathVariable Integer hotelId, @RequestBody User user) {
        try {
            bookingService.bookRoom(hotelId, user);
            return ResponseEntity.ok("Room booked successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Endpoint to cancel a booking
    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public ResponseEntity<?> cancelBooking(@PathVariable Integer bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking cancelled successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }
}


