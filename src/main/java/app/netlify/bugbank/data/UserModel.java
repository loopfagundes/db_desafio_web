package app.netlify.bugbank.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModel {
    String email;
    String name;
    String password;

    public UserModel() {

    }

    public UserModel(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}