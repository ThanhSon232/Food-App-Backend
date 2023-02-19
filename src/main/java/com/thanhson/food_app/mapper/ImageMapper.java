package com.thanhson.food_app.mapper;


import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.entity.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageEntity dtoToEntity (ImageDTO imageDTO);

    ImageDTO entityToDto (ImageEntity imageEntity);
}
