package com.thanhson.food_app.models.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountDTO implements Serializable {
    private Long Id;
    private String fullName;
    private String email;
    private List<ImageDTO> imageURL;
    private boolean isSeller;
}
