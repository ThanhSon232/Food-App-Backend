package com.thanhson.food_app.services.implementations;

import com.thanhson.food_app.mapper.ImageMapper;
import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.entity.*;
import com.thanhson.food_app.models.exception.ResourceInvalidException;
import com.thanhson.food_app.repositories.*;
import com.thanhson.food_app.services.ImageService;
import com.thanhson.food_app.utils.ImageUtils;
import com.thanhson.food_app.utils.RandomClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {
    final private ImageRepository repository;
    final private RestaurantRepository restaurantRepository;

    final private AccountRepository accountRepository;

    final private ProductRepository productRepository;

    final private ExtraProductRepository extraProductRepository;

    final private ImageMapper imageMapper;


    @Override
    public String uploadImage(MultipartFile file, String restaurantId) {
        System.out.println(restaurantId);
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(Long.valueOf(restaurantId));
        if(!restaurant.isPresent()) throw new ResourceInvalidException("Restaurant is not existed.");
        try{
            ImageEntity image = repository.save(ImageEntity.builder()
                    .name(RandomClass.randomIdentifier())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes())).restaurant(restaurant.get()).build());
            return "file uploaded successfully : " + file.getOriginalFilename();
        } catch (IOException e) {
            throw new ResourceInvalidException(e.getMessage());
        }
    }

    @Override
    public String uploadImageProfile(MultipartFile file, String accountId) throws IOException {
        Optional<AccountEntity> account = accountRepository.findAccountEntityByEmail(accountId);
        if(account.isEmpty()) throw new ResourceInvalidException("Account is not existed.");
        try{
            ImageEntity image = repository.save(ImageEntity.builder()
                    .name(RandomClass.randomIdentifier())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes())).user(account.get()).build());
            return "file uploaded successfully : " + file.getOriginalFilename();
        } catch (IOException e) {
            throw new ResourceInvalidException(e.getMessage());
        }
    }

    @Override
    public String uploadImageProduct(MultipartFile file, String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(Long.valueOf(id));
        if(!productEntity.isPresent()) throw new ResourceInvalidException("Product is not existed");
        try{
            ImageEntity image = repository.save(ImageEntity.builder()
                    .name(RandomClass.randomIdentifier())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes())).product(productEntity.get()).build());
            return "file uploaded successfully : " + file.getOriginalFilename();
        } catch (IOException e) {
            throw new ResourceInvalidException(e.getMessage());
        }
    }

    @Override
    public String uploadImageExtraProduct(MultipartFile file, String id) {
        Optional<ExtraProductEntity> entity = extraProductRepository.findById(Long.valueOf(id));
        if(!entity.isPresent()) throw new ResourceInvalidException("Extra product is not existed");
        try{
            ImageEntity image = repository.save(ImageEntity.builder()
                    .name(RandomClass.randomIdentifier())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes())).extraFood(entity.get()).build());
            return "file uploaded successfully : " + file.getOriginalFilename();
        } catch (IOException e) {
            throw new ResourceInvalidException(e.getMessage());
        }
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageEntity> dbImageEntity = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageEntity.get().getImageData());
        return images;
    }

    @Override
    public List<ImageDTO> getImageByExtraProduct(ExtraProductEntity extraProduct) {
        Optional<List<ImageEntity>> imageEntities = repository.findAllByExtraFood(extraProduct);
        if (!imageEntities.isPresent()) throw new ResourceInvalidException("Can't find images");
        return imageEntities.get().stream().map(value -> imageMapper.entityToDto(value)).toList();
    }

    @Override
    public List<ImageDTO> getImageByProduct(ProductEntity productEntity) {
        Optional<List<ImageEntity>> imageEntities = repository.findAllByProduct(productEntity);
        if (!imageEntities.isPresent()) throw new ResourceInvalidException("Can't find images");
        return imageEntities.get().stream().map(value -> imageMapper.entityToDto(value)).toList();
    }
}
