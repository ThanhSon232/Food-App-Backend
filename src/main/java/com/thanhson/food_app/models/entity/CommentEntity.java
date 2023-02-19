package com.thanhson.food_app.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    Long Id;

    String content;

    double rate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.Id")
    private AccountEntity user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product.Id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant.Id")
    private RestaurantEntity restaurant;
}
