package View;

import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class EmployeeWindow extends JFrame implements Observer {
    public JPanel contentPanel;
    public JPanel ordersPanel;
    public JTable ordersTable;
    public DefaultTableModel tableModel;
    public JScrollPane tableScrollPane;
    public JButton viewOrderButton;
    public JButton logOutButton;
    public static boolean flag = false;

    public EmployeeWindow(){}

    public EmployeeWindow(Map<Order, List<Product>> orders){
        super("Employee Window");
        initGUI(orders);
    }

    void initGUI(Map<Order, List<Product>> orders){
        setSize(630, 350);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initTable(orders);
        initButtons();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if(flag)
            new NotificationWindow("New orders received!");
        flag = false;
    }

    void initButtons(){
        viewOrderButton = new JButton("View Order");
        viewOrderButton.setBounds(10, 230, 200, 70);
        viewOrderButton.setEnabled(false);
        contentPanel.add(viewOrderButton);

        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(410, 230, 200, 70);
        contentPanel.add(logOutButton);
    }

    void initTable(Map<Order, List<Product>> orders){
        ordersPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Client Username", "Date", "Time"};
        Object[][] data = new Object[orders.size()][3];
        int index = 0;
        Set<Order> orderSet = orders.keySet();
        for(Order order : orderSet){
            data[index] = new Object[]{order.username, order.date, order.time};
            index++;
        }

        tableModel = new DefaultTableModel(data, columnNames);
        ordersTable = new JTable(tableModel);
        ordersTable.setDefaultEditor(Object.class, null);
        tableScrollPane = new JScrollPane(ordersTable);
        ordersPanel.add(tableScrollPane);
        ordersPanel.setBounds(10, 10, 600, 200);
        contentPanel.add(ordersPanel);
    }

    @Override
    public void update(Observable o, Object arg) {
            flag = true;
    }
}
