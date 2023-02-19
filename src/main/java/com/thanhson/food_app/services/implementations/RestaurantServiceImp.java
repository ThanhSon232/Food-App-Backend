package com.thanhson.food_app.services.implementations;

import com.thanhson.food_app.mapper.ImageMapper;
import com.thanhson.food_app.mapper.RestaurantMapper;
import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.DTO.RestaurantDTO;
import com.thanhson.food_app.models.DTO.TypeDTO;
import com.thanhson.food_app.models.entity.AccountEntity;
import com.thanhson.food_app.models.entity.ImageEntity;
import com.thanhson.food_app.models.entity.RestaurantEntity;
import com.thanhson.food_app.models.exception.ResourceInvalidException;
import com.thanhson.food_app.repositories.AccountRepository;
import com.thanhson.food_app.repositories.ImageRepository;
import com.thanhson.food_app.repositories.RestaurantRepository;
import com.thanhson.food_app.repositories.TypeRepository;
import com.thanhson.food_app.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    private final AccountRepository accountRepository;

    private final TypeRepository typeRepository;

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;



    @Override
    public RestaurantDTO restaurantRegister(RestaurantDTO restaurantDTO) {
        Optional<AccountEntity> account = accountRepository.findAccountEntityByEmail(restaurantDTO.getEmail());
        if(!account.isPresent()) throw new ResourceInvalidException("Account is not existed.");
        RestaurantEntity restaurantEntity = restaurantMapper.dtoToEntity(restaurantDTO);
        restaurantEntity.setOwner(account.get());
        RestaurantDTO response = restaurantMapper.entityToDto(restaurantRepository.save(restaurantEntity));
        response.setEmail(account.get().getEmail());
        return response;
    }

//    @Override
//    public List<RestaurantDTO> getRestaurantByEmail(String email) {
//        Optional<AccountEntity> account = accountRepository.findAccountEntityByEmail(email);
//        if(!account.isPresent()) throw new ResourceInvalidException("Account is not existed.");
//        return restaurantRepository.getRestaurantEntitiesByOwner(account.get().getEmail()).stream().map(value -> {
//            RestaurantDTO restaurantDTO = restaurantMapper.entityToDto(value);
//            restaurantDTO.setEmail(email);
//            return restaurantDTO;
//        }).toList();
//    }
//
//
    @Override
    public List<RestaurantDTO> getFeaturedRestaurant(String type) {
        return restaurantRepository.findFeaturedRestaurantEntities(true, type).stream().map(value -> {
            RestaurantDTO restaurantDTO = restaurantMapper.entityToDto(value);
            Optional<List<ImageEntity>> imageEntities = imageRepository.findAllByRestaurant(value);
            List<ImageDTO> imageDTOS = new ArrayList<>();
            for(int i = 0 ; i < imageEntities.get().size() ; i ++){
                imageDTOS.add(imageMapper.entityToDto(imageEntities.get().get(i)));
            }
            restaurantDTO.setImageURL(imageDTOS);
            return restaurantDTO;
        }).toList();
    }

    @Override
    public List<RestaurantDTO> getRestaurantWithNameASC(String type) {
        return restaurantRepository.findRestaurantNameAsc(true, type).stream().map(value -> {
            RestaurantDTO restaurantDTO = restaurantMapper.entityToDto(value);
            Optional<List<ImageEntity>> imageEntities = imageRepository.findAllByRestaurant(value);
            List<ImageDTO> imageDTOS = new ArrayList<>();
            for(int i = 0 ; i < imageEntities.get().size() ; i ++){
                imageDTOS.add(imageMapper.entityToDto(imageEntities.get().get(i)));
            }
            restaurantDTO.setImageURL(imageDTOS);
            return restaurantDTO;
        }).toList();      }

    @Override
    public List<RestaurantDTO> getRestaurantWithNameDESC(String type) {
        return restaurantRepository.findRestaurantNameDesc(true, type).stream().map(value -> {
            RestaurantDTO restaurantDTO = restaurantMapper.entityToDto(value);
            Optional<List<ImageEntity>> imageEntities = imageRepository.findAllByRestaurant(value);
            List<ImageDTO> imageDTOS = new ArrayList<>();
            for(int i = 0 ; i < imageEntities.get().size() ; i ++){
                imageDTOS.add(imageMapper.entityToDto(imageEntities.get().get(i)));
            }
            restaurantDTO.setImageURL(imageDTOS);
            return restaurantDTO;
        }).toList();
    }

    @Override
    public List<RestaurantDTO> getRestaurantWithFilter(String type, String filter) {
        if(filter.equals("Popular")){
            return getFeaturedRestaurant(type);
        } else if(filter.equals("A-Z")){
            return getRestaurantWithNameASC(type);
        }
        System.out.println("DESC");
        return getRestaurantWithNameDESC(type);
    }
}
