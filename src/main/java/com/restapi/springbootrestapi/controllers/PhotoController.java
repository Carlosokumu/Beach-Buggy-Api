package com.restapi.springbootrestapi.controllers;


import com.restapi.springbootrestapi.models.Photo;
import com.restapi.springbootrestapi.service.PhotoService;
import org.bson.types.Binary;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class PhotoController {



        @PostMapping("/photos/add")
        public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)  throws IOException {
                AnnotationConfigApplicationContext context
                        = new AnnotationConfigApplicationContext();
                context.scan("com.restapi.springbootrestapi");

                context.refresh();

                PhotoService photoService
                        = context.getBean(PhotoService.class);

                String id = photoService.addPhoto(title, image);
                return "redirect:/photos/" + id;
        }



        @GetMapping("/photos/all")
        public List<Photo> getAllPhotos() {
                AnnotationConfigApplicationContext context
                        = new AnnotationConfigApplicationContext();
                context.scan("com.restapi.springbootrestapi");

                context.refresh();

                PhotoService photoService
                        = context.getBean(PhotoService.class);
                return  photoService.getAll();
        }

        @GetMapping("/photos/{id}")
        public Photo getPhoto(@PathVariable String id, Model model) {
                AnnotationConfigApplicationContext context
                        = new AnnotationConfigApplicationContext();
                context.scan("com.restapi.springbootrestapi");

                context.refresh();

                PhotoService photoService
                        = context.getBean(PhotoService.class);
                Photo photo = photoService.getPhoto(id);
                model.addAttribute("title", photo.getTitle());
                model.addAttribute("image",
                        Base64.getEncoder().encodeToString(photo.getImage().getData()));

                return photo.getImage();
        }



}

