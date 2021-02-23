package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDto {

    private String name;
    private int stars;
    private List<ServiceDto> services;

}
