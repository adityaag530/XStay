package com.aditya.xStay.repository;
/*
 * @author adityagupta
 * @date 12/04/24
 */

import com.aditya.xStay.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
