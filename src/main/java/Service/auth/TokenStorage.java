package Service.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TokenStorage {
    private static final Map<String, String> tokenStore = new ConcurrentHashMap<>();
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static void storeToken(String token) {
        Objects.requireNonNull(token, "Token cannot be null");

        // Hash the token securely
        String hashedToken = hashToken(token);

        // Store the hashed token
        tokenStore.put(hashedToken, token);
    }

    public static boolean isTokenValid(String token) {
        Objects.requireNonNull(token, "Token cannot be null");

        try {
            // Validate the token's signature and expiration
            Jws<Claims> parsedToken = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // Get the claims from the parsed token
            Claims claims = parsedToken.getBody();

            // Perform additional validations if needed
            // Example: Check if the token exists in the token store
            return tokenStore.containsKey(token) && isTokenNotExpired(claims);
        } catch (ExpiredJwtException e) {
            // The token has expired
            return false;
        } catch (Exception e) {
            // An error occurred during token validation
            return false;
        }
    }

    public static Optional<String> getToken(String hashedToken) {
        Objects.requireNonNull(hashedToken, "Hashed token cannot be null");

        // Retrieve the original token from the token store
        return Optional.ofNullable(tokenStore.get(hashedToken));
    }

    public static void removeToken(String token) {
        Objects.requireNonNull(token, "Token cannot be null");

        // Hash the token for removal
        String hashedToken = hashToken(token);

        // Remove the hashed token from storage
        tokenStore.remove(hashedToken);
    }

    private static String hashToken(String token) {
        try {
            // Create a message digest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the token
            byte[] hashedBytes = digest.digest(token.getBytes());

            // Convert the hashed bytes to a hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isTokenNotExpired(Claims claims) {
        // Check if the token's expiration date is in the future
        Date expiration = claims.getExpiration();
        Date now = new Date();
        return expiration != null && expiration.after(now);
    }

    public static Map getTokenStorage(){
        return tokenStore;
    }
}
