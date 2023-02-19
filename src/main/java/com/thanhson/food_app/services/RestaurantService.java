package com.thanhson.food_app.services;

import com.thanhson.food_app.models.DTO.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO restaurantRegister(RestaurantDTO restaurantDTO);

//    List<RestaurantDTO> getRestaurantByEmail(String email);
//
    List<RestaurantDTO> getFeaturedRestaurant(String type);

    List<RestaurantDTO> getRestaurantWithNameASC(String type);

    List<RestaurantDTO> getRestaurantWithNameDESC(String type);

    List<RestaurantDTO> getRestaurantWithFilter(String type, String filter);
}
