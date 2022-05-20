package View;

import javax.swing.*;

public class AdminWindow extends JFrame{
    public JPanel contentPanel;
    public JButton importButton;
    public JButton manageProductsButton;
    public JButton generateReportButton;
    public JButton logOutButton;

    public AdminWindow(){
        super("Admin Window");
        initGUI();
    }

    void initGUI(){
        setSize(300, 500);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);

        importButton = new JButton("Import Products");
        importButton.setBounds(45, 50, 200, 70);
        contentPanel.add(importButton);

        manageProductsButton = new JButton("Manage Products");
        manageProductsButton.setBounds(45, 140, 200, 70);
        contentPanel.add(manageProductsButton);

        generateReportButton = new JButton("Generate Reports");
        generateReportButton.setBounds(45, 230, 200, 70);
        contentPanel.add(generateReportButton);

        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(45, 320, 200, 70);
        contentPanel.add(logOutButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
