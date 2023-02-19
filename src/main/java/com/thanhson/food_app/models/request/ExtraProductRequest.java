package com.thanhson.food_app.models.request;

import com.thanhson.food_app.models.DTO.ExtraFoodDTO;
import lombok.Data;

@Data
public class ExtraProductRequest {
    String name;
    double price;
    int quantity;
    Long productID;
}
