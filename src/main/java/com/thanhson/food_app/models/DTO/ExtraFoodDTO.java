package com.thanhson.food_app.models.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ExtraFoodDTO {
    Long Id;
    String name;
    double price;
    int quantity;
    List<ImageDTO> imageURL;
}
