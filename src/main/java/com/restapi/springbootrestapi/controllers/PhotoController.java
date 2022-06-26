package com.restapi.springbootrestapi.controllers;


import com.restapi.springbootrestapi.service.PhotoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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



}

