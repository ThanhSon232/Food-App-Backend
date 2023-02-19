package com.thanhson.food_app.mapper;

import com.thanhson.food_app.models.DTO.ProductDTO;
import com.thanhson.food_app.models.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO entityToDto(ProductEntity entity);

    ProductEntity dtoToEntity(ProductDTO productDTO);
}
