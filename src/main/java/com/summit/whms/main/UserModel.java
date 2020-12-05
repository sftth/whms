package com.summit.whms.main;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("users")
public class UserModel {
    private String inputName;
    private String inputEmail ;
    private String inputPassword;

    public UserModel(String inputName, String inputEmail, String inputPassword) {
        this.inputName = inputName;
        this.inputEmail = inputEmail;
        this.inputPassword = inputPassword;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }
}
