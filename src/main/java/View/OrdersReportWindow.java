package View;

import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrdersReportWindow extends JFrame {
    public JPanel contentPanel;
    public JPanel ordersPanel;
    public JTable ordersTable;
    public DefaultTableModel tableModel;
    public JScrollPane tableScrollPane;
    public JButton viewOrderButton;
    public JButton backButton;

    public OrdersReportWindow(Map<Order, List<Product>> orders, LocalTime startHour, LocalTime endHour){
        super("Orders Report");
        initGUI(orders, startHour, endHour);
    }

    void initGUI(Map<Order, List<Product>> orders, LocalTime startHour, LocalTime endHour){
        setSize(630, 350);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initTable(orders, startHour, endHour);
        initButtons();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void initTable(Map<Order, List<Product>> orders, LocalTime startHour, LocalTime endHour){
        int size = 0;
        ordersPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Client Username", "Date", "Time"};
        Set<Order> orderSet = orders.keySet();
        for(Order order : orderSet){
            if((startHour.compareTo(order.time) < 0) && (endHour.compareTo(order.time) > 0)){
                size++;
            }
        }

        int index = 0;
        Object[][] data = new Object[size][3];
        for(Order order : orderSet){
            if((startHour.compareTo(order.time) < 0) && (endHour.compareTo(order.time) > 0)) {
                data[index] = new Object[]{order.username, order.date, order.time};
                index++;
            }
        }

        tableModel = new DefaultTableModel(data, columnNames);
        ordersTable = new JTable(tableModel);
        ordersTable.setDefaultEditor(Object.class, null);
        tableScrollPane = new JScrollPane(ordersTable);
        ordersPanel.add(tableScrollPane);
        ordersPanel.setBounds(10, 10, 600, 200);
        contentPanel.add(ordersPanel);
    }

    void initButtons(){
        viewOrderButton = new JButton("View Order");
        viewOrderButton.setBounds(10, 230, 200, 70);
        viewOrderButton.setEnabled(false);
        contentPanel.add(viewOrderButton);

        backButton = new JButton("Back");
        backButton.setBounds(410, 230, 200, 70);
        contentPanel.add(backButton);
    }
}
