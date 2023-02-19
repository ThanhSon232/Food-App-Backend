package com.thanhson.food_app.mapper;

import com.thanhson.food_app.models.DTO.ExtraFoodDTO;
import com.thanhson.food_app.models.entity.ExtraProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtraFoodMapper {
    ExtraFoodDTO entityToDto(ExtraProductEntity entity);

    ExtraProductEntity dtoToEntity(ExtraFoodDTO extraFoodDTO);
}
