package app.netlify.bugbank.utils.security;


import app.netlify.bugbank.utils.FilesOperation;

public class SecurePropertiesWriter {
    public static void main(String[] args) {
        try {
            // Criptografar o e-mail e a senha
            String encryptedEmail = Crypto.encrypt(FilesOperation.getProperties("crypto").getProperty("email"));
            String encryptedPassword = Crypto.encrypt(FilesOperation.getProperties("crypto").getProperty("password"));

            // Armazenar dados criptografados
            FilesOperation.setProperty("crypto", "email", encryptedEmail);
            FilesOperation.setProperty("crypto", "password", encryptedPassword);

            System.out.println("Dados criptografados e armazenados com sucesso.");
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}