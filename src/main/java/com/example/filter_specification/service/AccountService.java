package com.example.filter_specification.service;

import com.example.filter_specification.entity.Account;
import com.example.filter_specification.entity.dto.AccountRegisterDto;
import com.example.filter_specification.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;
    public AccountRegisterDto register(AccountRegisterDto accountRegisterDto){
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(accountRegisterDto.getUsername());
        if (optionalAccount.isPresent()){
            return null;
        }
        Account account = Account.builder()
                .username(accountRegisterDto.getUsername())
                .passwordHash(passwordEncoder.encode(accountRegisterDto.getPassword()))
                .role(accountRegisterDto.getRole())
                .build();
        accountRepository.save(account);
        accountRegisterDto.setId(account.getId());
        return accountRegisterDto;
    }
    public void login() {

    }
    public void getInformation(){

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findAccountByUsername(username);
        return null;
    }
    public Credential login (AccountLoginDto accountLoginDto){
        //1.Tìm account theo username
        //User user = (User) loadUserByUsername(accountLoginDto.getUsername());
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername((accountLoginDto.getUsername());
        if (!optionalAccount.isPresent()){
            throw new UsernameNotFoundException("User is not found");
        }
        Account account = optionalAccount.get();
        // so sánh password match.
        boolean isMatch = passwordEncoder.matches(accountLoginDto.getPassword(), account.getPasswordHash());
        if (isMatch){
            int expiredAfterDay = 7;
            String accessToken = JwtUtil.generateTokenByAccount(account,expiredAfterDay * 24 * 60 * 60 * 1000);
            String refreshToken = JwtUtil.generateTokenByAccount(account,14 * 24 * 60 * 60 * 1000);
            Credential credential = new Credential();
            credential.setAccessToken(accessToken);
            credential.setRefreshToken(refreshToken);
            credential.setExpiredAt(expiredAfterDay);
            credential.setScope("basic_information");
            return credential;
        }else {
            throw new UsernameNotFoundException("Password is not match");
        }
    }
    public void getInformation(){

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(username);
        if (!optionalAccount.isPresent()){
            throw new UsernameNotFoundException("Username is not found!");
        }
        Account account = optionalAccount.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(account.getRole() == 1 ? "ADMIN" : "USER");
        authorities.add(simpleGrantedAuthority);
        return new User(account.getUsername(), account.getPasswordHash(), authorities);
    }
}
