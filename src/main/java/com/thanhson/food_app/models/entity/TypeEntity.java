package com.thanhson.food_app.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "type")
public class TypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;

    private String name;

    @ManyToMany(mappedBy = "types", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RestaurantEntity> restaurant;

    @OneToOne(mappedBy = "category")
    private ProductEntity productEntity;

}
