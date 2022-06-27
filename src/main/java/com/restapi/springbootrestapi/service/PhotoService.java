package com.restapi.springbootrestapi.service;


import com.restapi.springbootrestapi.models.Photo;
import com.restapi.springbootrestapi.repositories.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;


    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo.setTitle(title);
        photo = photoRepo.insert(photo);
        return photo.getId();
    }

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }





    public List<Photo> getAll(){
        return photoRepo.findAll();
    }
}
