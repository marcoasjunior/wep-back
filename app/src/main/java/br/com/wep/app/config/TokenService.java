package br.com.wep.app.config;

import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

public class TokenService {

    private static final long expirationTime = 1800000;
    private static final String key = "secretKey"; //tranformar em variavel de ambiente
    @Autowired
    private static UserRepo repo;

    public static String generateToken(User user){

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static Claims decodeToken(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
