package app.netlify.bugbank.utils.security;

import app.netlify.bugbank.utils.FilesOperation;

public class SecurePropertiesReader {
    public static void main(String[] args) {

        try {
            //SecurePropertiesWriter.crypto("1_user_crypto");
            //decrypted("1_user_crypto");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void decrypted(String nameProp) throws Exception {
        String email = Crypto.decrypt(FilesOperation.getProperties(nameProp).getProperty("email"));
        String name = Crypto.decrypt(FilesOperation.getProperties(nameProp).getProperty("name"));
        String password = Crypto.decrypt(FilesOperation.getProperties(nameProp).getProperty("password"));
        System.out.println("E-mail: " + email);
        System.out.println("Name: " + name );
        System.out.println("Senha: " + password);
    }
}