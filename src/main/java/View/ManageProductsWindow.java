package View;

import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageProductsWindow extends JFrame {
    public JPanel contentPanel;

    public JPanel productsPanel;
    public JTable productsTable;
    public DefaultTableModel tableModel;
    public JScrollPane tableScrollPane;

    public JPanel addPanel;
    public JLabel addLabel;
    public JLabel addTitleLabel;
    public JTextField addTitleField;
    public JLabel addRatingLabel;
    public JTextField addRatingField;
    public JLabel addCaloriesLabel;
    public JTextField addCaloriesField;
    public JLabel addProteinLabel;
    public JTextField addProteinField;
    public JLabel addFatLabel;
    public JTextField addFatField;
    public JLabel addSodiumLabel;
    public JTextField addSodiumField;
    public JLabel addPriceLabel;
    public JTextField addPriceField;
    public JButton addBaseProductButton;
    public JButton addCompositeProductButton;

    public JPanel modifyPanel;
    public JLabel modifyLabel;
    public JLabel modifyTitleLabel;
    public JTextField modifyTitleField;
    public JLabel modifyRatingLabel;
    public JTextField modifyRatingField;
    public JLabel modifyCaloriesLabel;
    public JTextField modifyCaloriesField;
    public JLabel modifyProteinLabel;
    public JTextField modifyProteinField;
    public JLabel modifyFatLabel;
    public JTextField modifyFatField;
    public JLabel modifySodiumLabel;
    public JTextField modifySodiumField;
    public JLabel modifyPriceLabel;
    public JTextField modifyPriceField;
    public JButton modifyProductButton;

    public JPanel deletePanel;
    public JLabel deleteLabel;
    public JLabel deleteTitleLabel;
    public JTextField deleteTitleField;
    public JLabel deleteRatingLabel;
    public JTextField deleteRatingField;
    public JLabel deleteCaloriesLabel;
    public JTextField deleteCaloriesField;
    public JLabel deleteProteinLabel;
    public JTextField deleteProteinField;
    public JLabel deleteFatLabel;
    public JTextField deleteFatField;
    public JLabel deleteSodiumLabel;
    public JTextField deleteSodiumField;
    public JLabel deletePriceLabel;
    public JTextField deletePriceField;
    public JButton deleteProductButton;
    public JButton backButton;

    public ManageProductsWindow(List<Product> menu){
        super("Manage Products");
        initGUI(menu);
    }

    void initGUI(List<Product> menu){
        setSize(955, 600);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        initTable(menu);
        initAddPanel();
        initModifyPanel();
        initDeletePanel();
        initBackButton();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void initBackButton(){
        backButton = new JButton("Back");
        add(backButton);
        backButton.setBounds(835, 10, 100, 70);
    }

    void initTable(List<Product> menu){
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

        tableModel = new DefaultTableModel(data, columnNames);
        productsTable = new JTable(tableModel);

        //productsTable = new JTable(data, columnNames);
        //tableModel = (DefaultTableModel) productsTable.getModel();
        productsTable.setDefaultEditor(Object.class, null);
        tableScrollPane = new JScrollPane(productsTable);
        productsPanel.add(tableScrollPane);
        productsTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        productsPanel.setBounds(10, 10, 600, 200);
        contentPanel.add(productsPanel);
    }

    void initAddPanel(){
        addPanel = new JPanel(null);
        addLabel = new JLabel("Fill the fields below and press \"Add Product\"");
        addLabel.setBounds(15, 10, 270, 15);
        addTitleLabel = new JLabel("Title");
        addTitleLabel.setBounds(15, 45, 50, 15);
        addTitleField = new JTextField();
        addTitleField.setBounds(75, 45, 200, 20);
        addRatingLabel = new JLabel("Rating");
        addRatingLabel.setBounds(15, 70, 50, 15);
        addRatingField = new JTextField();
        addRatingField.setBounds(75, 70, 200, 20);
        addCaloriesLabel = new JLabel("Calories");
        addCaloriesLabel.setBounds(15, 95, 50, 15);
        addCaloriesField = new JTextField();
        addCaloriesField.setBounds(75, 95, 200, 20);
        addProteinLabel = new JLabel("Protein");
        addProteinLabel.setBounds(15, 120, 50, 15);
        addProteinField = new JTextField();
        addProteinField.setBounds(75, 120, 200, 20);
        addFatLabel = new JLabel("Fat");
        addFatLabel.setBounds(15, 145, 50, 15);
        addFatField = new JTextField();
        addFatField.setBounds(75, 145, 200, 20);
        addSodiumLabel = new JLabel("Sodium");
        addSodiumLabel.setBounds(15, 170, 50, 15);
        addSodiumField = new JTextField();
        addSodiumField.setBounds(75, 170, 200, 20);
        addPriceLabel = new JLabel("Price");
        addPriceLabel.setBounds(15, 195, 50, 15);
        addPriceField = new JTextField();
        addPriceField.setBounds(75, 195, 200, 20);
        addBaseProductButton = new JButton("Add Base Product");
        addBaseProductButton.setBounds(75, 220, 200, 70);
        addCompositeProductButton = new JButton("Add Composite Product");
        addCompositeProductButton.setBounds(75, 220, 200, 70);
        addPanel.add(addLabel);
        addPanel.add(addTitleLabel);
        addPanel.add(addTitleField);
        addPanel.add(addRatingLabel);
        addPanel.add(addRatingField);
        addPanel.add(addCaloriesLabel);
        addPanel.add(addCaloriesField);
        addPanel.add(addProteinLabel);
        addPanel.add(addProteinField);
        addPanel.add(addFatLabel);
        addPanel.add(addFatField);
        addPanel.add(addSodiumLabel);
        addPanel.add(addSodiumField);
        addPanel.add(addPriceLabel);
        addPanel.add(addPriceField);
        addPanel.add(addBaseProductButton);
        //addPanel.add(addCompositeProductButton);
        contentPanel.add(addPanel);
        addPanel.setBounds(10, 220,  300, 300);
        addPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    void initModifyPanel(){
        modifyPanel = new JPanel(null);
        modifyLabel = new JLabel("Select product, edit, press \"Modify Product\"");
        modifyLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        modifyLabel.setBounds(15, 10, 270, 15);
        modifyTitleLabel = new JLabel("Title");
        modifyTitleLabel.setBounds(15, 45, 50, 15);
        modifyTitleField = new JTextField();
        modifyTitleField.setBounds(75, 45, 200, 20);
        modifyRatingLabel = new JLabel("Rating");
        modifyRatingLabel.setBounds(15, 70, 50, 15);
        modifyRatingField = new JTextField();
        modifyRatingField.setBounds(75, 70, 200, 20);
        modifyCaloriesLabel = new JLabel("Calories");
        modifyCaloriesLabel.setBounds(15, 95, 50, 15);
        modifyCaloriesField = new JTextField();
        modifyCaloriesField.setBounds(75, 95, 200, 20);
        modifyProteinLabel = new JLabel("Protein");
        modifyProteinLabel.setBounds(15, 120, 50, 15);
        modifyProteinField = new JTextField();
        modifyProteinField.setBounds(75, 120, 200, 20);
        modifyFatLabel = new JLabel("Fat");
        modifyFatLabel.setBounds(15, 145, 50, 15);
        modifyFatField = new JTextField();
        modifyFatField.setBounds(75, 145, 200, 20);
        modifySodiumLabel = new JLabel("Sodium");
        modifySodiumLabel.setBounds(15, 170, 50, 15);
        modifySodiumField = new JTextField();
        modifySodiumField.setBounds(75, 170, 200, 20);
        modifyPriceLabel = new JLabel("Price");
        modifyPriceLabel.setBounds(15, 195, 50, 15);
        modifyPriceField = new JTextField();
        modifyPriceField.setBounds(75, 195, 200, 20);
        modifyProductButton = new JButton("Modify Product");
        modifyProductButton.setBounds(75, 220, 200, 70);
        modifyProductButton.setEnabled(false);
        modifyPanel.add(modifyLabel);
        modifyPanel.add(modifyTitleLabel);
        modifyPanel.add(modifyTitleField);
        modifyPanel.add(modifyRatingLabel);
        modifyPanel.add(modifyRatingField);
        modifyPanel.add(modifyCaloriesLabel);
        modifyPanel.add(modifyCaloriesField);
        modifyPanel.add(modifyProteinLabel);
        modifyPanel.add(modifyProteinField);
        modifyPanel.add(modifyFatLabel);
        modifyPanel.add(modifyFatField);
        modifyPanel.add(modifySodiumLabel);
        modifyPanel.add(modifySodiumField);
        modifyPanel.add(modifyPriceLabel);
        modifyPanel.add(modifyPriceField);
        modifyPanel.add(modifyProductButton);
        contentPanel.add(modifyPanel);
        modifyPanel.setBounds(320, 220,  300, 300);
        modifyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    void initDeletePanel(){
        deletePanel = new JPanel(null);
        deleteLabel = new JLabel("Select products and press \"Delete Products\"");
        deleteLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        deleteLabel.setBounds(15, 10, 270, 15);
        deleteTitleLabel = new JLabel("Title");
        deleteTitleLabel.setBounds(15, 45, 50, 15);
        deleteTitleField = new JTextField();
        deleteTitleField.setEditable(false);
        deleteTitleField.setBounds(75, 45, 200, 20);
        deleteRatingLabel = new JLabel("Rating");
        deleteRatingLabel.setBounds(15, 70, 50, 15);
        deleteRatingField = new JTextField();
        deleteRatingField.setEditable(false);
        deleteRatingField.setBounds(75, 70, 200, 20);
        deleteCaloriesLabel = new JLabel("Calories");
        deleteCaloriesLabel.setBounds(15, 95, 50, 15);
        deleteCaloriesField = new JTextField();
        deleteCaloriesField.setEditable(false);
        deleteCaloriesField.setBounds(75, 95, 200, 20);
        deleteProteinLabel = new JLabel("Protein");
        deleteProteinLabel.setBounds(15, 120, 50, 15);
        deleteProteinField = new JTextField();
        deleteProteinField.setEditable(false);
        deleteProteinField.setBounds(75, 120, 200, 20);
        deleteFatLabel = new JLabel("Fat");
        deleteFatLabel.setBounds(15, 145, 50, 15);
        deleteFatField = new JTextField();
        deleteFatField.setEditable(false);
        deleteFatField.setBounds(75, 145, 200, 20);
        deleteSodiumLabel = new JLabel("Sodium");
        deleteSodiumLabel.setBounds(15, 170, 50, 15);
        deleteSodiumField = new JTextField();
        deleteSodiumField.setEditable(false);
        deleteSodiumField.setBounds(75, 170, 200, 20);
        deletePriceLabel = new JLabel("Price");
        deletePriceLabel.setBounds(15, 195, 50, 15);
        deletePriceField = new JTextField();
        deletePriceField.setEditable(false);
        deletePriceField.setBounds(75, 195, 200, 20);
        deleteProductButton = new JButton("Delete Products");
        deleteProductButton.setBounds(75, 220, 200, 70);
        deleteProductButton.setEnabled(false);
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteTitleLabel);
        deletePanel.add(deleteTitleField);
        deletePanel.add(deleteRatingLabel);
        deletePanel.add(deleteRatingField);
        deletePanel.add(deleteCaloriesLabel);
        deletePanel.add(deleteCaloriesField);
        deletePanel.add(deleteProteinLabel);
        deletePanel.add(deleteProteinField);
        deletePanel.add(deleteFatLabel);
        deletePanel.add(deleteFatField);
        deletePanel.add(deleteSodiumLabel);
        deletePanel.add(deleteSodiumField);
        deletePanel.add(deletePriceLabel);
        deletePanel.add(deletePriceField);
        deletePanel.add(deleteProductButton);
        contentPanel.add(deletePanel);
        deletePanel.setBounds(630, 220,  300, 300);
        deletePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void clearAddFields(){
        addTitleField.setText("");
        addRatingField.setText("");
        addCaloriesField.setText("");
        addProteinField.setText("");
        addFatField.setText("");
        addSodiumField.setText("");
        addPriceField.setText("");
    }

    public void clearModifyFields(){
        modifyTitleField.setText("");
        modifyRatingField.setText("");
        modifyCaloriesField.setText("");
        modifyProteinField.setText("");
        modifyFatField.setText("");
        modifySodiumField.setText("");
        modifyPriceField.setText("");
    }

    public void clearDeleteFields(){
        deleteTitleField.setText("");
        deleteRatingField.setText("");
        deleteCaloriesField.setText("");
        deleteProteinField.setText("");
        deleteFatField.setText("");
        deleteSodiumField.setText("");
        deletePriceField.setText("");
    }
    
}


