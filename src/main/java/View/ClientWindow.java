package View;

import Model.Product;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientWindow extends JFrame{
    public JPanel contentPanel;

    public JPanel productsPanel;
    public JTable productsTable;
    public DefaultTableModel productsTableModel;
    public JScrollPane productsScrollPane;

    public JPanel sortPanel;
    public JLabel sortLabel;

    public JLabel sortTitleLabel;
    public JTextField sortTitleField;

    public JLabel sortRatingLabel;
    public JTextField sortRatingField;
    public JToggleButton lessRatingButton;
    public JToggleButton equalRatingButton;
    public JToggleButton greaterRatingButton;
    public ButtonGroup ratingButtonGroup;

    public JLabel sortCaloriesLabel;
    public JTextField sortCaloriesField;
    public JToggleButton lessCaloriesButton;
    public JToggleButton equalCaloriesButton;
    public ButtonGroup caloriesButtonGroup;

    public JToggleButton greaterCaloriesButton;
    public JLabel sortProteinLabel;
    public JTextField sortProteinField;
    public JToggleButton lessProteinButton;
    public JToggleButton equalProteinButton;
    public JToggleButton greaterProteinButton;
    public ButtonGroup proteinButtonGroup;

    public JLabel sortFatLabel;
    public JTextField sortFatField;
    public JToggleButton lessFatButton;
    public JToggleButton equalFatButton;
    public JToggleButton greaterFatButton;
    public ButtonGroup fatButtonGroup;

    public JLabel sortSodiumLabel;
    public JTextField sortSodiumField;
    public JToggleButton lessSodiumButton;
    public JToggleButton equalSodiumButton;
    public JToggleButton greaterSodiumButton;
    public ButtonGroup sodiumButtonGroup;

    public JLabel sortPriceLabel;
    public JTextField sortPriceField;
    public JToggleButton lessPriceButton;
    public JToggleButton equalPriceButton;
    public JToggleButton greaterPriceButton;
    public ButtonGroup priceButtonGroup;

    public JPanel buttonPanel;
    public JButton addProductsButton;
    public JButton viewBasketButton;
    public JButton logOutButton;

    public JPanel basketPanel;
    public JTable basketTable;
    public DefaultTableModel basketTableModel;
    public JScrollPane basketScrollPane;

    public JPanel orderPanel;
    public JButton orderButton;
    public JButton deleteBasketProductsButton;

    public JPanel billPanel;
    public JTextArea billTextArea;
    public JScrollPane billScrollPane;

    public User client;

    public ClientWindow(User client, List<Product> menu){
        super("Client Window");
        this.client = client;
        initGUI(menu);
    }

    void initGUI(List<Product> menu){
        setSize(1240, 500);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initProductsTable(menu);
        initSortPanel();
        initBasketTable();
        initOrderPanel();
        initBill();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void initBill(){
        billPanel = new JPanel(new BorderLayout());
        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        billScrollPane = new JScrollPane(billTextArea);
        billPanel.add(billScrollPane);
        billPanel.setBounds(860, 220, 360, 230);
        contentPanel.add(billPanel);
    }

    void initOrderPanel(){
        orderPanel = new JPanel(null);
        orderButton = new JButton("Order");
        orderButton.setBounds(15, 10, 200, 70);
        orderButton.setEnabled(false);
        deleteBasketProductsButton = new JButton("Delete Basket Products");
        deleteBasketProductsButton.setBounds(15, 90, 200, 70);
        deleteBasketProductsButton.setEnabled(false);
        orderPanel.add(orderButton);
        orderPanel.add(deleteBasketProductsButton);
        orderPanel.setBounds(620, 220, 230, 230);
        orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(orderPanel);
    }

    void initBasketTable(){
        basketPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[][] data = new Object[0][7];
        basketTableModel = new DefaultTableModel(data, columnNames);
        basketTable = new JTable(basketTableModel);
        basketTable.setDefaultEditor(Object.class, null);
        basketScrollPane = new JScrollPane(basketTable);
        basketPanel.add(basketScrollPane);
        basketTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        basketPanel.setBounds(620, 10, 600, 200);
        contentPanel.add(basketPanel);
    }


    void initSortPanel(){
        sortPanel = new JPanel(null);
        sortLabel = new JLabel("Fill the fields below to sort the products");
        sortLabel.setBounds(15, 10, 270, 15);
        
        sortTitleLabel = new JLabel("Title");
        sortTitleLabel.setBounds(15, 45, 50, 15);
        sortTitleField = new JTextField();
        sortTitleField.setBounds(75, 45, 200, 20);
        sortTitleField.getDocument().putProperty("Source", sortTitleField);
        
        sortRatingLabel = new JLabel("Rating");
        sortRatingLabel.setBounds(15, 70, 50, 15);
        sortRatingField = new JTextField();
        sortRatingField.setBounds(75, 70, 200, 20);
        sortRatingField.getDocument().putProperty("Source", sortRatingField);
        lessRatingButton = new JToggleButton("<");
        lessRatingButton.setMargin(new Insets(0, 0, 0, 0));
        lessRatingButton.setBounds(285, 70, 20, 20);
        equalRatingButton = new JToggleButton("=");
        equalRatingButton.setBounds(306, 70, 20, 20);
        equalRatingButton.setMargin(new Insets(0, 0, 0, 0));
        greaterRatingButton = new JToggleButton(">");
        greaterRatingButton.setBounds(327, 70, 20, 20);
        greaterRatingButton.setMargin(new Insets(0, 0, 0, 0));
        ratingButtonGroup = new ButtonGroup();
        ratingButtonGroup.add(lessRatingButton);
        ratingButtonGroup.add(equalRatingButton);
        ratingButtonGroup.add(greaterRatingButton);
        ratingButtonGroup.setSelected(equalRatingButton.getModel(), true);
        
        sortCaloriesLabel = new JLabel("Calories");
        sortCaloriesLabel.setBounds(15, 95, 50, 15);
        sortCaloriesField = new JTextField();
        sortCaloriesField.setBounds(75, 95, 200, 20);
        sortCaloriesField.getDocument().putProperty("Source", sortCaloriesField);
        lessCaloriesButton = new JToggleButton("<");
        lessCaloriesButton.setMargin(new Insets(0, 0, 0, 0));
        lessCaloriesButton.setBounds(285, 95, 20, 20);
        equalCaloriesButton = new JToggleButton("=");
        equalCaloriesButton.setBounds(306, 95, 20, 20);
        equalCaloriesButton.setMargin(new Insets(0, 0, 0, 0));
        greaterCaloriesButton = new JToggleButton(">");
        greaterCaloriesButton.setBounds(327, 95, 20, 20);
        greaterCaloriesButton.setMargin(new Insets(0, 0, 0, 0));
        caloriesButtonGroup = new ButtonGroup();
        caloriesButtonGroup.add(lessCaloriesButton);
        caloriesButtonGroup.add(equalCaloriesButton);
        caloriesButtonGroup.add(greaterCaloriesButton);
        caloriesButtonGroup.setSelected(equalCaloriesButton.getModel(), true);
        
        sortProteinLabel = new JLabel("Protein");
        sortProteinLabel.setBounds(15, 120, 50, 15);
        sortProteinField = new JTextField();
        sortProteinField.setBounds(75, 120, 200, 20);
        sortProteinField.getDocument().putProperty("Source", sortProteinField);
        lessProteinButton = new JToggleButton("<");
        lessProteinButton.setMargin(new Insets(0, 0, 0, 0));
        lessProteinButton.setBounds(285, 120, 20, 20);
        equalProteinButton = new JToggleButton("=");
        equalProteinButton.setBounds(306, 120, 20, 20);
        equalProteinButton.setMargin(new Insets(0, 0, 0, 0));
        greaterProteinButton = new JToggleButton(">");
        greaterProteinButton.setBounds(327, 120, 20, 20);
        greaterProteinButton.setMargin(new Insets(0, 0, 0, 0));
        proteinButtonGroup = new ButtonGroup();
        proteinButtonGroup.add(lessProteinButton);
        proteinButtonGroup.add(equalProteinButton);
        proteinButtonGroup.add(greaterProteinButton);
        proteinButtonGroup.setSelected(equalProteinButton.getModel(), true);
        
        sortFatLabel = new JLabel("Fat");
        sortFatLabel.setBounds(15, 145, 50, 15);
        sortFatField = new JTextField();
        sortFatField.setBounds(75, 145, 200, 20);
        sortFatField.getDocument().putProperty("Source", sortFatField);
        lessFatButton = new JToggleButton("<");
        lessFatButton.setMargin(new Insets(0, 0, 0, 0));
        lessFatButton.setBounds(285, 145, 20, 20);
        equalFatButton = new JToggleButton("=");
        equalFatButton.setBounds(306, 145, 20, 20);
        equalFatButton.setMargin(new Insets(0, 0, 0, 0));
        greaterFatButton = new JToggleButton(">");
        greaterFatButton.setBounds(327, 145, 20, 20);
        greaterFatButton.setMargin(new Insets(0, 0, 0, 0));
        fatButtonGroup = new ButtonGroup();
        fatButtonGroup.add(lessFatButton);
        fatButtonGroup.add(equalFatButton);
        fatButtonGroup.add(greaterFatButton);
        fatButtonGroup.setSelected(equalFatButton.getModel(), true);
        
        sortSodiumLabel = new JLabel("Sodium");
        sortSodiumLabel.setBounds(15, 170, 50, 15);
        sortSodiumField = new JTextField();
        sortSodiumField.setBounds(75, 170, 200, 20);
        sortSodiumField.getDocument().putProperty("Source", sortSodiumField);
        lessSodiumButton = new JToggleButton("<");
        lessSodiumButton.setMargin(new Insets(0, 0, 0, 0));
        lessSodiumButton.setBounds(285, 170, 20, 20);
        equalSodiumButton = new JToggleButton("=");
        equalSodiumButton.setBounds(306, 170, 20, 20);
        equalSodiumButton.setMargin(new Insets(0, 0, 0, 0));
        greaterSodiumButton = new JToggleButton(">");
        greaterSodiumButton.setBounds(327, 170, 20, 20);
        greaterSodiumButton.setMargin(new Insets(0, 0, 0, 0));
        sodiumButtonGroup = new ButtonGroup();
        sodiumButtonGroup.add(lessSodiumButton);
        sodiumButtonGroup.add(equalSodiumButton);
        sodiumButtonGroup.add(greaterSodiumButton);
        sodiumButtonGroup.setSelected(equalSodiumButton.getModel(), true);
        
        sortPriceLabel = new JLabel("Price");
        sortPriceLabel.setBounds(15, 195, 50, 15);
        sortPriceField = new JTextField();
        sortPriceField.setBounds(75, 195, 200, 20);
        sortPriceField.getDocument().putProperty("Source", sortPriceField);
        lessPriceButton = new JToggleButton("<");
        lessPriceButton.setMargin(new Insets(0, 0, 0, 0));
        lessPriceButton.setBounds(285, 195, 20, 20);
        equalPriceButton = new JToggleButton("=");
        equalPriceButton.setBounds(306, 195, 20, 20);
        equalPriceButton.setMargin(new Insets(0, 0, 0, 0));
        greaterPriceButton = new JToggleButton(">");
        greaterPriceButton.setBounds(327, 195, 20, 20);
        greaterPriceButton.setMargin(new Insets(0, 0, 0, 0));
        priceButtonGroup = new ButtonGroup();
        priceButtonGroup.add(lessPriceButton);
        priceButtonGroup.add(equalPriceButton);
        priceButtonGroup.add(greaterPriceButton);
        priceButtonGroup.setSelected(equalPriceButton.getModel(), true);

        sortPanel.add(sortLabel);
        sortPanel.add(sortTitleLabel);
        sortPanel.add(sortTitleField);
        sortPanel.add(sortRatingLabel);
        sortPanel.add(sortRatingField);
        sortPanel.add(lessRatingButton);
        sortPanel.add(equalRatingButton);
        sortPanel.add(greaterRatingButton);
        sortPanel.add(sortCaloriesLabel);
        sortPanel.add(sortCaloriesField);
        sortPanel.add(lessCaloriesButton);
        sortPanel.add(equalCaloriesButton);
        sortPanel.add(greaterCaloriesButton);
        sortPanel.add(sortProteinLabel);
        sortPanel.add(sortProteinField);
        sortPanel.add(lessProteinButton);
        sortPanel.add(equalProteinButton);
        sortPanel.add(greaterProteinButton);
        sortPanel.add(sortFatLabel);
        sortPanel.add(sortFatField);
        sortPanel.add(lessFatButton);
        sortPanel.add(equalFatButton);
        sortPanel.add(greaterFatButton);
        sortPanel.add(sortSodiumLabel);
        sortPanel.add(sortSodiumField);
        sortPanel.add(lessSodiumButton);
        sortPanel.add(equalSodiumButton);
        sortPanel.add(greaterSodiumButton);
        sortPanel.add(sortPriceLabel);
        sortPanel.add(sortPriceField);
        sortPanel.add(lessPriceButton);
        sortPanel.add(equalPriceButton);
        sortPanel.add(greaterPriceButton);
        contentPanel.add(sortPanel);
        sortPanel.setBounds(10, 220,  360, 230);
        sortPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        buttonPanel = new JPanel(null);
        addProductsButton = new JButton("Add Products");
        addProductsButton.setBounds(15, 10, 200, 70);
        addProductsButton.setEnabled(false);
        viewBasketButton = new JButton("Hide Basket");
        viewBasketButton.setBounds(15, 90, 200, 70);
        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(15, 170, 200, 50);
        buttonPanel.add(addProductsButton);
        buttonPanel.add(viewBasketButton);
        buttonPanel.add(logOutButton);
        contentPanel.add(buttonPanel);
        buttonPanel.setBounds(380, 220, 230, 230);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    void initProductsTable(List<Product> menu){
        productsPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[][] data = new Object[menu.size()][7];
        int index = 0;
        for(Product product : menu){
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

    public void modifyTable(List<Product> menu){
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[][] data = new Object[menu.size()][7];
        int index = 0;
        for(Product product : menu){
            data[index] = new Object[]{product.getTitle(), product.getRating(), product.getCalories(),
                    product.getProtein(), product.getFat(), product.getSodium(),
                    product.getPrice()};
            index++;
        }
        productsTableModel.setDataVector(data, columnNames);
        productsTable.getColumnModel().getColumn(0).setPreferredWidth(400);
    }

    public void generateBill(){
        int totalPrice = 0;
        for(int row = 0; row < basketTableModel.getRowCount(); row++){
            billTextArea.append(basketTableModel.getValueAt(row, 0).toString() + ", " +
                    basketTableModel.getValueAt(row, 1).toString() + ", " +
                    basketTableModel.getValueAt(row, 2).toString() + ", " +
                    basketTableModel.getValueAt(row, 3).toString() + ", " +
                    basketTableModel.getValueAt(row, 4).toString() + ", " +
                    basketTableModel.getValueAt(row, 5).toString() + ", " +
                    basketTableModel.getValueAt(row, 6).toString() + ", " + "\n");
            totalPrice += Integer.parseInt(basketTableModel.getValueAt(row, 6).toString());
        }
        billTextArea.append("Total Price: " + totalPrice);
    }

}
