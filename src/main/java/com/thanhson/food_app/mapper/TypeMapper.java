package com.thanhson.food_app.mapper;

import com.thanhson.food_app.models.DTO.TypeDTO;
import com.thanhson.food_app.models.entity.TypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeDTO entityToDto (TypeEntity entity);

    TypeEntity dtoToEntity (TypeDTO typeDTO);

}
