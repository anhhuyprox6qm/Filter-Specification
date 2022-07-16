package com.example.filter_specification.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credential {
    private String accessToken;
    private String refreshToken;
    private long expiredAt;
    private String scope;// basic_information
}
