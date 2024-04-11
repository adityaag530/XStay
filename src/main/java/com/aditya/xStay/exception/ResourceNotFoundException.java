package com.aditya.xStay.exception;
/*
 * @author adityagupta
 * @date 12/04/24
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String hotelNotFound) {
        super(hotelNotFound);
    }
}
