package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.util.List;

/**
 * Created by An on 7/9/2017.
 */
public class JWTHandler {
    private static final String sign = "SGVsbG8gUGxheSBmcmFtZXdvcms=";

    public static String createToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject("Login")
                .claim("username", username)
                .claim("roles", roles)
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

    public static List<String> getRolesBaseOnToken(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(
                        TextCodec.BASE64.decode(sign)
                )
                .parseClaimsJws(token);
        return (List<String>) jws.getBody().get("roles");
    }

}
