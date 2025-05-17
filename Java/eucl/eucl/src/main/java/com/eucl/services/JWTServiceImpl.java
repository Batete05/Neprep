package com.eucl.services;

import com.eucl.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWTServiceImpl {

    public String extractUserName(String token) throws BadRequestException {
        return extractClaim(token, Claims::getSubject);

    }

    public String generateToken(UserDetails userDetails) {
        User user = (User) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateRefreshToken(claims, userDetails);
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSigninkey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) throws BadRequestException {
        final Claims claims = extralAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    private Claims extralAllClaims(String token) throws BadRequestException {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigninkey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new BadRequestException(" please provide valid token " + e.getMessage());
        } catch (JwtException e) {
            throw new BadRequestException("token parsing failed  " + e.getMessage());

        }


    }


    private Key getSigninkey() {
        byte[] key = Decoders.BASE64.decode("3D41112DCF3F434B58C192E379DF13D41112DCF3F434B58C192E379DF1");
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) throws BadRequestException {
        final String username = extractUserName(token);

        return (Objects.equals(username, userDetails.getUsername()) && isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) throws BadRequestException {
        final Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.after(new Date());
    }

}
