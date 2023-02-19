package com.thanhson.food_app.controllers;

import com.thanhson.food_app.models.DTO.AccountDTO;
import com.thanhson.food_app.models.ResponseObject;
import com.thanhson.food_app.models.request.AccountRequest;
import com.thanhson.food_app.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    @PostMapping("/register")
    ResponseEntity<ResponseObject> register(@RequestBody AccountRequest accountDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",accountService.register(accountDTO)));
    }

    @PostMapping("/validate")
    ResponseEntity<ResponseObject> validateOTP(@RequestBody AccountRequest accountDTO) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.value(),"success",accountService.validateOTP(accountDTO)));
    }

    @PostMapping("/login")
    ResponseEntity<ResponseObject> login(@RequestBody AccountRequest accountDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.value(),"success",accountService.login(accountDTO)));
    }
}
