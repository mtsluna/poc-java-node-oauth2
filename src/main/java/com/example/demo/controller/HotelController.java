package com.example.demo.controller;

import com.example.demo.dto.HotelDto;
import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping(path = "/")
    public ResponseEntity<Hotel> post(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.save(hotel));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Hotel> put(@PathVariable String id, @RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.update(id, hotel));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Hotel> getOne(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findOne(id));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<HotelDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable String id){
        hotelService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @DeleteMapping(path = "")
    public ResponseEntity<?> deleteAll(){
        hotelService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}
