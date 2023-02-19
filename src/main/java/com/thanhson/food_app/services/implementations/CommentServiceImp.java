package com.thanhson.food_app.services.implementations;

import com.thanhson.food_app.mapper.AccountMapper;
import com.thanhson.food_app.mapper.CommentMapper;
import com.thanhson.food_app.models.DTO.CommentDTO;
import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.entity.*;
import com.thanhson.food_app.models.exception.ResourceInvalidException;
import com.thanhson.food_app.models.request.CommentRequest;
import com.thanhson.food_app.repositories.*;
import com.thanhson.food_app.services.AccountService;
import com.thanhson.food_app.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final RestaurantRepository restaurantRepository;

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    private final AccountService accountService;



    @Override
    public CommentDTO addCommentRestaurant(CommentRequest commentRequest) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(commentRequest.getRestaurantID());
        Optional<AccountEntity> account = accountRepository.findById(commentRequest.getAccountId());
        if(!account.isPresent()) throw new ResourceInvalidException("Account is not existed");
        if(!restaurant.isPresent()) throw new ResourceInvalidException("Restaurant is not existed");
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setRestaurant(restaurant.get());
        commentEntity.setUser(account.get());
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setRate(commentRequest.getRate());
        commentEntity.setCreateOn(LocalDateTime.now());
        CommentDTO commentDTO = commentMapper.entityToDto(commentRepository.save(commentEntity));
        double rateRatio = 0;
        for(int i = 1; i<=5 ;i++){
            int value = commentRepository.countAllByRestaurantAndRate(restaurant.get(), i);
            rateRatio += i*value;
        }

        RestaurantEntity restaurantEntity = restaurant.get();
        restaurantEntity.setNumRate(restaurantEntity.getNumRate()+1);
        restaurantEntity.setRateRatio(rateRatio/restaurantEntity.getNumRate());
        restaurantRepository.save(restaurantEntity);

        commentDTO.setAccount(accountService.getInfo(account.get().getId()));
        return commentDTO;
    }

    @Override
    public CommentDTO addCommentProduct(CommentRequest commentRequest) {
        Optional<ProductEntity> product = productRepository.findById(commentRequest.getProductID());
        Optional<AccountEntity> account = accountRepository.findById(commentRequest.getAccountId());
        if(!account.isPresent()) throw new ResourceInvalidException("Account is not existed");
        if(!product.isPresent()) throw new ResourceInvalidException("Restaurant is not existed");
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setProduct(product.get());
        commentEntity.setUser(account.get());
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setRate(commentRequest.getRate());
        commentEntity.setCreateOn(LocalDateTime.now());
        CommentDTO commentDTO = commentMapper.entityToDto(commentRepository.save(commentEntity));
        double rateRatio = 0;
        for(int i = 1; i<=5 ;i++){
            int value = commentRepository.countAllByProductAndRate(product.get(), i);
            rateRatio += i*value;
        }

        ProductEntity productEntity = product.get();
        productEntity.setNumRate(productEntity.getNumRate()+1);
        productEntity.setRateRatio(rateRatio/productEntity.getNumRate());
        productRepository.save(productEntity);

        commentDTO.setAccount(accountService.getInfo(account.get().getId()));
        return commentDTO;
    }
}
