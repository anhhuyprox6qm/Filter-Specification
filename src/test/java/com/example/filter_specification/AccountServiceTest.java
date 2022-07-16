package com.example.filter_specification;

import com.example.filter_specification.entity.Credential;
import com.example.filter_specification.entity.dto.AccountLoginDto;
import com.example.filter_specification.entity.dto.AccountRegisterDto;
import com.example.filter_specification.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = T2009m1SpringSecurityApplication.class)
public class AccountServiceTest {
    @Autowired
    AccountService accountService;
    @Test
    void register(){
        AccountRegisterDto accountRegisterDto = AccountRegisterDto();
        accountRegisterDto.setUsername("quanghuy");
        accountRegisterDto.setPassword("123456");
        accountRegisterDto.setRole(1);
        AccountRegisterDto afterCreate = accountService.register(accountRegisterDto);
        System.out.println(afterCreate);
    }
    @Test
    void login(){
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        accountLoginDto.setUsername("quanghuy");
        accountLoginDto.setPassword("123456");
        Credential credential = accountService.login(accountLoginDto);
        System.out.println(credential.toString());
    }
}
