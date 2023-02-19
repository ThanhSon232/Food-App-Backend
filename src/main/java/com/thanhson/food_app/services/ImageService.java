package com.thanhson.food_app.services;

import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.entity.ExtraProductEntity;
import com.thanhson.food_app.models.entity.ProductEntity;
import com.thanhson.food_app.models.entity.RestaurantEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    String uploadImage(MultipartFile file, String restaurantId) throws IOException;

    String uploadImageProfile(MultipartFile file, String accountId) throws IOException;

    String uploadImageProduct(MultipartFile file, String id);

    String uploadImageExtraProduct(MultipartFile file, String id);

    byte[] downloadImage(String fileName);

    List<ImageDTO> getImageByExtraProduct(ExtraProductEntity extraProduct);

    List<ImageDTO> getImageByProduct(ProductEntity productEntity);
}
