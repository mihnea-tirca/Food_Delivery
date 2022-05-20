package View;

import javax.swing.*;
import java.awt.*;

import Logic.Controller;

public class UserTypeWindow extends JFrame {
    public JPanel contentPanel;

    public JPanel instructionsPanel;
    public JLabel instructionsLabel;

    public JPanel buttonPanel;
    public JButton clientButton;
    public JButton employeeButton;
    public JButton adminButton;

    public UserTypeWindow(String name){
        super(name);
        initGUI();
        new Controller(this);
    }

    void initGUI(){
        setSize(500, 100);
        contentPanel = new JPanel(new GridLayout(2, 1));
        setContentPane(contentPanel);

        instructionsPanel = new JPanel();
        instructionsLabel = new JLabel("Choose user type");
        instructionsPanel.add(instructionsLabel);

        buttonPanel = new JPanel(new GridLayout(1, 3));
        clientButton = new JButton("Client");
        buttonPanel.add(clientButton);
        employeeButton = new JButton("Employee");
        buttonPanel.add(employeeButton);
        adminButton = new JButton("Administrator");
        buttonPanel.add(adminButton);

        contentPanel.add(instructionsPanel);
        contentPanel.add(buttonPanel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
