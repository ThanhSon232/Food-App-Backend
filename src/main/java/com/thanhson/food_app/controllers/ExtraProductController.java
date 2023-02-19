package com.thanhson.food_app.controllers;

import com.thanhson.food_app.models.DTO.ProductDTO;
import com.thanhson.food_app.models.ResponseObject;
import com.thanhson.food_app.models.request.ExtraProductRequest;
import com.thanhson.food_app.services.ExtraProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/extraProduct")
@RequiredArgsConstructor
public class ExtraProductController {
    final private ExtraProductService extraProductService;

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addExtraProduct(@RequestBody ExtraProductRequest extraProductRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",extraProductService.addExtraFood(extraProductRequest)));
    }

    @GetMapping("/get")
    ResponseEntity<ResponseObject> getExtraProduct(String productId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",extraProductService.getExtraFood(productId)));
    }
}
