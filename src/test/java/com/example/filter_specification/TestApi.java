package com.example.filter_specification;

import com.example.filter_specification.entity.dto.AccountRegisterDto;
import com.example.filter_specification.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = T2009M1SpringSecurityApplication.class)
public class TestApi {
    @Autowired
    AccountService accountService;
    @Test
    public void register(){
        AccountRegisterDto accountRegisterDto = new AccountRegisterDto();
        accountRegisterDto.setUsername("huy");
        accountRegisterDto.setPassword("123456");
        accountRegisterDto.setRole(1);
        AccountRegisterDto afterCreate = accountService.register(accountRegisterDto);
        System.out.println(afterCreate);
    }
}
