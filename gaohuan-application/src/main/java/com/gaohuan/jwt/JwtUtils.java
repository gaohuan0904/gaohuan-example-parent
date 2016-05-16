package com.gaohuan.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 * Created by gh on 2016/5/11 0011.
 */
public class JwtUtils {

    public static void main(String[] args) {

        Key key= MacProvider.generateKey();
        String s= Jwts.builder().setSubject("gaohuan").signWith(SignatureAlgorithm.HS512,key).compact();
        System.out.println(s);

       String subject= Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject();
        System.out.println(subject);
    }
}
