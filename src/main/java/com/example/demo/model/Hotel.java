package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Hotel extends Audit{

    @Id
    private String _id;
    private String name;
    private String description;
    private int stars;
    private List<Service> services;
    private List<Room> rooms;

}
