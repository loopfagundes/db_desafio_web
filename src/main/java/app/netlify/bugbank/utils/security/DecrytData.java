package app.netlify.bugbank.utils.security;

import app.netlify.bugbank.supports.RecorderGet;

public class DecrytData {

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