package View;

import javax.swing.*;
import java.awt.*;

public class AdminLogInWindow extends JFrame {
    public JPanel contentPanel;

    public JPanel credentialsPanel;
    public JLabel usernameLabel;
    public JTextField usernameField;
    public JLabel passwordLabel;
    public JPasswordField passwordField;

    public JPanel smallPanel;
    public JLabel validInputLabel;
    public JPanel buttonPanel;
    public JButton logInButton;
    public JButton backButton;


    public AdminLogInWindow(){
        super("Admin Log In");
        initGUI();
    }

    void initGUI(){
        setSize(500, 300);
        contentPanel = new JPanel(new GridLayout(2, 1));
        setContentPane(contentPanel);

        credentialsPanel = new JPanel(new GridLayout(2, 2));
        usernameLabel = new JLabel("username: ");
        credentialsPanel.add(usernameLabel);
        usernameField = new JTextField("admin");
        credentialsPanel.add(usernameField);
        passwordLabel = new JLabel("password: ");
        credentialsPanel.add(passwordLabel);
        passwordField = new JPasswordField("admin");
        credentialsPanel.add(passwordField);
        contentPanel.add(credentialsPanel);

        smallPanel = new JPanel(new GridLayout(1, 2));
        validInputLabel = new JLabel("");
        smallPanel.add(validInputLabel);
        buttonPanel = new JPanel(new GridLayout(1, 2));
        logInButton = new JButton("Log In");
        buttonPanel.add(logInButton);
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        smallPanel.add(buttonPanel);
        contentPanel.add(smallPanel);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
