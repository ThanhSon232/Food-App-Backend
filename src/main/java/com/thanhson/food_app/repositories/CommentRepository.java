package com.thanhson.food_app.repositories;

import com.thanhson.food_app.models.entity.CommentEntity;
import com.thanhson.food_app.models.entity.ProductEntity;
import com.thanhson.food_app.models.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    public int countAllByRestaurantAndRate(RestaurantEntity restaurant, double rate);

    public int countAllByProductAndRate(ProductEntity restaurant, double rate);

}
