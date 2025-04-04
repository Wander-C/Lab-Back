package com.example.tomatomall.vo;

import com.example.tomatomall.enums.Role;
import com.example.tomatomall.po.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class AccountVO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String avatar;
    private Role role;
    private String telephone;
    private String email;
    private String location;

    public Account toPO(){
        Account account = new Account();
        account.setId(this.id);
        account.setUsername(this.username);
        account.setPassword(this.password);
        account.setName(this.name);
        account.setAvatar(this.avatar);
        account.setRole(this.role);
        account.setTelephone(this.telephone);
        account.setEmail(this.email);
        account.setLocation(this.location);
        return account;
    }
}
