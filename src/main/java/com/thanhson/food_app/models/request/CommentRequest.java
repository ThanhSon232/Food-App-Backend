package com.thanhson.food_app.models.request;

import com.thanhson.food_app.models.DTO.AccountDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentRequest {
    String content;
    String date;
    double rate;
    Long accountId;
    Long restaurantID;
    Long productID;
}
