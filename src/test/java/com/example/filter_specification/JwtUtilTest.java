package com.example.filter_specification;

import com.example.filter_specification.entity.Account;
import org.junit.Test;

public class JwtUtilTest {
    @Test
    public void testGenerateToken(){
        Account account = Account.builder()
                .id(System.currentTimeMillis())
                .role(1)
                .username("quanghuy123")
                .passwordHash("xyz")
                .build();
        String accessToken = JwtUtilTest.generateTokenByAccount(account,15 * 24 * 60 * 60 * 1000);
        System.out.println(accessToken);
    }
}
