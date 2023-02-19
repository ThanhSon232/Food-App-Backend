package com.thanhson.food_app.models.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDTO implements Serializable {
    private String name;
    private String type;
}
