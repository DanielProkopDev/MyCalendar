package Service.auth;

import Data.User;
import Utils.JwtTokenUtil;

public class JwtAuthenticationToken extends AuthenticationToken {

    JwtTokenUtil jwtTokenUtil = JwtTokenUtil.getInstance();
    public JwtAuthenticationToken(User user) {
        super(user);
    }

    @Override
    public void authenticate(User user) {
        // Implementation for JWT authentication
        // Authenticate the user and generate a JWT token
        String jwtToken = jwtTokenUtil.generateJwtToken(user);
    }

    // Additional properties and methods specific to JWT authentication
    // ...
}