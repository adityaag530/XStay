package com.aditya.xStay.repository;
/*
 * @author adityagupta
 * @date 11/04/24
 */

import com.aditya.xStay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String username);
}
