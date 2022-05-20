package View;

import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewOrderWindow extends JFrame {

    JPanel contentPanel;

    public JPanel productsPanel;
    public JTable productsTable;
    public DefaultTableModel productsTableModel;
    public JScrollPane productsScrollPane;

    public JButton deliverButton;
    public JButton backButton;

    public ViewOrderWindow(List<Product> products){
        super("Order Details");
        initGUI(products);
    }

    void initGUI(List<Product> products){
        setSize(630, 350);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initProductsTable(products);
        initButtons();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void initButtons(){
        deliverButton = new JButton("Deliver");
        deliverButton.setBounds(10, 230, 200, 70);
        contentPanel.add(deliverButton);

        backButton = new JButton("Back");
        backButton.setBounds(410, 230, 200, 70);
        contentPanel.add(backButton);
    }

    void initProductsTable(List<Product> products){
        productsPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[][] data = new Object[products.size()][7];
        int index = 0;
        for(Product product : products){
            data[index] = new Object[]{product.getTitle(), product.getRating(), product.getCalories(),
                    product.getProtein(), product.getFat(), product.getSodium(),
                    product.getPrice()};
            index++;
        }

        productsTableModel = new DefaultTableModel(data, columnNames);
        productsTable = new JTable(productsTableModel);

        productsTable.setDefaultEditor(Object.class, null);
        productsScrollPane = new JScrollPane(productsTable);
        productsPanel.add(productsScrollPane);
        productsTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        productsPanel.setBounds(10, 10, 600, 200);
        contentPanel.add(productsPanel);
    }
}
