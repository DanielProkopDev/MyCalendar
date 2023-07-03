package Service.auth;

import Data.User;

public abstract class AuthenticationToken {
    private User user;
    private String token;

    public AuthenticationToken(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public abstract void authenticate(User user);
}