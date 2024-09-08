package com.intel.photo_clone.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.intel.photo_clone.models.Photo;
import com.intel.photo_clone.services.PhotoService;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PhotoDownloadController {

    private final PhotoService photoService;

    public PhotoDownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable Integer id) {
        Photo photo = this.photoService.getPhoto(id);
        if (photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        headers.setContentLength(data.length);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(photo.getFileName()).build(); // this will download the file
        // ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename(photo.getFileName()).build(); // this will open the file in the browser
        headers.setContentDisposition(contentDisposition);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
    
    
}
