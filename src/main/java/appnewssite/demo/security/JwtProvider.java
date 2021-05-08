package appnewssite.demo.security;


import appnewssite.demo.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

     static final long expireTime = 1000 * 60 * 60 * 24;
     static final String securitKey = "maxfiysuzhechkimbilmasin324244323dytfghftucyfhtd56r56d65d";

    public String generateToken(String username, Role roles){

        Date expireDate = new Date(System.currentTimeMillis()+ expireTime);


        String token =  Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles",roles.getName())
                .signWith(SignatureAlgorithm.HS512,securitKey)
                .compact();
        return token;


        

    }

    public String getUsernameFromToken(String token){

        try {
            String email = Jwts
                    .parser()
                    .setSigningKey(securitKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        }catch (Exception e){
            return null;
        }

    }



}
