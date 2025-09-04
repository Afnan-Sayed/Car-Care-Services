package com.ccs.Models.DTOs;

import lombok.Data;

@Data
public class ReviewCreateRequest {
    private int rating;
    private String comment;
}

