package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.model.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    void createHotel(Hotel hotel);
    void updateHotel(Integer id, Hotel updatedHotel);
    void deleteHotel(Integer id);
    Hotel getHotelById(Integer id);
}

