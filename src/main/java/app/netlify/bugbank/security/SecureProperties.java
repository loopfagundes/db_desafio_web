package app.netlify.bugbank.security;

import app.netlify.bugbank.utils.FilesOperation;

import java.io.IOException;

import static app.netlify.bugbank.data.UserData.*;
import static app.netlify.bugbank.security.Crypto.*;

public class SecureProperties {

    private static void firstUserEncrypt(String createName, String nameProp) throws Exception {
        String encryptedEmail = encrypt(firstUser().getEmail());
        String encryptedName = encrypt(firstUser().getName());
        String encryptedPassword = encrypt(firstUser().getPassword());
        storeTheDataForEncryption(createName, nameProp, encryptedEmail, encryptedName, encryptedPassword);
    }

    private static void secondUserEncrypt(String createName, String nameProp) throws Exception {
        String encryptedEmail = encrypt(secondUser().getEmail());
        String encryptedName = encrypt(secondUser().getName());
        String encryptedPassword = encrypt(secondUser().getPassword());
        storeTheDataForEncryption(createName, nameProp, encryptedEmail, encryptedName, encryptedPassword);
    }

    private static void storeTheDataForEncryption(String createName, String nameProp, String encryptedEmail, String encryptedName, String encryptedPassword) throws IOException {
        FilesOperation.createProperties("dataUser", createName);
        FilesOperation.setProperties("dataUser", nameProp, "email", encryptedEmail);
        FilesOperation.setProperties("dataUser", nameProp, "name", encryptedName);
        FilesOperation.setProperties("dataUser", nameProp, "password", encryptedPassword);
    }

    public static void dataUser(String user, String createName, String nameProp) throws Exception {
        switch (user) {
            case "firstUser":
                firstUserEncrypt(createName, nameProp);
                break;
            case "secondUser":
                secondUserEncrypt(createName, nameProp);
                break;
        }
    }

    public static void createProperties() {
        FilesOperation.createProperties("dataUSer", "1_user_crypto");
        FilesOperation.createProperties("dataUSer", "1_user");
        FilesOperation.createProperties("dataUSer", "2_user_crypto");
        FilesOperation.createProperties("dataUSer", "2_user");
    }
}