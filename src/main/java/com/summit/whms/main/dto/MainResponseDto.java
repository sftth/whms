package com.summit.whms.main.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //모든 필드에 get 메소드 생성
@RequiredArgsConstructor //선언된 모든 final 필드가 포함된 생성자 생성
public class MainResponseDto {
    private final String name;
    private final String email;
    private final String password;


}
