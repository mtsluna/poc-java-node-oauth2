package com.example.demo.service.impl;

import com.example.demo.dto.HotelDto;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl extends HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Hotel save(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public Hotel update(String id, Hotel hotel){
        hotel.set_id(id);
        return hotelRepository.save(hotel);
    }

    public List<HotelDto> findAll(){

        List<Hotel> hotels = hotelRepository.findAll();

        return hotels
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    public Hotel findOne(String id){
        return hotelRepository.findById(id).get();
    }

    public void deleteOne(String id){
        this.hotelRepository.deleteById(id);
    }

    public void deleteAll(){
        this.hotelRepository.deleteAll();
    }

}
