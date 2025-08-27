package com.ccs.Models.DTOs;

import lombok.Data;

@Data
public class ProviderSignupRequestDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private Double locationLat;
    private Double locationLong;
    private String nationalIdImage;

}