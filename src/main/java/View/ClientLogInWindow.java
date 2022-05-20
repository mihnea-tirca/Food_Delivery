package View;

import javax.swing.*;
import java.awt.*;

public class ClientLogInWindow extends JFrame{
    public JPanel contentPanel;

    public JButton alreadyRegisteredButton;
    public JButton createAccountButton;

    public JPanel smallPanel;
    public JButton logInButton;
    public JButton registerButton;
    public JButton backButton;

    public JLabel usernameLabel;
    public JTextField usernameField;
    public JLabel passwordLabel;
    public JPasswordField passwordField;
    public JLabel confirmPasswordLabel;
    public JPasswordField confirmPasswordField;

    public JLabel validInputLabel;

    public ClientLogInWindow(){
        super("Client Log In");
        initGUI();
    }

    void initGUI(){
        setSize(500, 500);
        contentPanel = new JPanel(new GridLayout(5, 2));
        setContentPane(contentPanel);

        createAccountButton = new JButton("Create Account");
        contentPanel.add(createAccountButton);
        alreadyRegisteredButton = new JButton("Already Registered?");
        contentPanel.add(alreadyRegisteredButton);
        usernameLabel = new JLabel("username: ");
        contentPanel.add(usernameLabel);
        usernameField = new JTextField("client");
        contentPanel.add(usernameField);
        passwordLabel = new JLabel("password: ");
        contentPanel.add(passwordLabel);
        passwordField = new JPasswordField("client");
        contentPanel.add(passwordField);
        confirmPasswordLabel = new JLabel("confirm password: ");
        contentPanel.add(confirmPasswordLabel);
        confirmPasswordLabel.setVisible(false);
        confirmPasswordField = new JPasswordField();
        contentPanel.add(confirmPasswordField);
        confirmPasswordField.setVisible(false);
        validInputLabel = new JLabel("");
        contentPanel.add(validInputLabel);
        smallPanel = new JPanel(new GridLayout(1, 2));
        contentPanel.add(smallPanel);

        logInButton = new JButton("Log In");
        registerButton = new JButton("Register");
        smallPanel.add(logInButton);
        backButton = new JButton("Back");
        smallPanel.add(backButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
