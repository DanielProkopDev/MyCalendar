package GUI;

import Data.User;
import GUI.util.LoginStatusObserver;
import Service.UserService;
import Service.auth.TokenStorage;
import Utils.CompareChar;
import Utils.Hash;
import Utils.JwtTokenUtil;
import Utils.SaltHash;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LoginSubmitListener extends MouseAdapter {
    private final UserService userService;
    private final LoginFrame loginFrame;

    SaltHash saltHash = new SaltHash();

    Hash hash = new Hash();

    CompareChar compareChar = new CompareChar();


    private List<LoginStatusObserver> observers = new ArrayList<>();

    private boolean loginSuccess;


    public LoginSubmitListener(LoginFrame loginFrame, StartView1 startView1) {
        this.loginFrame = loginFrame;
        this.userService = UserService.getInstance();
        addObserver(startView1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String email = loginFrame.getEmail();
        char[] password = loginFrame.getPassword().toCharArray();
        System.out.println(email);
        System.out.println(Arrays.toString(password));
        // Find the user by email
        Optional<User> user = userService.findUserByEmail(email);


        if (user.isPresent()) {
            // Verify the password
            User foundUser = user.get();
            char[] storedPasswordHash = foundUser.getPassword();
            Optional<String> storedPasswordSalt = Optional.of(foundUser.getSalt());
            Optional<char[]> inputPasswordHash = hash.hashPassword(password, storedPasswordSalt);

            System.out.println(password);
            System.out.println(storedPasswordSalt.get());
            System.out.println(storedPasswordHash);
            System.out.println(inputPasswordHash.get());

            if (compareChar.compareCharArrays(storedPasswordHash,inputPasswordHash.get())) {
                // Password is correct, perform login actions
                // Generate JWT token and handle further authentication/authorization

                // Generate JWT token
                String jwtToken = JwtTokenUtil.getInstance().generateJwtToken(foundUser);

                //  Store the token securely
                TokenStorage.storeToken(jwtToken);

                System.out.println("you token storage:" +TokenStorage.getTokenStorage());

                loginSuccess = true;

                handleSuccessfulLogin();

                // Redirect to a secured page
                // Your code here

                return; // Exit the method
            }else {
                loginSuccess = false;
                handleSuccessfulLogin();
                // Invalid credentials, display error message or perform other actions
                displayErrorMessage("Invalid credentials. Please try again.");
            }
        }else {
            loginSuccess = false;
            handleSuccessfulLogin();
            // Invalid credentials, display error message or perform other actions
            displayErrorMessage("Invalid credentials. Please try again.");
        }

    }
    // Method to display an error message
    private void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addObserver(LoginStatusObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LoginStatusObserver observer) {
        observers.remove(observer);
    }

    public void handleSuccessfulLogin() {
        // Notify all observers about the login status change
        for (LoginStatusObserver observer : observers) {
            observer.updateStatusIndicator(loginSuccess);
        }
    }
}
