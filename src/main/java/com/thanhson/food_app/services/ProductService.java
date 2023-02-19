package com.thanhson.food_app.services;

import com.thanhson.food_app.models.DTO.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);

    ProductDTO getProductDetail(String id);

    List<ProductDTO> getPopularItems(String type);

    List<ProductDTO> getItemWithNameAsc(String type);
    List<ProductDTO> getItemWithNameDesc(String type);
    List<ProductDTO> getItemWithPriceAsc(String type);
    List<ProductDTO> getItemWithPriceDesc(String type);

    List<ProductDTO> getItemsWithFilter(String type, String filter);
}
