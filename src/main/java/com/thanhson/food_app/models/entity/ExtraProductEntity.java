package com.thanhson.food_app.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "extra_food")
public class ExtraProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String name;

    double price;

    int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product.Id")
    private ProductEntity product;

    @OneToMany(mappedBy = "extraFood", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ImageEntity> imageURL;
}
