package org.ttweb.taskmanagement.domain.common.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.ttweb.taskmanagement.domain.model.user.UserId;

import java.security.Key;

@Component
public class TokenManager {
    private Key secretKey;

    public TokenManager(@Value("${app.token-secret-key}") String secretKey){
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    /**
     * Generate a JWT with user's id as its subject
     *
     * @param userId the id of the user
     * @return a JWT value
     */
    public String jwt(UserId userId){
        return Jwts.builder()
                .setSubject(String.valueOf(userId.value()))
                .signWith(secretKey).compact();
    }

    /**
     * Get user id out of a JWT value
     *
     * @param jws the jwt string
     * @return user id
     */
    public UserId verifyJwt(String jws){
        // String userIdValue = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jws).getBody().getSubject();
        String userIdValue = Jwts.parserBuilder().setSigningKey(secretKey).requireSubject(jws).toString();
        return new UserId(Long.parseLong(userIdValue));
    }
}
