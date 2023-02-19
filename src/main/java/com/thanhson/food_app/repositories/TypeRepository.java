package com.thanhson.food_app.repositories;

import com.thanhson.food_app.models.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  TypeRepository  extends JpaRepository<TypeEntity,Long> {
    Optional<TypeEntity> findByName(String name);
}
