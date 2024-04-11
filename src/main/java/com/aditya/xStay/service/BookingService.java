package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.model.User;

public interface BookingService {
    void bookRoom(Integer hotelId, User user);
    void cancelBooking(Integer bookingId);
}

