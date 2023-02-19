package com.thanhson.food_app.models;

import lombok.Data;

@Data
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long size;

}