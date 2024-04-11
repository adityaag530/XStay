package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.exception.ResourceNotFoundException;
import com.aditya.xStay.model.Hotel;
import com.aditya.xStay.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
    }

    @Override
    public void createHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void updateHotel(Integer id, Hotel updatedHotel) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        existingHotel.setName(updatedHotel.getName());
        existingHotel.setLocation(updatedHotel.getLocation());
        existingHotel.setDescription(updatedHotel.getDescription());
        existingHotel.setNumberOfAvailableRooms(updatedHotel.getNumberOfAvailableRooms());
        hotelRepository.save(existingHotel);
    }

    @Override
    public void deleteHotel(Integer id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotelRepository.delete(hotel);
    }
}


