package Service;

import Data.User;
import Utils.AccessLevels;

public class AuthenticationServiceToken {
    private final User user;

    private int loggedIn = 0;

    private AccessLevels accessLevels = AccessLevels.USER;

    private volatile AuthenticationServiceToken instance;

    private AuthenticationServiceToken(User user){
        this.user = user;
    }

    public AuthenticationServiceToken getInstance(User user){
        if (instance == null){
            synchronized (AuthenticationServiceToken.class){
                if (instance == null){
                    instance = new AuthenticationServiceToken(user);
                    loggedIn = 1;
                } else if (!this.user.getUserName().equals(user.getUserName())) {
                    instance = new AuthenticationServiceToken(user);
                    loggedIn = 1;
                }
            }
        }
        return instance;
    }

    private User getUser() {
        return user;
    }


    private int getLoggedIn() {
        return loggedIn;
    }

    private void setLoggedIn(int loggedIn) {
        this.loggedIn = loggedIn;
    }

    private AccessLevels getAccessLevels() {
        return accessLevels;
    }

    private void setAccessLevels(AccessLevels accessLevels) {
        this.accessLevels = accessLevels;
    }

    private AuthenticationServiceToken getInstance() {
        return instance;
    }

    private void setInstance(AuthenticationServiceToken instance) {
        this.instance = instance;
    }
}
