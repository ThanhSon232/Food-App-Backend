package com.thanhson.food_app.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ImageEntity> imageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<CommentEntity> comments;
    private boolean isSeller;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<RestaurantEntity> restaurants;


    @Override
    public String toString() {
        return "AccountEntity{" +
                "Id=" + Id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", imageUrl=" + imageUrl +
                ", isSeller=" + isSeller +
                ", restaurants=" + restaurants +
                '}';
    }
}
