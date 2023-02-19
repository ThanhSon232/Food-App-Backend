package com.thanhson.food_app.mapper;

import com.thanhson.food_app.models.DTO.RestaurantDTO;
import com.thanhson.food_app.models.entity.RestaurantEntity;
import com.thanhson.food_app.models.entity.TypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TypeMapper.class})
public interface RestaurantMapper {
    RestaurantDTO entityToDto (RestaurantEntity restaurantEntity);

    @Mapping(target = "owner", ignore = true)
    RestaurantEntity dtoToEntity (RestaurantDTO restaurantDTO);
}
