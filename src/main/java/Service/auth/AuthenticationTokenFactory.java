package Service.auth;

import Data.User;
import Service.AuthenticationServiceToken;

public interface AuthenticationTokenFactory {
    AuthenticationToken createAuthenticationToken(User user);
}