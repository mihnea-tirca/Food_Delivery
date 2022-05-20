package View;

import javax.swing.*;

public class GenerateReportsWindow extends JFrame {
    public JPanel contentPanel;

    public JLabel hourIntervalLabel;
    public JTextField startHourField;
    public JLabel toLabel;
    public JTextField endHourField;

    public JLabel clientOrdersMoreThanLabel;
    public JTextField clientOrderedMoreThanField;

    public JLabel clientPriceHigherThanLabel;
    public JTextField clientPriceHigherThanField;

    public JLabel productsOrderedMoreThanLabel;
    public JTextField productsOrderedMoreThanField;

    public JLabel productsOrderedInDayLabel;
    public JTextField productsOrderedInDayField;

    public JButton generateReportButton;
    public JButton backButton;

    public GenerateReportsWindow(){
        super("Generate Reports");
        initGUI();
    }

    void initGUI(){
        setSize(375, 440);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void initComponents(){
        hourIntervalLabel = new JLabel("Orders performed between hours:");
        hourIntervalLabel.setBounds(20, 20, 300, 15);

        startHourField = new JTextField();
        startHourField.setBounds(20, 45, 100, 20);

        toLabel = new JLabel("-");
        toLabel.setBounds(125, 45, 5, 15);

        endHourField = new JTextField();
        endHourField.setBounds(135, 45, 100, 20);

        clientOrdersMoreThanLabel = new JLabel("Clients that ordered more than                    times");
        clientOrdersMoreThanLabel.setBounds(20, 125, 300, 15);

        clientOrderedMoreThanField = new JTextField();
        clientOrderedMoreThanField.setBounds(200, 125, 50, 20);

        clientPriceHigherThanLabel = new JLabel("Clients that made orders with price higher than");
        clientPriceHigherThanLabel.setBounds(20, 150, 270, 15);

        clientPriceHigherThanField = new JTextField();
        clientPriceHigherThanField.setBounds(295, 150, 50, 20);

        productsOrderedMoreThanLabel = new JLabel("Products ordered more than                    times");
        productsOrderedMoreThanLabel.setBounds(20, 230, 300, 15);

        productsOrderedMoreThanField = new JTextField();
        productsOrderedMoreThanField.setBounds(185, 230, 50, 20);

        productsOrderedInDayLabel = new JLabel("Products ordered in day");
        productsOrderedInDayLabel.setBounds(20, 255, 150, 15);

        productsOrderedInDayField = new JTextField();
        productsOrderedInDayField.setBounds(160, 255, 100, 20);

        generateReportButton = new JButton("Generate Report");
        generateReportButton.setBounds(20, 320, 150, 50);
        generateReportButton.setEnabled(false);

        backButton = new JButton("Back");
        backButton.setBounds(190, 320, 150, 50);

        contentPanel.add(hourIntervalLabel);
        contentPanel.add(startHourField);
        contentPanel.add(toLabel);
        contentPanel.add(endHourField);
        contentPanel.add(clientOrdersMoreThanLabel);
        contentPanel.add(clientOrderedMoreThanField);
        contentPanel.add(clientPriceHigherThanLabel);
        contentPanel.add(clientPriceHigherThanField);
        contentPanel.add(productsOrderedMoreThanLabel);
        contentPanel.add(productsOrderedMoreThanField);
        contentPanel.add(productsOrderedInDayLabel);
        contentPanel.add(productsOrderedInDayField);
        contentPanel.add(generateReportButton);
        contentPanel.add(backButton);
    }
}
