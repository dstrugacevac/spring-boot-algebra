package algebra.spring_boot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private final String SECRET_KEY = "ZGBHJBD6A7T32ZGUHJ2B1HGD67G872H2";

    public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        boolean isUsernameValid = username.equals(userDetails.getUsername());

        if (!isUsernameValid){
            return false;
        }

        boolean isTokenExpired = checkIsTokenExpired(token);

        if (isTokenExpired) {
            return false;
        }

        return true;
    }

    private boolean checkIsTokenExpired(String token){
        Date expirationDate = extractExpirationDate(token);
        boolean isExpired = expirationDate.before(new Date());
        return isExpired;
    }

    public String extractUsername (String token){
        Claims allClaims = extractAllClaims(token);
        return allClaims.getSubject();
    }

    public Date extractExpirationDate (String token) {
        Claims allClaims = extractAllClaims(token);
        return allClaims.getExpiration();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
