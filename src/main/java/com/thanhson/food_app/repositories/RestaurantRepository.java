package com.thanhson.food_app.repositories;

import com.thanhson.food_app.models.entity.RestaurantEntity;
import jakarta.persistence.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
//    @Query("SELECT r from RestaurantEntity r inner join r.owner u where u.email=?1")
//    List<RestaurantEntity> getRestaurantEntitiesByOwner(String email);
//

    @Query("SELECT r from RestaurantEntity r inner join r.types t where r.verified=?1 and t.name =?2 order by r.name desc")
    List<RestaurantEntity> findRestaurantNameDesc(boolean isVerified,String t);

    @Query("SELECT r from RestaurantEntity r inner join r.types t where r.verified=?1 and t.name =?2 order by r.name asc ")
    List<RestaurantEntity> findRestaurantNameAsc(boolean isVerified,String t);

    Optional<RestaurantEntity> findByName(String restaurant);
    @Query("SELECT r from RestaurantEntity r inner join r.types t where r.verified=?1 and t.name =?2 order by r.rateRatio desc")
    List<RestaurantEntity> findFeaturedRestaurantEntities(boolean isVerified,String t);
}
