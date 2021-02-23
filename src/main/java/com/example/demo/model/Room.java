package com.example.demo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Room {

    public Room(){
        this._id = ObjectId.get().toString();
    }

    @Id
    private String _id;
    private String name;
    private String description;

}
