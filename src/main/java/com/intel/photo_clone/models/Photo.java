package com.intel.photo_clone.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

@Table("PHOTOS")
public class Photo {
    @Id
    private Integer id;

    @NotEmpty 
    private String fileName;
    private String contentType;
    
    @JsonIgnore 
    private byte[] data;

    public Photo() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return this.id + " " + this.fileName;
    }
    
}
