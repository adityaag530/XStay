package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.aditya.xStay.model.Booking;
import com.aditya.xStay.model.Hotel;
import com.aditya.xStay.model.Role;
import com.aditya.xStay.model.User;
import com.aditya.xStay.repository.BookingRepository;
import com.aditya.xStay.repository.HotelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Hotel hotel;
    private User user;

    @Before
    public void setUp() {
        hotel = new Hotel(1, "Hotel A", "Location A", "Description A", 10);
        user = new User(1, "user1@example.com", "password", "John", "Doe", Role.CUSTOMER);
    }

    @Test
    public void testBookRoom() {
        // Arrange
        Mockito.when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));

        // Act
        bookingService.bookRoom(1, user);

        // Assert
        assertEquals(9, hotel.getNumberOfAvailableRooms());
        verify(hotelRepository).save(hotel);
        verify(bookingRepository).save(Mockito.any(Booking.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBookRoomNoAvailableRooms() {
        // Arrange
        hotel.setNumberOfAvailableRooms(0);
        Mockito.when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));

        // Act
        bookingService.bookRoom(1, user);
    }

    @Test
    public void testCancelBooking() {
        // Arrange
        Booking booking = new Booking(1, user, hotel, LocalDate.now());
        Mockito.when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));

        // Act
        bookingService.cancelBooking(1);

        // Assert
        assertEquals(11, hotel.getNumberOfAvailableRooms()); // Assuming hotel had 10 rooms before booking
        verify(hotelRepository).save(hotel);
        verify(bookingRepository).delete(booking);
    }
}

