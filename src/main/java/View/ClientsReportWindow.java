package View;

import Model.Order;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ClientsReportWindow extends JFrame{

    public JPanel contentPanel;

    public JPanel clientsPanel;
    public JTextArea clientsTextArea;
    public JScrollPane textScrollPane;
    public JButton backButton;

    public ClientsReportWindow(Map<Order, List<Product>> orders, int timesOrdered, double price){
        super("Clients Report");
        initGUI(orders, timesOrdered, price);
    }

    void initGUI(Map<Order, List<Product>> orders, int timesOrdered, double price){
        setSize(300, 300);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initTextArea();
        initBackButton();
        fillTextArea(orders, timesOrdered, price);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void initBackButton(){
        backButton = new JButton("Back");
        backButton.setBounds(150, 220, 125, 35);
        contentPanel.add(backButton);
    }

    void initTextArea(){
        clientsPanel = new JPanel(new BorderLayout());
        clientsTextArea = new JTextArea();
        clientsTextArea.setEditable(false);
        textScrollPane = new JScrollPane(clientsTextArea);
        clientsPanel.add(textScrollPane);
        clientsPanel.setBounds(10, 10, 265, 200);
        contentPanel.add(clientsPanel);
    }

    void fillTextArea(Map<Order, List<Product>> orders, int timesOrdered, double price){
        List<String> clientList = new ArrayList<>();
        List<Product> products = null;
        int totalPrice;
        Hashtable<String, Integer> countTable = new Hashtable<>();
        for(Order order : orders.keySet()){
            totalPrice = 0;
            products = orders.get(order);
            for(Product product : products){
                totalPrice += product.getPrice();
            }
            if(totalPrice > price && !clientList.contains(order.username)){
                clientList.add(order.username);
            }
            if(countTable.containsKey(order.username)){
                countTable.put(order.username, countTable.get(order.username) + 1);
            }
            else{
                countTable.put(order.username, 1);
            }
        }
        clientList.removeIf(client -> countTable.get(client) <= timesOrdered);
        for(String client : clientList){
            clientsTextArea.append(client + "\n");
        }
    }
}
