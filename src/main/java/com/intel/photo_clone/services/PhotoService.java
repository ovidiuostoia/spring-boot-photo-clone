package com.intel.photo_clone.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.intel.photo_clone.models.Photo;
import com.intel.photo_clone.repository.PhotoRepository;

import jakarta.validation.Valid;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }   

    public Iterable<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhoto(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }

    public Photo addPhoto(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        
        this.photoRepository.save(photo);
        return photo;
    }

    public Photo updatePhoto(Integer id, @Valid Photo updatedPhoto) {
        Photo photo = this.photoRepository.findById(id).orElse(null);
        if (photo == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        photo.setFileName(updatedPhoto.getFileName());
        photo.setContentType(updatedPhoto.getContentType());
        photo.setData(updatedPhoto.getData());

        this.photoRepository.save(photo);
        return photo;
    }
    
}
