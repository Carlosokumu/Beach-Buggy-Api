package com.restapi.springbootrestapi.controllers;


import com.restapi.springbootrestapi.models.LoadFile;
import com.restapi.springbootrestapi.models.Photo;
import com.restapi.springbootrestapi.service.FileService;
import com.restapi.springbootrestapi.service.PhotoService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class PhotoController {

        @Autowired
        private FileService fileService;

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

//        @GetMapping("/photos/{id}")
//        public Photo getPhoto(@PathVariable String id, Model model) {
//                AnnotationConfigApplicationContext context
//                        = new AnnotationConfigApplicationContext();
//                context.scan("com.restapi.springbootrestapi");
//
//                context.refresh();
//
//                PhotoService photoService
//                        = context.getBean(PhotoService.class);
//                Photo photo = photoService.getPhoto(id);
//                model.addAttribute("title", photo.getTitle());
//                model.addAttribute("image",
//                        Base64.getEncoder().encodeToString(photo.getImage().getData()));
//
//                return photo.getImage();
//        }



        @PostMapping("/upload")
        public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
                return new ResponseEntity<>(fileService.addFile(file), HttpStatus.OK);
        }

        @GetMapping("/download/{id}")
        public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
                LoadFile loadFile = fileService.downloadFile(id);

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                        .body(new ByteArrayResource(loadFile.getFile()));
        }



}

