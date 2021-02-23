package com.example.demo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Service {

    public Service(){
        this._id = ObjectId.get().toString();
    }

    @Id
    private String _id;
    private String name;

}
