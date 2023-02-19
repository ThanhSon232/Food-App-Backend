package com.thanhson.food_app.repositories;

import com.thanhson.food_app.models.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p from ProductEntity p inner join p.category t where t.name = ?1 order by p.name  DESC")
    List<ProductEntity> getProductEntitiesByNameDESC(String t);

    @Query("SELECT p from ProductEntity p inner join p.category t where t.name = ?1 order by p.name ASC ")
    List<ProductEntity> getProductEntitiesByNameASC(String t);

    @Query("SELECT p from ProductEntity p inner join p.category t where t.name = ?1 order by p.price ASC ")
    List<ProductEntity> getProductEntitiesByPriceASC(String t);

    @Query("SELECT p from ProductEntity p inner join p.category t where t.name = ?1 order by p.price DESC ")
    List<ProductEntity> getProductEntitiesByPriceDESC(String t);

    @Query("SELECT p from ProductEntity p inner join p.category t where t.name = ?1 order by p.rateRatio  DESC")
    List<ProductEntity> getPopularItems(String t);
}
