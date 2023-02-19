package com.thanhson.food_app.repositories;

import com.thanhson.food_app.models.entity.ExtraProductEntity;
import com.thanhson.food_app.models.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtraProductRepository extends JpaRepository<ExtraProductEntity, Long> {
    List<ExtraProductEntity> findAllByProduct(ProductEntity productEntity);
}
