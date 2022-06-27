package com.restapi.springbootrestapi.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo {
    @Id
    private String id;

    public String getTitle() {
        return title;
    }

    public Binary getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    private String title;

    private Binary image;

    public String getId() {
        return id;
    }
}