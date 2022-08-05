package com.mcamelo.msgApp.dtos;


import com.mcamelo.msgApp.services.validation.UserInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "Password must be not empity.")
    @Size(min = 3, max = 30, message = "Must has between 3 and 30 chars.")
    private String password;

    UserInsertDTO(){
        super();
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
