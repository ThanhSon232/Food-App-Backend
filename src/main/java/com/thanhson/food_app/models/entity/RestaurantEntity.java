package com.thanhson.food_app.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;


    private double rateRatio;
    private int numRate;
    private boolean verified;
    private boolean freeShipping;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ImageEntity> imageUrl;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<CommentEntity> comments;

    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner.Id")
    private AccountEntity owner;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_type", joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "Id"), inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "Id"))
    @JsonManagedReference
    private Set<TypeEntity> types;

    @OneToMany (mappedBy = "restaurant",targetEntity = ProductEntity.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "createdBy", referencedColumnName = "Id")
    private List<ProductEntity> createdBy;


}
