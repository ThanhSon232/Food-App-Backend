package com.thanhson.food_app.models.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    Long Id;
    String content;
    LocalDateTime createOn;
    double rate;
    AccountDTO account;
}
