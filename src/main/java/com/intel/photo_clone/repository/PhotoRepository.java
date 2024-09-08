package com.intel.photo_clone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intel.photo_clone.models.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    
    

}
