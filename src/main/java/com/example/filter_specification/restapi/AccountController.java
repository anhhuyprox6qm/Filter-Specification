package com.example.filter_specification.restapi;

import com.example.filter_specification.entity.dto.AccountRegisterDto;
import com.example.filter_specification.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AccountController {
    final AccountService accountService;
    @RequestMapping(path = "register",method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountRegisterDto accountRegisterDto){
        return ResponseEntity.ok().body(accountService.register(accountRegisterDto));
    }
    // @RequestMapping(path = "login", method = RequestMethod.POST)
    // public ResponseEntity<?> login(@RequestBOdy AccountLoginDto accountLoginDto){
    // return ResponseEntity.ok().body(accountService.login(accountLoginDTO));
    // }
    @RequestMapping(method = RequestMethod.GET)
    public String getInformation(){return "";}
}
