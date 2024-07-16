package app.netlify.bugbank.utils.security;

import app.netlify.bugbank.data.UserData;
import app.netlify.bugbank.utils.FilesOperation;
import app.netlify.bugbank.utils.LoggerFactory;

import java.io.IOException;

public class SecureProperties {

    public static void firstUserEncrypt(String createName, String nameProp) throws Exception {
        String encryptedEmail = Crypto.encrypt(UserData.firstUser().getEmail());
        String encryptedName = Crypto.encrypt(UserData.firstUser().getName());
        String encryptedPassword = Crypto.encrypt(UserData.firstUser().getPassword());
        storeTheDataForEncryption(createName, nameProp, encryptedEmail, encryptedName, encryptedPassword);
    }

    public static void secondUserEncrypt(String createName, String nameProp) throws Exception {
        String encryptedEmail = Crypto.encrypt(UserData.secondUser().getEmail());
        String encryptedName = Crypto.encrypt(UserData.secondUser().getName());
        String encryptedPassword = Crypto.encrypt(UserData.secondUser().getPassword());
        storeTheDataForEncryption(createName, nameProp, encryptedEmail, encryptedName, encryptedPassword);
    }

    private static void storeTheDataForEncryption(String createName, String nameProp, String encryptedEmail, String encryptedName, String encryptedPassword) throws IOException {
        FilesOperation.createProperties("dataUser", createName);
        FilesOperation.setProperties("dataUser", nameProp, "email", encryptedEmail);
        FilesOperation.setProperties("dataUser", nameProp, "name", encryptedName);
        FilesOperation.setProperties("dataUser", nameProp, "password", encryptedPassword);
        LoggerFactory.log_INFO("Dados criptografados e armazenados com sucesso.");
    }

    public static void createProperties() {
        FilesOperation.createProperties("dataUSer", "1_user_crypto");
        FilesOperation.createProperties("dataUSer", "1_user");
        FilesOperation.createProperties("dataUSer", "2_user_crypto");
        FilesOperation.createProperties("dataUSer", "2_user");
    }

    public static void deleteProperties() {
        FilesOperation.deleteAllProperties("dataUSer");
    }
}