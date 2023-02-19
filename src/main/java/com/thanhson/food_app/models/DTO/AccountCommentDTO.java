package com.thanhson.food_app.models.DTO;

import com.thanhson.food_app.models.entity.ImageEntity;
import lombok.Data;

import java.util.List;

@Data
public class AccountCommentDTO {
    String email;
    List<ImageEntity> imageURL;
}
