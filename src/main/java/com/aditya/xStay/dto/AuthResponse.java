package com.aditya.xStay.dto;

import lombok.Builder;
import lombok.Data;

/*
 * @author adityagupta
 * @date 11/04/24
 */
@Builder
@Data
public class AuthResponse {
    private final String message = "Success";
    private final String accessToken;
}
