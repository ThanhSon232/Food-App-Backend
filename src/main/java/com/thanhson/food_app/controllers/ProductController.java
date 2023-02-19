package com.thanhson.food_app.controllers;

import com.thanhson.food_app.models.DTO.ProductDTO;
import com.thanhson.food_app.models.DTO.RestaurantDTO;
import com.thanhson.food_app.models.ResponseObject;
import com.thanhson.food_app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",productService.addProduct(productDTO)));
    }

    @GetMapping("/getProductDetail")
    ResponseEntity<ResponseObject> getProductDetail(String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",productService.getProductDetail(id)));
    }

    @GetMapping("/getPopularItems")
    ResponseEntity<ResponseObject> getPopularItems(String type){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",productService.getPopularItems(type)));
    }

    @GetMapping("/getAllProduct")
    ResponseEntity<ResponseObject> getPopularItems(String type, String filter){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",productService.getItemsWithFilter(type, filter)));
    }

}
