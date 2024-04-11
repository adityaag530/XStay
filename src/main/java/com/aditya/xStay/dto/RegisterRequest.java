package com.aditya.xStay.dto;
/*
 * @author adityagupta
 * @date 11/04/24
 */

import com.aditya.xStay.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
