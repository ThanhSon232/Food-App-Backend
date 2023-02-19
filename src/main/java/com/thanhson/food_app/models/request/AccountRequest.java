package com.thanhson.food_app.models.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequest {
    private String fullName;
    private String email;
    private String password;
    private boolean isSeller;
    String otp;
}
