package br.com.wep.app.config;

import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.security.MessageDigest;

@Configuration
public class md5Password {
    public static String md5(String pass){
        String message = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(pass.getBytes()));
            message = hash.toString(16);
        }catch (Exception e){

        }

        return message;
    }
}
