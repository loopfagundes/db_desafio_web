package app.netlify.bugbank.utils.security;


import app.netlify.bugbank.data.UserData;
import app.netlify.bugbank.supports.RecorderGet;
import app.netlify.bugbank.utils.FilesOperation;
import app.netlify.bugbank.utils.LoggerFactory;

import java.io.IOException;

public class SecurePropertiesWriter {

//    public static void main(String[] args) throws Exception {
//        crypto("1_user_crypto");
//        crypto("2_user_crypto");
//    }

    public static void crypto(String nameProp) throws Exception {
        // Criptografar o e-mail e a senha
        String encryptedEmail = Crypto.encrypt(UserData.firstUser().getEmail());
        String encryptedName = Crypto.encrypt(UserData.firstUser().getName());
        String encryptedPassword = Crypto.encrypt(UserData.firstUser().getPassword());

        // Armazenar dados criptografados
        FilesOperation.setProperty(nameProp, "email", encryptedEmail);
        FilesOperation.setProperty(nameProp, "name", encryptedName);
        FilesOperation.setProperty(nameProp, "password", encryptedPassword);
        LoggerFactory.log_INFO("Dados criptografados e armazenados com sucesso.");
    }

    public static String encryptoEmail(String nameProp) throws Exception {
        return Crypto.decrypt(RecorderGet.getDataUser(nameProp, "email"));
    }

    public static String encryptoName(String nameProp) throws Exception {
        return Crypto.decrypt(RecorderGet.getDataUser(nameProp, "name"));
    }

    public static String encryptoPassword(String nameProp) throws Exception {
        return Crypto.decrypt(RecorderGet.getDataUser(nameProp, "password"));
    }
}