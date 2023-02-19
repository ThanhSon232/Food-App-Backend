package com.thanhson.food_app.models.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeDTO implements Serializable {
    long id;
    String name;
}
