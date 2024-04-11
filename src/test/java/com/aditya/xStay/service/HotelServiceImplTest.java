package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.aditya.xStay.exception.ResourceNotFoundException;
import com.aditya.xStay.model.Hotel;
import com.aditya.xStay.repository.HotelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Hotel hotel1;
    private Hotel hotel2;

    @Before
    public void setUp() {
        hotel1 = new Hotel(1, "Hotel A", "Location A", "Description A", 10);
        hotel2 = new Hotel(2, "Hotel B", "Location B", "Description B", 15);
    }


    @Test
    public void testGetAllHotels() {
        // Arrange
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel1);
        hotels.add(hotel2);

        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);

        // Act
        List<Hotel> result = hotelService.getAllHotels();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testGetHotelById() {
        // Arrange
        Mockito.when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel1));

        // Act
        Hotel result = hotelService.getHotelById(1);

        // Assert
        assertEquals(hotel1, result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetHotelByIdNotFound() {
        // Arrange
        Mockito.when(hotelRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        hotelService.getHotelById(1);
    }

    @Test
    public void testCreateHotel() {
        // Arrange
        Hotel newHotel = new Hotel(null, "New Hotel", "Location C", "Description C", 20);

        // Act
        hotelService.createHotel(newHotel);

        // Assert
        verify(hotelRepository).save(newHotel);
    }

    @Test
    public void testUpdateHotel() {
        // Arrange
        Hotel updatedHotel = new Hotel(1, "Updated Hotel", "Updated Location", "Updated Description", 25);
        Mockito.when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel1));

        // Act
        hotelService.updateHotel(1, updatedHotel);

        // Assert
        assertEquals("Updated Hotel", hotel1.getName());
        assertEquals("Updated Location", hotel1.getLocation());
        assertEquals("Updated Description", hotel1.getDescription());
        assertEquals(25, hotel1.getNumberOfAvailableRooms());
        verify(hotelRepository).save(hotel1);
    }

    @Test
    public void testDeleteHotel() {
        // Arrange
        Mockito.when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel1));

        // Act
        hotelService.deleteHotel(1);

        // Assert
        verify(hotelRepository).delete(hotel1);
    }
}

