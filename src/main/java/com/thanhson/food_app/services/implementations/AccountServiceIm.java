package com.thanhson.food_app.services.implementations;

import com.thanhson.food_app.mapper.AccountMapper;
import com.thanhson.food_app.mapper.ImageMapper;
import com.thanhson.food_app.models.DTO.AccountDTO;
import com.thanhson.food_app.models.DTO.ImageDTO;
import com.thanhson.food_app.models.entity.AccountEntity;
import com.thanhson.food_app.models.entity.ImageEntity;
import com.thanhson.food_app.models.exception.ResourceInvalidException;
import com.thanhson.food_app.models.request.AccountRequest;
import com.thanhson.food_app.repositories.AccountRepository;
import com.thanhson.food_app.repositories.ImageRepository;
import com.thanhson.food_app.services.AccountService;
import com.thanhson.food_app.utils.EmailUtils;
import com.thanhson.food_app.utils.RandomClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountServiceIm implements AccountService {
    private final AccountRepository accountRepository;
    private Map<String, String> cacheOTP = new HashMap<>();

    private final EmailUtils emailUtils;

    private final AccountMapper accountMapper;

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;


    @Override
    public AccountRequest register(AccountRequest accountDTO) throws Exception {
        Optional<AccountEntity> result = accountRepository.findAccountEntityByEmail(accountDTO.getEmail());
        if (result.isPresent()) throw new ResourceInvalidException("Email is existed. Try another one");
        else {
            Integer otp = RandomClass.randomNumber();
            cacheOTP.put(accountDTO.getEmail(), otp.toString());
            emailUtils.sendMail("your otp is: " + otp, accountDTO.getEmail(), "REGISTER");
            return accountDTO;
        }
    }

    @Override
    public String validateOTP(AccountRequest accountDTO) {
        System.out.println(accountDTO);
        Optional<AccountEntity> result = accountRepository.findAccountEntityByEmail(accountDTO.getEmail());
        if (result.isPresent()) throw new ResourceInvalidException("Email is existed. Try another one");
        if (!cacheOTP.containsKey(accountDTO.getEmail())) {
            throw new ResourceInvalidException("account is invalid");
        }
        String email = cacheOTP.get(accountDTO.getEmail());
        String otp = accountDTO.getOtp();
        if (!Objects.equals(otp,email)) throw new ResourceInvalidException("otp invalid");
        else {
            AccountEntity userEntity = new AccountEntity();
            userEntity.setEmail(accountDTO.getEmail());
            userEntity.setPassword(accountDTO.getPassword());
            userEntity.setFullName(accountDTO.getFullName());
            accountRepository.save(userEntity);
            cacheOTP.remove(accountDTO.getEmail());
            return accountDTO.getEmail();
        }

    }

    @Override
    public AccountDTO login(AccountRequest accountDTO) {
        AccountEntity userEntity = accountRepository.findAccountEntityByEmailAndPassword(accountDTO.getEmail(), accountDTO.getPassword()).orElseThrow(() -> {
            throw new ResourceInvalidException("Either email or password is incorrect");
        });
        return getAccountDTO(userEntity);
    }

    @Override
    public AccountDTO getInfo(Long Id) {
        AccountEntity userEntity = accountRepository.findById(Id).orElseThrow(() -> {
            throw new ResourceInvalidException("Account is not existed");
        });
        return getAccountDTO(userEntity);
    }

    private AccountDTO getAccountDTO(AccountEntity userEntity) {
        Optional<List<ImageEntity>> image = imageRepository.findAllByUser(userEntity);
        List<ImageDTO> imageDTOS = new ArrayList<>();
        for(int i = 0 ; i < image.get().size() ; i++){
            imageDTOS.add(imageMapper.entityToDto(image.get().get(i)));
        }
        AccountDTO user = accountMapper.entityToDto(userEntity);
        user.setImageURL(imageDTOS);
        return user;
    }


}
