package app.netlify.bugbank.data;

import app.netlify.bugbank.supports.RecorderGet;
import lombok.Getter;

import java.io.IOException;

@Getter
public class DataObjectUser {
    String email;
    String name;
    String password;

    public DataObjectUser(String nameProp) throws IOException {
        this.email = RecorderGet.getDataUser(nameProp, "email");
        this.name = RecorderGet.getDataUser(nameProp, "name");
        this.password = RecorderGet.getDataUser(nameProp, "password");
    }
}