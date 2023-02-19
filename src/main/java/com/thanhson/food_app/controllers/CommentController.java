package com.thanhson.food_app.controllers;

import com.thanhson.food_app.models.ResponseObject;
import com.thanhson.food_app.models.request.CommentRequest;
import com.thanhson.food_app.services.CommentService;
import com.thanhson.food_app.services.implementations.CommentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImp commentServiceImp;

    @PostMapping(path = "/restaurant")
    private ResponseEntity<ResponseObject> addCommentRestaurant(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",commentServiceImp.addCommentRestaurant(commentRequest)));
    }

    @PostMapping(path = "/product")
    private ResponseEntity<ResponseObject> addCommentProduct(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",commentServiceImp.addCommentProduct(commentRequest)));
    }
}
