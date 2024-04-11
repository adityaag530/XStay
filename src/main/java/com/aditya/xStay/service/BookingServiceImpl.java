package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.exception.ResourceNotFoundException;
import com.aditya.xStay.model.Booking;
import com.aditya.xStay.model.Hotel;
import com.aditya.xStay.model.User;
import com.aditya.xStay.repository.BookingRepository;
import com.aditya.xStay.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void bookRoom(Integer hotelId, User user) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        // Check if there are available rooms in the hotel
        int availableRooms = hotel.getNumberOfAvailableRooms();
        if (availableRooms > 0) {
            // Create a new booking
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setHotel(hotel);
            booking.setDate(LocalDate.now()); // Example: Booking for today

            // Update the number of available rooms in the hotel
            hotel.setNumberOfAvailableRooms(availableRooms - 1);
            hotelRepository.save(hotel);

            // Save the booking
            bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("No available rooms in this hotel");
        }
    }

    @Override
    public void cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        Hotel hotel = booking.getHotel();
        // Increase the number of available rooms in the hotel
        hotel.setNumberOfAvailableRooms(hotel.getNumberOfAvailableRooms() + 1);
        hotelRepository.save(hotel);

        // Delete the booking
        bookingRepository.delete(booking);
    }
}
