package View;

import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class ProductsReportWindow extends JFrame {

    JPanel contentPanel;
    public JPanel productsPanel;
    public JTable productsTable;
    public DefaultTableModel tableModel;
    public JScrollPane productsScrollPane;
    public JButton backButton;

    public ProductsReportWindow(Map<Order, List<Product>> orders, int timesOrdered, LocalDate dayOrdered){
        super("Products Report");
        initGUI(orders, timesOrdered, dayOrdered);
    }

    void initGUI(Map<Order, List<Product>> orders, int timesOrdered, LocalDate dayOrdered){
        setSize(830, 300);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initProductsTable(getProducts(orders, timesOrdered, dayOrdered));
        initButtons();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void initButtons(){
        backButton = new JButton("Back");
        backButton.setBounds(685, 220, 125, 35);
        contentPanel.add(backButton);
    }

    void initProductsTable(Hashtable<Product, Integer> products){
        productsPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price", "Times Ordered"};
        Object[][] data = new Object[products.size()][8];
        int index = 0;
        for(Product product : products.keySet()){
            data[index] = new Object[]{product.getTitle(), product.getRating(), product.getCalories(),
                    product.getProtein(), product.getFat(), product.getSodium(),
                    product.getPrice(), products.get(product)};
            index++;
        }
        tableModel = new DefaultTableModel(data, columnNames);
        productsTable = new JTable(tableModel);

        productsTable.setDefaultEditor(Object.class, null);
        productsScrollPane = new JScrollPane(productsTable);
        productsPanel.add(productsScrollPane);
        productsTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        productsTable.getColumnModel().getColumn(7).setPreferredWidth(150);
        productsPanel.setBounds(10, 10, 800, 200);
        contentPanel.add(productsPanel);
    }

    Hashtable<Product, Integer> getProducts(Map<Order, List<Product>> orders, int timesOrdered, LocalDate dayOrdered){
        List<Product> productList = new ArrayList<>();
        List<Product> products;
        Hashtable<Product, Integer> countTable = new Hashtable<>();
        for(Order order : orders.keySet()){
            products = orders.get(order);
            for(Product product : products){
                if(countTable.containsKey(product)){
                    countTable.put(product, countTable.get(product) + 1);
                }
                else{
                    countTable.put(product, 1);
                }
            }
        }
        for(Product product : countTable.keySet()){
            if(countTable.get(product) > timesOrdered){
                productList.add(product);
            }
        }
        Hashtable<Product, Integer> result = new Hashtable<>();
        if(dayOrdered == null){
            for(Product product : productList){
                result.put(product, countTable.get(product));
            }
            return result;
        }
        else{
            List<Product> orderProducts = new ArrayList<>();
            for(Order order : orders.keySet()){
                if(order.date.equals(dayOrdered)){
                    products = orders.get(order);
                    orderProducts.addAll(products);
                }
            }
            productList.removeIf(product -> !orderProducts.contains(product));
            result.clear();
            for(Product product : productList){
                result.put(product, countTable.get(product));
            }
            return result;
        }
    }
}
