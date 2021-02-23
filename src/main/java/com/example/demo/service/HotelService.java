package com.example.demo.service;

import com.example.demo.dto.HotelDto;
import com.example.demo.model.Hotel;

import java.util.List;

public abstract class HotelService {

    public abstract Hotel save(Hotel hotel);

    public abstract Hotel update(String id, Hotel hotel);

    public abstract List<HotelDto> findAll();

    public abstract Hotel findOne(String id);

    public abstract void deleteOne(String id);

    public abstract void deleteAll();

}
