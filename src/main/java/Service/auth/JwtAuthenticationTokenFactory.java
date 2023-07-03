package Service.auth;

import Data.User;

public class JwtAuthenticationTokenFactory implements AuthenticationTokenFactory {
    @Override
    public AuthenticationToken createAuthenticationToken(User user) {
        // Create and initialize JWT authentication token
        JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(user);
        jwtToken.authenticate(user);
        return jwtToken;
    }
}