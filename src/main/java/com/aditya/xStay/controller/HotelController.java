package com.aditya.xStay.controller;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.exception.ResourceNotFoundException;
import com.aditya.xStay.model.Hotel;
import com.aditya.xStay.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // Endpoint to retrieve all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Endpoint to retrieve a hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Integer id) {
        try {
            Hotel hotel = hotelService.getHotelById(id);
            return ResponseEntity.ok(hotel);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
        }
    }

    // Endpoint to create a hotel
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        hotelService.createHotel(hotel);
        return ResponseEntity.ok("Hotel created successfully");
    }

    // Endpoint to update a hotel
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public ResponseEntity<?> updateHotel(@PathVariable Integer id, @RequestBody Hotel updatedHotel) {
        try {
            hotelService.updateHotel(id, updatedHotel);
            return ResponseEntity.ok("Hotel updated successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
        }
    }

    // Endpoint to delete a hotel
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHotel(@PathVariable Integer id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.ok("Hotel deleted successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
        }
    }
}
