package com.thanhson.food_app.repositories;

import com.thanhson.food_app.models.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageEntity,Long> {
    Optional<ImageEntity> findByName(String fileName);
}
