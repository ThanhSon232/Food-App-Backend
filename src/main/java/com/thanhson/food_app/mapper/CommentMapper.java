package com.thanhson.food_app.mapper;

import com.thanhson.food_app.models.DTO.CommentDTO;
import com.thanhson.food_app.models.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentEntity dtoToEntity(CommentDTO commentDTO);

    CommentDTO entityToDto(CommentEntity commentEntity);
}
