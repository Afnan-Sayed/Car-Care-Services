package com.ccs.Models.DTOs;

import lombok.Data;

@Data
public class OrderCreateRequest {
    private Long carId;
    private Long serviceDetailId;
    private double latitude;
    private double longitude;
}

