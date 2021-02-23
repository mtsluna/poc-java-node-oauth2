package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Audit {

    public Date createdAt = new Date();
    public Date updatedAt = new Date();

}
