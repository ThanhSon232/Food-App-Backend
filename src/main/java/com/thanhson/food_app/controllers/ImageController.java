package com.thanhson.food_app.controllers;

import com.thanhson.food_app.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/image")
@RequiredArgsConstructor
public class ImageController {
    final private ImageService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("restaurantId") String restaurantId) throws IOException {
        String uploadImage = service.uploadImage(file, restaurantId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PostMapping(path = "/profile")
    public ResponseEntity<?> uploadImageProfile(@RequestParam("image") MultipartFile file, @RequestParam("account") String account) throws IOException {
        String uploadImage = service.uploadImageProfile(file, account);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PostMapping(path = "/product")
    public ResponseEntity<?> uploadImageProduct(@RequestParam("image") MultipartFile file, @RequestParam("product") String productId) throws IOException {
        String uploadImage = service.uploadImageProduct(file, productId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PostMapping(path = "/extraProduct")
    public ResponseEntity<?> uploadImageExtraProduct(@RequestParam("image") MultipartFile file, @RequestParam("extraFood") String extraFoodId) {
        String uploadImage = service.uploadImageExtraProduct(file, extraFoodId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }


    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

}
