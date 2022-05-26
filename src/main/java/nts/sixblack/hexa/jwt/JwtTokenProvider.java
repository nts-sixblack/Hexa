package nts.sixblack.hexa.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${application.security.secret-key}")
    private String jwtSecretKey;

    private final long expirationTime=6*30*24*60*60*100;


    public String generateToken(CustomUserDetail userDetail){
        Date now = new Date();
        Date expiration = new Date(now.getTime()+expirationTime);
        return Jwts.builder()
                .setSubject(Long.toString(userDetail.getUser().getUserId()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public long getUserId(String token){
        return Long.parseLong(Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject());
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
