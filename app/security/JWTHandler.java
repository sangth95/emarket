package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

/**
 * Created by An on 7/9/2017.
 */
public class JWTHandler {
    private static final String sign = "SGVsbG8gUGxheSBmcmFtZXdvcms=";

    public static String createToken(String username) {
        return Jwts.builder()
                .setSubject("Login")
                .claim("username", username)
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(sign)
                )
                .compact();
    }

    public static String getUsernameBaseOnToken(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(
                        TextCodec.BASE64.decode(sign)
                )
                .parseClaimsJws(token);
        return (String) jws.getBody().get("username");
    }

}
