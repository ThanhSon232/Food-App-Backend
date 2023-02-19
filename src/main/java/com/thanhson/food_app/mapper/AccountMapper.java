package com.thanhson.food_app.mapper;
import com.thanhson.food_app.models.DTO.AccountDTO;
import com.thanhson.food_app.models.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO entityToDto(AccountEntity accountEntity);
    AccountEntity dtoToEntity(AccountDTO accountDto);
}
