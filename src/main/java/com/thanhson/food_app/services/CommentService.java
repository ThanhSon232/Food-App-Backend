package com.thanhson.food_app.services;

import com.thanhson.food_app.models.DTO.CommentDTO;
import com.thanhson.food_app.models.request.CommentRequest;

public interface CommentService {
    public CommentDTO addCommentRestaurant(CommentRequest commentRequest);

    public CommentDTO addCommentProduct(CommentRequest commentRequest);
}
