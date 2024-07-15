package app.netlify.bugbank.utils.security;

import app.netlify.bugbank.data.UserData;
import app.netlify.bugbank.supports.RecorderGet;
import app.netlify.bugbank.utils.FilesOperation;
import app.netlify.bugbank.utils.LoggerFactory;

import java.io.IOException;

public class SecureProperties {

    public static void firstUserEncrypt(String nameProp) throws Exception {
        String encryptedEmail = Crypto.encrypt(UserData.firstUser().getEmail());
        String encryptedName = Crypto.encrypt(UserData.firstUser().getName());
        String encryptedPassword = Crypto.encrypt(UserData.firstUser().getPassword());
        storeTheDataForEncryption(nameProp, encryptedEmail, encryptedName, encryptedPassword);
    }

    public static void secondUserEncrypt(String nameProp) throws Exception {
        String encryptedEmail = Crypto.encrypt(UserData.secondUser().getEmail());
        String encryptedName = Crypto.encrypt(UserData.secondUser().getName());
        String encryptedPassword = Crypto.encrypt(UserData.secondUser().getPassword());
        storeTheDataForEncryption(nameProp, encryptedEmail, encryptedName, encryptedPassword);
    }

    private static void storeTheDataForEncryption(String nameProp, String encryptedEmail, String encryptedName, String encryptedPassword) throws IOException {
        FilesOperation.setProperty("dataUser", nameProp, "email", encryptedEmail);
        FilesOperation.setProperty("dataUser", nameProp, "name", encryptedName);
        FilesOperation.setProperty("dataUser", nameProp, "password", encryptedPassword);
        LoggerFactory.log_INFO("Dados criptografados e armazenados com sucesso.");
    }

    /*
     * converter dados de um formato cifrado.
     */
    public static String decryptoEmail(String nameProp) throws Exception {
        return Crypto.decrypt(RecorderGet.getDataUser("dataUser", nameProp, "email"));
    }

    public static String decryptoName(String nameProp) throws Exception {
        return Crypto.decrypt(RecorderGet.getDataUser("dataUser", nameProp, "name"));
    }

    public static String decryptoPassword(String nameProp) throws Exception {
        return Crypto.decrypt(RecorderGet.getDataUser("dataUser", nameProp, "password"));
    }
}