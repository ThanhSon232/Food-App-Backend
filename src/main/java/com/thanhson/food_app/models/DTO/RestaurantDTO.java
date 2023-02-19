package com.thanhson.food_app.models.DTO;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class RestaurantDTO implements Serializable {
    private List<ImageDTO> imageURL;
    private double rateRatio;
    private int numRate;
    private boolean isVerified;
    private Set<TypeDTO> types;
    private boolean isFreeShipping;
    private String name;
    private String email;
    private long Id;
}
