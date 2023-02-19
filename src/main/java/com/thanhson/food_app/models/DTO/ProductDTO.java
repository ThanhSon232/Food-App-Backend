package com.thanhson.food_app.models.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    Long Id;
    String name;
    TypeDTO category;
    int quantity;
    double rateRatio;
    int numRate;
    String description;
    List<ImageDTO> image;
    String restaurantName;
    int price;
}
