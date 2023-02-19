package com.thanhson.food_app.services.implementations;

import com.thanhson.food_app.mapper.ExtraFoodMapper;
import com.thanhson.food_app.models.DTO.ExtraFoodDTO;
import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.entity.ExtraProductEntity;
import com.thanhson.food_app.models.entity.ProductEntity;
import com.thanhson.food_app.models.exception.ResourceInvalidException;
import com.thanhson.food_app.models.request.ExtraProductRequest;
import com.thanhson.food_app.repositories.ExtraProductRepository;
import com.thanhson.food_app.repositories.ProductRepository;
import com.thanhson.food_app.services.ExtraProductService;
import com.thanhson.food_app.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExtraProductServiceImp implements ExtraProductService {
    final private ExtraProductRepository extraProductRepository;
    final private ProductRepository productRepository;

    final private ExtraFoodMapper extraFoodMapper;

    final private ImageService imageService;

    @Override
    public ExtraFoodDTO addExtraFood(ExtraProductRequest extraProductRequest) {
        Optional<ProductEntity> product = productRepository.findById(extraProductRequest.getProductID());
        if(!product.isPresent()) throw new ResourceInvalidException("Product is not existed");
        ExtraProductEntity entity = new ExtraProductEntity();
        entity.setProduct(product.get());
        entity.setName(extraProductRequest.getName());
        entity.setPrice(extraProductRequest.getPrice());
        entity.setQuantity(extraProductRequest.getQuantity());
        return extraFoodMapper.entityToDto(extraProductRepository.save(entity));
    }

    @Override
    public List<ExtraFoodDTO> getExtraFood(String Id) {
        Optional<ProductEntity> product = productRepository.findById(Long.valueOf(Id));
        if(!product.isPresent()) throw new ResourceInvalidException("Product is not existed");
        return extraProductRepository.findAllByProduct(product.get()).stream().map(value -> {
            List<ImageDTO> imageDTOS = imageService.getImageByExtraProduct(value);
            ExtraFoodDTO extraFoodDTO = extraFoodMapper.entityToDto(value);
            extraFoodDTO.setImageURL(imageDTOS);
            return extraFoodDTO;
        }).toList();
    }

}
