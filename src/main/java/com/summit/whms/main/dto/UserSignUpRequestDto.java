package com.summit.whms.main.dto;

import com.summit.whms.main.domain.users.Role;
import com.summit.whms.main.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String name;
    private String email;
    private String passwd;
    private Role role;

    @Builder
    public UserSignUpRequestDto(String name, String email, String passwd, Role role) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .passwd(passwd)
                .role(role)
                .build();
    }
}
