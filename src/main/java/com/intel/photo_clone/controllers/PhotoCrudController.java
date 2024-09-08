package com.intel.photo_clone.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.intel.photo_clone.models.Photo;
import com.intel.photo_clone.services.PhotoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class PhotoCrudController {

    private final PhotoService photoService;

    public PhotoCrudController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/photos")
    public Iterable<Photo> getPhotos() {
        return this.photoService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable Integer id) {
        Photo photo = this.photoService.getPhoto(id);
        if (photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photo;
    }
    
    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable Integer id) {
        this.photoService.deletePhoto(id);
    }

    @PostMapping("/photos")
    public Photo addPhoto(@RequestPart("data") MultipartFile file ) throws IOException {
        return this.photoService.addPhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

    @PutMapping("/photos/{id}")
    public Photo updatePhoto(@PathVariable Integer id, @RequestBody @Valid Photo updatedPhoto) {
        return this.photoService.updatePhoto(id, updatedPhoto);
    }
    
}
