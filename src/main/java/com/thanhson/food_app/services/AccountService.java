package com.thanhson.food_app.services;

import com.thanhson.food_app.models.DTO.AccountDTO;
import com.thanhson.food_app.models.request.AccountRequest;

public interface AccountService {
    AccountRequest register(AccountRequest accountDTO) throws Exception;

    String validateOTP(AccountRequest accountDTO);

    AccountDTO login(AccountRequest accountDTO);

    AccountDTO getInfo(Long Id);


}
