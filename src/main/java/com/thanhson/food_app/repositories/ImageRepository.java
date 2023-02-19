package com.thanhson.food_app.repositories;


import com.thanhson.food_app.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    Optional<ImageEntity> findByName(String fileName);

    Optional<List<ImageEntity>> findAllByProduct(ProductEntity productEntity);

    Optional<List<ImageEntity>> findAllByUser(AccountEntity Id);


    Optional<List<ImageEntity>> findAllByRestaurant(RestaurantEntity Id);

    Optional<List<ImageEntity>> findAllByExtraFood(ExtraProductEntity Id);
}
