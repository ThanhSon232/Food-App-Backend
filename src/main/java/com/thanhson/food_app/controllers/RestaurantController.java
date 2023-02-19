package com.thanhson.food_app.controllers;

import com.thanhson.food_app.models.DTO.AccountDTO;
import com.thanhson.food_app.models.DTO.RestaurantDTO;
import com.thanhson.food_app.models.ResponseObject;
import com.thanhson.food_app.services.ImageService;
import com.thanhson.food_app.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/add")
    ResponseEntity<ResponseObject> register(@RequestBody RestaurantDTO restaurantDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",restaurantService.restaurantRegister(restaurantDTO)));
    }

//    @GetMapping("/get")
//    ResponseEntity<ResponseObject> getRestaurant(String email)  {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new ResponseObject(HttpStatus.OK.value(),"success",restaurantService.getRestaurantByEmail(email)));
//    }
//
    @GetMapping("/getFeatured")
    ResponseEntity<ResponseObject> getFeaturedRestaurant(String type)  {
        System.out.println(type);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",restaurantService.getFeaturedRestaurant(type)));
    }

    @GetMapping("/getAllRestaurant")
    ResponseEntity<ResponseObject> getAllRestaurant(String type, String filter)  {
        System.out.println(type);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",restaurantService.getRestaurantWithFilter(type, filter)));
    }
}
