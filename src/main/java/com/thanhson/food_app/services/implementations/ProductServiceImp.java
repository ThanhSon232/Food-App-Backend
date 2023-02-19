package com.thanhson.food_app.services.implementations;

import com.thanhson.food_app.mapper.ImageMapper;
import com.thanhson.food_app.mapper.ProductMapper;
import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.DTO.ProductDTO;
import com.thanhson.food_app.models.entity.ImageEntity;
import com.thanhson.food_app.models.entity.ProductEntity;
import com.thanhson.food_app.models.entity.RestaurantEntity;
import com.thanhson.food_app.models.exception.ResourceInvalidException;
import com.thanhson.food_app.repositories.ImageRepository;
import com.thanhson.food_app.repositories.ProductRepository;
import com.thanhson.food_app.repositories.RestaurantRepository;
import com.thanhson.food_app.services.ImageService;
import com.thanhson.food_app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    private final ImageRepository imageRepository;

    private final ImageService imageService;

    private final ImageMapper imageMapper;


    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findByName(productDTO.getRestaurantName());
        if (!restaurant.isPresent()) throw new ResourceInvalidException("Restaurant is not existed");
        ProductEntity product = productMapper.dtoToEntity(productDTO);
        product.setRestaurant(restaurant.get());
        ProductDTO response = productMapper.entityToDto(productRepository.save(product));
        System.out.println(response);
        response.setRestaurantName(restaurant.get().getName());
        return response;
    }

    @Override
    public ProductDTO getProductDetail(String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(Long.valueOf(id));
        if (!productEntity.isPresent()) throw new ResourceInvalidException("Product is not existed");
        Optional<List<ImageEntity>> imageEntities = imageRepository.findAllByProduct(productEntity.get());
        List<ImageDTO> imageDTOS = new ArrayList<>();
        for (int i = 0; i < imageEntities.get().size(); i++) {
            imageDTOS.add(imageMapper.entityToDto(imageEntities.get().get(i)));
        }
        ProductDTO response = productMapper.entityToDto(productEntity.get());
        response.setRestaurantName(productEntity.get().getRestaurant().getName());
        response.setImage(imageDTOS);
        return response;
    }

    @Override
    public List<ProductDTO> getPopularItems(String type) {
        return productRepository.getPopularItems(type).stream().map(value -> {
            ProductDTO productDTO = productMapper.entityToDto(value);
            Optional<List<ImageEntity>> imageEntities = imageRepository.findAllByProduct(value);
            List<ImageDTO> imageDTOS = new ArrayList<>();
            for (int i = 0; i < imageEntities.get().size(); i++) {
                imageDTOS.add(imageMapper.entityToDto(imageEntities.get().get(i)));
            }
            productDTO.setImage(imageDTOS);
            return productDTO;
        }).toList();
    }

    @Override
    public List<ProductDTO> getItemWithNameAsc(String type) {
        return productRepository.getProductEntitiesByNameASC(type).stream().map(value -> {
            ProductDTO productDTO = productMapper.entityToDto(value);
            List<ImageDTO> imageDTOS = imageService.getImageByProduct(value);
            productDTO.setImage(imageDTOS);
            return productDTO;
        }).toList();
    }

    @Override
    public List<ProductDTO> getItemWithNameDesc(String type) {
        return productRepository.getProductEntitiesByNameDESC(type).stream().map(value -> {
            ProductDTO productDTO = productMapper.entityToDto(value);
            List<ImageDTO> imageDTOS = imageService.getImageByProduct(value);
            productDTO.setImage(imageDTOS);
            return productDTO;
        }).toList();
    }

    @Override
    public List<ProductDTO> getItemWithPriceAsc(String type) {
        return productRepository.getProductEntitiesByPriceASC(type).stream().map(value -> {
            ProductDTO productDTO = productMapper.entityToDto(value);
            List<ImageDTO> imageDTOS = imageService.getImageByProduct(value);
            productDTO.setImage(imageDTOS);
            return productDTO;
        }).toList();
    }

    @Override
    public List<ProductDTO> getItemWithPriceDesc(String type) {
        return productRepository.getProductEntitiesByPriceDESC(type).stream().map(value -> {
            ProductDTO productDTO = productMapper.entityToDto(value);
            List<ImageDTO> imageDTOS = imageService.getImageByProduct(value);
            productDTO.setImage(imageDTOS);
            return productDTO;
        }).toList();
    }

    @Override
    public List<ProductDTO> getItemsWithFilter(String type, String filter) {
        switch (filter){
            case "Popular":
                return getPopularItems(type);
            case "A-Z":
                return getItemWithNameAsc(type);
            case "Z-A":
                return getItemWithNameDesc(type);
            case "High to low":
                return getItemWithPriceDesc(type);
            default:
                return getItemWithPriceAsc(type);
        }
    }


}
