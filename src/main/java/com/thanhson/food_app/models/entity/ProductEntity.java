package com.thanhson.food_app.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long Id;
    String name;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category", referencedColumnName = "Id")
    private TypeEntity category;

    int quantity;
    double rateRatio;
    int numRate;
    String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ImageEntity> imageUrl;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ExtraProductEntity> extraProductEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<CommentEntity> commentEntities;

    int price;
}
