package com.thanhson.food_app.services;

import com.thanhson.food_app.models.DTO.ExtraFoodDTO;
import com.thanhson.food_app.models.request.ExtraProductRequest;

import java.util.List;

public interface ExtraProductService {
    ExtraFoodDTO addExtraFood(ExtraProductRequest extraProductRequest);

    List<ExtraFoodDTO> getExtraFood(String Id);

}
