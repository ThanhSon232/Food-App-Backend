package com.thanhson.food_app.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant.Id")
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.Id")
    private AccountEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product.Id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extraFood.Id")
    private ExtraProductEntity extraFood;

//    @Lob
//    @Column(length = 4000)
//    @Column(columnDefinition="bytea")
    private byte[] imageData;
}
