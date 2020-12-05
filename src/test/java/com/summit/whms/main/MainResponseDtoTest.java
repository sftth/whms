package com.summit.whms.main;

import com.summit.whms.main.dto.MainResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainResponseDtoTest {

    @Test
    public void lombokTest() {
        //given
        String name = "jacob";
        String email = "jacob@me.com";
        String password = "1234";

        //when
        MainResponseDto mainResponseDto = new MainResponseDto(name,email,password);

        //then
        assertThat(mainResponseDto.getName()).isEqualTo(name);
        assertThat(mainResponseDto.getEmail()).isEqualTo(email);
        assertThat(mainResponseDto.getPassword()).isEqualTo(password);
    }
}
