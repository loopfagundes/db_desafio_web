package app.netlify.bugbank.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModelDTO {
    String email;
    String name;
    String password;

    public UserModelDTO() {

    }

    public UserModelDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}