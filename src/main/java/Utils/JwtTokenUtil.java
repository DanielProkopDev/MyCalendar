package Utils;

import Data.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    private static JwtTokenUtil instance;
    private  final String SECRET_KEY = "BhZ2ZRDMA9p88qbCzraPekWf1J3nU+AYaxxXkZ3XcAo="; // Replace with your own secret key
    private  final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    private JwtTokenUtil() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static JwtTokenUtil getInstance() {
        if (instance == null) {
            synchronized (JwtTokenUtil.class) {
                if (instance == null) {
                    instance = new JwtTokenUtil();
                }
            }
        }
        return instance;
    }

    public  String generateJwtToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("email", user.getEmail());
        // Add additional claims as needed

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, User user) {
        String username = extractUsername(token);
        return (username.equals(user.getUserName()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        return expirationDate.before(new Date());
    }
}