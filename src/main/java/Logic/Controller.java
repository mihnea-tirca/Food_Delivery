package Logic;

import Data.Serializator;
import Model.*;
import View.*;

import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Controller{
    public UserTypeWindow userTypeWindow;
    public UserTypeWindowListener userTypeWindowListener = new UserTypeWindowListener();

    public ClientLogInWindow clientLogInWindow;
    public ClientLogInWindowListener clientLogInWindowListener = new ClientLogInWindowListener();
    public ClientWindow clientWindow;
    public ClientWindowListener clientWindowListener = new ClientWindowListener();

    public EmployeeLogInWindow employeeLogInWindow;
    public EmployeeLogInWindowListener employeeLogInWindowListener = new EmployeeLogInWindowListener();
    public EmployeeWindow employeeWindow;
    public EmployeeWindowListener employeeWindowListener = new EmployeeWindowListener();
    public ViewOrderWindow viewOrderWindow;

    public AdminLogInWindow adminLogInWindow;
    public AdminLogInWindowListener adminLogInWindowListener = new AdminLogInWindowListener();
    public AdminWindow adminWindow;
    public AdminWindowListener adminWindowListener = new AdminWindowListener();
    public ManageProductsWindow manageProductsWindow;
    public GenerateReportsWindow generateReportsWindow;
    public OrdersReportWindow ordersReportWindow;
    public ClientsReportWindow clientsReportWindow;
    public ProductsReportWindow productsReportWindow;

    public ArrayList<User> clients;
    public ArrayList<User> employees;
    public ArrayList<User> admins;

    DeliveryService deliveryService = new DeliveryService();

    public Controller(UserTypeWindow v){
        userTypeWindow = v;
        userTypeWindow.clientButton.addActionListener(userTypeWindowListener);
        userTypeWindow.adminButton.addActionListener(userTypeWindowListener);
        userTypeWindow.employeeButton.addActionListener(userTypeWindowListener);
        clients = (ArrayList<User>) Serializator.deserializeUsers(UserType.Client);
        employees = (ArrayList<User>) Serializator.deserializeUsers(UserType.Employee);
        admins = (ArrayList<User>) Serializator.deserializeUsers(UserType.Admin);
        deliveryService.menu = Serializator.deserializeMenu();
        deliveryService.orders = Serializator.deserializeOrders();
        employeeWindow = new EmployeeWindow();
        deliveryService.addObserver(employeeWindow);
//        employees = new ArrayList<>();
//        employees.add(new User("employee", "employee", UserType.Employee));
//        Serializator.serialize(employees, UserType.Employee);
//        employees = Serializator.deserialize(UserType.Employee);
//
//        admins = new ArrayList<>();
//        admins.add(new User("admin", "admin", UserType.Admin));
//        Serializator.serialize(admins, UserType.Admin);
//        admins = Serializator.deserialize(UserType.Admin);
    }

    public class UserTypeWindowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch(command){
                case "Client":
                    userTypeWindow.setVisible(false);
                    clientLogInWindow = new ClientLogInWindow();
                    clientLogInWindow.createAccountButton.addActionListener(clientLogInWindowListener);
                    clientLogInWindow.alreadyRegisteredButton.addActionListener(clientLogInWindowListener);
                    clientLogInWindow.logInButton.addActionListener(clientLogInWindowListener);
                    clientLogInWindow.registerButton.addActionListener(clientLogInWindowListener);
                    clientLogInWindow.backButton.addActionListener(clientLogInWindowListener);
                    break;
                case "Employee":
                    userTypeWindow.setVisible(false);
                    employeeLogInWindow = new EmployeeLogInWindow();
                    employeeLogInWindow.logInButton.addActionListener(employeeLogInWindowListener);
                    employeeLogInWindow.backButton.addActionListener(employeeLogInWindowListener);
                    break;
                case "Administrator":
                    userTypeWindow.setVisible(false);
                    adminLogInWindow = new AdminLogInWindow();
                    adminLogInWindow.logInButton.addActionListener(adminLogInWindowListener);
                    adminLogInWindow.backButton.addActionListener(adminLogInWindowListener);
                    break;
            }
        }
    }

    public class ClientLogInWindowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = clientLogInWindow.usernameField.getText();
            String password = String.valueOf(clientLogInWindow.passwordField.getPassword());
            String password2 = String.valueOf(clientLogInWindow.confirmPasswordField.getPassword());
            String command = e.getActionCommand();
            switch (command){
                case "Create Account":
                    clientLogInWindow.smallPanel.remove(0);
                    clientLogInWindow.smallPanel.add(clientLogInWindow.registerButton, 0);
                    clientLogInWindow.logInButton.setVisible(false);
                    clientLogInWindow.registerButton.setVisible(true);
                    clientLogInWindow.confirmPasswordField.setVisible(true);
                    clientLogInWindow.confirmPasswordLabel.setVisible(true);
                    clientLogInWindow.smallPanel.revalidate();
                    clientLogInWindow.smallPanel.repaint();
                    break;
                case "Already Registered?":
                    clientLogInWindow.smallPanel.remove(0);
                    clientLogInWindow.smallPanel.add(clientLogInWindow.logInButton, 0);
                    clientLogInWindow.registerButton.setVisible(false);
                    clientLogInWindow.logInButton.setVisible(true);
                    clientLogInWindow.confirmPasswordField.setVisible(false);
                    clientLogInWindow.confirmPasswordLabel.setVisible(false);
                    clientLogInWindow.smallPanel.revalidate();
                    clientLogInWindow.smallPanel.repaint();
                    break;
                case "Log In":
                    clientLogInWindow.validInputLabel.setText("");
                    if(clients.stream().anyMatch(user -> user.username.equals(username))){
                        User client = clients.stream().filter(user -> user.username.equals(username)).findAny().get();
                        clientLogInWindow.validInputLabel.setText("Successfully logged in");
                        clientLogInWindow.dispose();
                        clientWindowListener.filteredMenu = new ArrayList<>(deliveryService.menu);
                        clientWindow = new ClientWindow(client, deliveryService.menu);
                        clientWindow.addProductsButton.addActionListener(clientWindowListener);
                        clientWindow.viewBasketButton.addActionListener(clientWindowListener);
                        clientWindow.logOutButton.addActionListener(clientWindowListener);
                        clientWindow.deleteBasketProductsButton.addActionListener(clientWindowListener);
                        clientWindow.orderButton.addActionListener(clientWindowListener);
                        clientWindow.basketTableModel.addTableModelListener(clientWindowListener.clientBasketTableListener);
                        clientWindow.sortTitleField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.sortRatingField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.sortCaloriesField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.sortProteinField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.sortFatField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.sortSodiumField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.sortPriceField.getDocument().addDocumentListener(clientWindowListener.filterProductsListener);
                        clientWindow.lessRatingButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.equalRatingButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.greaterRatingButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.lessCaloriesButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.equalCaloriesButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.greaterCaloriesButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.lessProteinButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.equalProteinButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.greaterProteinButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.lessFatButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.equalFatButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.greaterFatButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.lessSodiumButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.equalSodiumButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.greaterSodiumButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.lessPriceButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.equalPriceButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.greaterPriceButton.addActionListener(clientWindowListener.filterProductsListener);
                        clientWindow.productsTable.getSelectionModel().addListSelectionListener(clientWindowListener.clientMenuSelectedRowListener);
                        clientWindow.basketTable.getSelectionModel().addListSelectionListener(clientWindowListener.clientBasketSelectedRowListener);
                    }
                    else{

                        clientLogInWindow.validInputLabel.setText("Wrong credentials!");
                    }

                    break;
                case "Register":
                    clientLogInWindow.validInputLabel.setText("");
                    switch(validCredentials(username, password, password2)){
                        case 0:
                            String userData = username + " " + password + " Client";
                            User user = new User(userData);
                            clients.add(user);
                            Serializator.serializeUsers(clients, UserType.Client);
                            clientLogInWindow.validInputLabel.setText("Successfully registered!");
                            break;
                        case 1:
                            clientLogInWindow.validInputLabel.setText("Passwords do not match!");
                            break;
                        case 2:
                            clientLogInWindow.validInputLabel.setText("Username and password can't contain spaces!");
                            break;
                        case 3:
                            clientLogInWindow.validInputLabel.setText("Fill all fields!");
                            break;
                        case 4:
                            clientLogInWindow.validInputLabel.setText("Username already exists!");
                            break;
                    }
                    break;
                case "Back":
                    clientLogInWindow.dispose();
                    userTypeWindow.setVisible(true);
                    break;
            }
        }
    }

    public class ClientWindowListener implements ActionListener{

        FilterProductsListener filterProductsListener = new FilterProductsListener();
        ClientMenuSelectedRowListener clientMenuSelectedRowListener = new ClientMenuSelectedRowListener();
        ClientBasketSelectedRowListener clientBasketSelectedRowListener = new ClientBasketSelectedRowListener();
        ClientBasketTableListener clientBasketTableListener = new ClientBasketTableListener();

        List<Product> filteredMenu;
        List<Product> basket = new ArrayList<>();

        public class ClientMenuSelectedRowListener implements ListSelectionListener{

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] rows = clientWindow.productsTable.getSelectedRows();
                clientWindow.addProductsButton.setEnabled(rows.length > 0);
                if(rows.length > 0) {
                    clientWindow.basketTable.clearSelection();
                }
            }
        }

        public class ClientBasketSelectedRowListener implements ListSelectionListener{

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] rows = clientWindow.basketTable.getSelectedRows();
                clientWindow.deleteBasketProductsButton.setEnabled(rows.length > 0);
                if(rows.length > 0) {
                    clientWindow.productsTable.clearSelection();
                }
            }
        }

        public class ClientBasketTableListener implements TableModelListener{

            @Override
            public void tableChanged(TableModelEvent e) {
                int noRows = clientWindow.basketTable.getRowCount();
                clientWindow.orderButton.setEnabled(noRows > 0);
            }
        }

        public class FilterProductsListener implements DocumentListener, ActionListener {

            public void sort(){
                filteredMenu = new ArrayList<>(deliveryService.menu);
                if(!Objects.equals(clientWindow.sortTitleField.getText(), "")){
                    filteredMenu = filteredMenu.stream().filter(product -> product.getTitle().toLowerCase().contains(clientWindow.sortTitleField.getText().toLowerCase())).collect(Collectors.toList());
                }
                if(!Objects.equals(clientWindow.sortRatingField.getText(), "")){
                    if(clientWindow.ratingButtonGroup.getSelection() == clientWindow.lessRatingButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getRating() < Double.parseDouble(clientWindow.sortRatingField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.ratingButtonGroup.getSelection() == clientWindow.equalRatingButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getRating() == Double.parseDouble(clientWindow.sortRatingField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.ratingButtonGroup.getSelection() == clientWindow.greaterRatingButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getRating() > Double.parseDouble(clientWindow.sortRatingField.getText())).collect(Collectors.toList());
                    }
                }
                if(!Objects.equals(clientWindow.sortCaloriesField.getText(), "")){
                    if(clientWindow.caloriesButtonGroup.getSelection() == clientWindow.lessCaloriesButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getCalories() < Integer.parseInt(clientWindow.sortCaloriesField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.caloriesButtonGroup.getSelection() == clientWindow.equalCaloriesButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getCalories() == Integer.parseInt(clientWindow.sortCaloriesField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.caloriesButtonGroup.getSelection() == clientWindow.greaterCaloriesButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getCalories() > Integer.parseInt(clientWindow.sortCaloriesField.getText())).collect(Collectors.toList());
                    }
                }
                if(!Objects.equals(clientWindow.sortProteinField.getText(), "")){
                    if(clientWindow.proteinButtonGroup.getSelection() == clientWindow.lessProteinButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getProtein() < Integer.parseInt(clientWindow.sortProteinField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.proteinButtonGroup.getSelection() == clientWindow.equalProteinButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getProtein() == Integer.parseInt(clientWindow.sortProteinField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.proteinButtonGroup.getSelection() == clientWindow.greaterProteinButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getProtein() > Integer.parseInt(clientWindow.sortProteinField.getText())).collect(Collectors.toList());
                    }
                }
                if(!Objects.equals(clientWindow.sortFatField.getText(), "")){
                    if(clientWindow.fatButtonGroup.getSelection() == clientWindow.lessFatButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getFat() < Integer.parseInt(clientWindow.sortFatField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.fatButtonGroup.getSelection() == clientWindow.equalFatButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getFat() == Integer.parseInt(clientWindow.sortFatField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.fatButtonGroup.getSelection() == clientWindow.greaterFatButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getFat() > Integer.parseInt(clientWindow.sortFatField.getText())).collect(Collectors.toList());
                    }
                }
                if(!Objects.equals(clientWindow.sortSodiumField.getText(), "")){
                    if(clientWindow.sodiumButtonGroup.getSelection() == clientWindow.lessSodiumButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getSodium() < Integer.parseInt(clientWindow.sortSodiumField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.sodiumButtonGroup.getSelection() == clientWindow.equalSodiumButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getSodium() == Integer.parseInt(clientWindow.sortSodiumField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.sodiumButtonGroup.getSelection() == clientWindow.greaterSodiumButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getSodium() > Integer.parseInt(clientWindow.sortSodiumField.getText())).collect(Collectors.toList());
                    }
                }
                if(!Objects.equals(clientWindow.sortPriceField.getText(), "")){
                    if(clientWindow.priceButtonGroup.getSelection() == clientWindow.lessPriceButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getPrice() < Integer.parseInt(clientWindow.sortPriceField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.priceButtonGroup.getSelection() == clientWindow.equalPriceButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getPrice() == Integer.parseInt(clientWindow.sortPriceField.getText())).collect(Collectors.toList());
                    }
                    else if(clientWindow.priceButtonGroup.getSelection() == clientWindow.greaterPriceButton.getModel()) {
                        filteredMenu = filteredMenu.stream().filter(product -> product.getPrice() > Integer.parseInt(clientWindow.sortPriceField.getText())).collect(Collectors.toList());
                    }
                }
                clientWindow.modifyTable(filteredMenu);
            }



            @Override
            public void insertUpdate(DocumentEvent e) {
                sort();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                sort();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                sort();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                sort();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] rows;
            Product product;
            String command = e.getActionCommand();
            switch(command){
                case "Add Products":
                    clientWindow.billTextArea.setText("");
                    rows = clientWindow.productsTable.getSelectedRows();
                    for(int row : rows){
                        product = filteredMenu.get(row);
                        basket.add(product);
                        clientWindow.basketTableModel.addRow(new Object[]{product.getTitle(), product.getRating(),
                                product.getCalories(), product.getProtein(), product.getFat(), product.getSodium(),
                                product.getPrice()});
                    }
                    break;
                case "View Basket":
                    clientWindow.setSize(1240, 500);
                    clientWindow.viewBasketButton.setText("Hide Basket");
                    break;
                case "Hide Basket":
                    clientWindow.setSize(630, 500);
                    clientWindow.viewBasketButton.setText("View Basket");
                case "Delete Basket Products":
                    rows = clientWindow.basketTable.getSelectedRows();
                    for(int i = 0; i < rows.length; i++){
                        basket.remove(rows[i] - i);
                        clientWindow.basketTableModel.removeRow(rows[i] - i);
                    }
                    break;
                case "Order":
                    Order order = new Order(clientWindow.client.username, LocalDate.now(), LocalTime.now());
                    deliveryService.addOrder(order, new ArrayList<>(basket));
                    clientWindow.generateBill();
                    clientWindow.basketTableModel.setRowCount(0);
                    new SuccessWindow("Order successfully placed");
                    basket.clear();
                    break;
                case "Log Out":
                    clientWindow.dispose();
                    userTypeWindow.setVisible(true);
                    break;
            }
        }

    }

    public class EmployeeLogInWindowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = employeeLogInWindow.usernameField.getText();
            String password = String.valueOf(employeeLogInWindow.passwordField.getPassword());
            String command = e.getActionCommand();
            switch (command){
                case "Log In":
                    employeeLogInWindow.validInputLabel.setText("");
                    User employee = new User(username, password, UserType.Employee);
                    if(employees.contains(employee)){
                        employeeLogInWindow.validInputLabel.setText("Successfully logged in");
                        employeeLogInWindow.dispose();
                        employeeWindow = new EmployeeWindow(deliveryService.orders);
                        employeeWindow.viewOrderButton.addActionListener(employeeWindowListener);
                        employeeWindow.logOutButton.addActionListener(employeeWindowListener);
                        employeeWindow.ordersTable.getSelectionModel().addListSelectionListener(employeeWindowListener.employeeOrdersSelectedRowListener);
                    }
                    else{
                        employeeLogInWindow.validInputLabel.setText("Wrong credentials!");
                    }
                    break;
                case "Back":
                    employeeLogInWindow.setVisible(false);
                    userTypeWindow.setVisible(true);
                    break;
            }
        }
    }

    public class EmployeeWindowListener implements ActionListener{

        public EmployeeOrdersSelectedRowListener employeeOrdersSelectedRowListener = new EmployeeOrdersSelectedRowListener();
        public ViewOrderWindowListener viewOrderWindowListener = new ViewOrderWindowListener();

        class EmployeeOrdersSelectedRowListener implements ListSelectionListener{

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] rows = employeeWindow.ordersTable.getSelectedRows();
                employeeWindow.viewOrderButton.setEnabled(rows.length == 1);
            }
        }

        class ViewOrderWindowListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                switch (command){
                    case "Deliver":
                        int row = employeeWindow.ordersTable.getSelectedRow();
                        Order order = new Order((String) employeeWindow.ordersTable.getValueAt(row, 0),
                                (LocalDate) employeeWindow.ordersTable.getValueAt(row, 1),
                                (LocalTime) employeeWindow.ordersTable.getValueAt(row, 2));
                        deliveryService.removeOrder(order);
                        employeeWindow.setVisible(true);
                        employeeWindow.tableModel.removeRow(row);
                        viewOrderWindow.dispose();
                        break;
                    case "Back":
                        viewOrderWindow.dispose();
                        employeeWindow.setVisible(true);
                        break;
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch(command){
                case "View Order":
                    employeeWindow.setVisible(false);
                    int row = employeeWindow.ordersTable.getSelectedRow();
                    Order order = new Order((String) employeeWindow.ordersTable.getValueAt(row, 0),
                            (LocalDate) employeeWindow.ordersTable.getValueAt(row, 1),
                            (LocalTime) employeeWindow.ordersTable.getValueAt(row, 2));
                    List<Product> products = deliveryService.orders.get(order);
                    viewOrderWindow = new ViewOrderWindow(products);
                    viewOrderWindow.deliverButton.addActionListener(viewOrderWindowListener);
                    viewOrderWindow.backButton.addActionListener(viewOrderWindowListener);
                    break;
                case "Log Out":
                    employeeWindow.dispose();
                    userTypeWindow.setVisible(true);
                    break;
            }
        }
    }

    public class AdminLogInWindowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminLogInWindow.usernameField.getText();
            String password = String.valueOf(adminLogInWindow.passwordField.getPassword());
            String command = e.getActionCommand();
            switch (command){
                case "Log In":
                    adminLogInWindow.validInputLabel.setText("");
                    User admin = new User(username, password, UserType.Admin);
                    if(admins.contains(admin)){
                        adminLogInWindow.dispose();
                        adminWindow = new AdminWindow();
                        adminWindow.importButton.addActionListener(adminWindowListener);
                        adminWindow.manageProductsButton.addActionListener(adminWindowListener);
                        adminWindow.generateReportButton.addActionListener(adminWindowListener);
                        adminWindow.logOutButton.addActionListener(adminWindowListener);
                    }
                    else{
                        adminLogInWindow.validInputLabel.setText("Wrong credentials!");
                    }
                    break;
                case "Back":
                    adminLogInWindow.setVisible(false);
                    userTypeWindow.setVisible(true);
                    break;
            }
        }
    }

    public class AdminWindowListener implements ActionListener{

        public ManageProductsWindowListener manageProductsWindowListener = new ManageProductsWindowListener();
        public GenerateReportsWindowListener generateReportsWindowListener = new GenerateReportsWindowListener();

        public class ManageProductsWindowListener implements ActionListener{

            public AdminSelectedRowListener adminSelectedRowListener = new AdminSelectedRowListener();

            public class AdminSelectedRowListener implements ListSelectionListener {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    try{
                        int[] rows = manageProductsWindow.productsTable.getSelectedRows();
                        if(rows.length != 1 || deliveryService.menu.get(rows[0]) instanceof CompositeProduct){
                            manageProductsWindow.modifyTitleField.setEditable(false);
                            manageProductsWindow.modifyRatingField.setEditable(false);
                            manageProductsWindow.modifyCaloriesField.setEditable(false);
                            manageProductsWindow.modifyProteinField.setEditable(false);
                            manageProductsWindow.modifyFatField.setEditable(false);
                            manageProductsWindow.modifySodiumField.setEditable(false);
                            manageProductsWindow.modifyPriceField.setEditable(false);
                            manageProductsWindow.modifyProductButton.setEnabled(false);
                        }
                        else {
                            manageProductsWindow.modifyTitleField.setEditable(true);
                            manageProductsWindow.modifyRatingField.setEditable(true);
                            manageProductsWindow.modifyCaloriesField.setEditable(true);
                            manageProductsWindow.modifyProteinField.setEditable(true);
                            manageProductsWindow.modifyFatField.setEditable(true);
                            manageProductsWindow.modifySodiumField.setEditable(true);
                            manageProductsWindow.modifyPriceField.setEditable(true);
                            manageProductsWindow.modifyProductButton.setEnabled(true);
                            manageProductsWindow.modifyTitleField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 0).toString());
                            manageProductsWindow.modifyRatingField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 1).toString());
                            manageProductsWindow.modifyCaloriesField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 2).toString());
                            manageProductsWindow.modifyProteinField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 3).toString());
                            manageProductsWindow.modifyFatField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 4).toString());
                            manageProductsWindow.modifySodiumField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 5).toString());
                            manageProductsWindow.modifyPriceField.setText(manageProductsWindow.productsTable.getValueAt(rows[0], 6).toString());
                        }
                        if(rows.length == 0){
                            manageProductsWindow.deleteProductButton.setEnabled(false);
                        }
                        else {
                            CompositeProduct product = new CompositeProduct();
                            for(int row : rows){
                                product.products.add(deliveryService.menu.get(row));
                                manageProductsWindow.deleteTitleField.setText(product.getTitle());
                                manageProductsWindow.deleteRatingField.setText(product.getRating() + "");
                                manageProductsWindow.deleteCaloriesField.setText(product.getCalories() + "");
                                manageProductsWindow.deleteProteinField.setText(product.getProtein() + "");
                                manageProductsWindow.deleteFatField.setText(product.getFat() + "");
                                manageProductsWindow.deleteSodiumField.setText(product.getSodium() + "");
                                manageProductsWindow.deletePriceField.setText(product.getPrice() + "");
                            }
                            manageProductsWindow.deleteProductButton.setEnabled(true);
                        }
                        if(rows.length > 1){
                            CompositeProduct product = new CompositeProduct();
                            for(int row : rows){
                                product.products.add(deliveryService.menu.get(row));
                                manageProductsWindow.addTitleField.setText(product.getTitle());
                                manageProductsWindow.addRatingField.setText(product.getRating() + "");
                                manageProductsWindow.addCaloriesField.setText(product.getCalories() + "");
                                manageProductsWindow.addProteinField.setText(product.getProtein() + "");
                                manageProductsWindow.addFatField.setText(product.getFat() + "");
                                manageProductsWindow.addSodiumField.setText(product.getSodium() + "");
                                manageProductsWindow.addPriceField.setText(product.getPrice() + "");
                            }
                            manageProductsWindow.deleteProductButton.setEnabled(true);
                            manageProductsWindow.addTitleField.setEditable(false);
                            manageProductsWindow.addRatingField.setEditable(false);
                            manageProductsWindow.addCaloriesField.setEditable(false);
                            manageProductsWindow.addProteinField.setEditable(false);
                            manageProductsWindow.addFatField.setEditable(false);
                            manageProductsWindow.addSodiumField.setEditable(false);
                            manageProductsWindow.addPriceField.setEditable(false);
                            manageProductsWindow.addPanel.remove(manageProductsWindow.addBaseProductButton);
                            manageProductsWindow.addPanel.add(manageProductsWindow.addCompositeProductButton);
                            manageProductsWindow.addBaseProductButton.setVisible(false);
                            manageProductsWindow.addCompositeProductButton.setVisible(true);
                            manageProductsWindow.addPanel.revalidate();
                            manageProductsWindow.addPanel.repaint();
                        }
                        else{
                            manageProductsWindow.addTitleField.setEditable(true);
                            manageProductsWindow.addRatingField.setEditable(true);
                            manageProductsWindow.addCaloriesField.setEditable(true);
                            manageProductsWindow.addProteinField.setEditable(true);
                            manageProductsWindow.addFatField.setEditable(true);
                            manageProductsWindow.addSodiumField.setEditable(true);
                            manageProductsWindow.addPriceField.setEditable(true);
                            manageProductsWindow.addPanel.remove(manageProductsWindow.addCompositeProductButton);
                            manageProductsWindow.addPanel.add(manageProductsWindow.addBaseProductButton);
                            manageProductsWindow.addBaseProductButton.setVisible(true);
                            manageProductsWindow.addCompositeProductButton.setVisible(false);
                            manageProductsWindow.addPanel.revalidate();
                            manageProductsWindow.addPanel.repaint();
                        }
                    } catch(ArrayIndexOutOfBoundsException ignored){}
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows;
                String title, rating, calories, protein, fat, sodium, price;
                String command = e.getActionCommand();
                switch(command){
                    case "Add Base Product":
                        title = manageProductsWindow.addTitleField.getText();
                        rating = manageProductsWindow.addRatingField.getText();
                        calories = manageProductsWindow.addCaloriesField.getText();
                        protein = manageProductsWindow.addProteinField.getText();
                        fat = manageProductsWindow.addFatField.getText();
                        sodium = manageProductsWindow.addSodiumField.getText();
                        price = manageProductsWindow.addPriceField.getText();
                        if(!validProductInput(title, rating, calories, protein, fat, sodium, price)){
                            new FailureWindow("Invalid Input");
                        }
                        else{
                            Product product = new BaseProduct(title, Double.parseDouble(rating), Integer.parseInt(calories),
                                    Integer.parseInt(protein), Integer.parseInt(fat), Integer.parseInt(sodium), Integer.parseInt(price));
                            deliveryService.addMenuProduct(product);
                            manageProductsWindow.tableModel.addRow(new Object[]{product.getTitle(), product.getRating(),
                                    product.getCalories(), product.getProtein(), product.getFat(), product.getSodium(),
                                    product.getPrice()});
                            new SuccessWindow("Successfully added base product");
                        }
                        break;
                    case "Add Composite Product":
                        rows = manageProductsWindow.productsTable.getSelectedRows();
                        CompositeProduct compositeProduct = new CompositeProduct();
                        for(int row: rows){
                            compositeProduct.products.add(deliveryService.menu.get(row));
                        }
                        deliveryService.addMenuProduct(compositeProduct);
                        manageProductsWindow.tableModel.addRow(new Object[]{compositeProduct.getTitle(), compositeProduct.getRating(),
                                compositeProduct.getCalories(), compositeProduct.getProtein(), compositeProduct.getFat(),
                                compositeProduct.getSodium(), compositeProduct.getPrice()});
                        new SuccessWindow("<html>Successfully added<br>composite product</html>");
                        break;
                    case "Modify Product":
                        int row = manageProductsWindow.productsTable.getSelectedRow();
                        title = manageProductsWindow.modifyTitleField.getText();
                        rating = manageProductsWindow.modifyRatingField.getText();
                        calories = manageProductsWindow.modifyCaloriesField.getText();
                        protein = manageProductsWindow.modifyProteinField.getText();
                        fat = manageProductsWindow.modifyFatField.getText();
                        sodium = manageProductsWindow.modifySodiumField.getText();
                        price = manageProductsWindow.modifyPriceField.getText();
                        if(!validProductInput(title, rating, calories, protein, fat, sodium, price)) {
                            new FailureWindow("Invalid Input");
                        }
                        else{
                            deliveryService.modifyMenuProductAt(row, title, Double.parseDouble(rating), Integer.parseInt(calories),
                                    Integer.parseInt(protein), Integer.parseInt(fat), Integer.parseInt(sodium), Integer.parseInt(price));
                            manageProductsWindow.tableModel.setValueAt(title, row, 0);
                            manageProductsWindow.tableModel.setValueAt(rating, row, 1);
                            manageProductsWindow.tableModel.setValueAt(calories, row, 2);
                            manageProductsWindow.tableModel.setValueAt(protein, row, 3);
                            manageProductsWindow.tableModel.setValueAt(fat, row, 4);
                            manageProductsWindow.tableModel.setValueAt(sodium, row, 5);
                            manageProductsWindow.tableModel.setValueAt(price, row, 6);
                            manageProductsWindow.productsTable.clearSelection();
                        }
                        break;
                    case "Delete Products":
                        rows = manageProductsWindow.productsTable.getSelectedRows();
                        for(int i = 0; i < rows.length; i++){
                            deliveryService.menu.remove(rows[i] - i);
                            manageProductsWindow.tableModel.removeRow(rows[i] - i);
                        }
                        Serializator.serializeMenu(deliveryService.menu);
                        manageProductsWindow.clearDeleteFields();
                        break;
                    case "Back":
                        manageProductsWindow.dispose();
                        adminWindow.setVisible(true);
                }
            }
        }

        public class GenerateReportsWindowListener implements ActionListener{

            InputListener inputListener = new InputListener();
            OrdersReportWindowListener ordersReportWindowListener = new OrdersReportWindowListener();
            ClientsReportWindowListener clientsReportWindowListener = new ClientsReportWindowListener();
            ProductsReportWindowListener productsReportWindowListener = new ProductsReportWindowListener();

            public class InputListener implements DocumentListener{

                void updateFields(){
                    if(generateReportsWindow.startHourField.getText().equals("") &&
                            generateReportsWindow.endHourField.getText().equals("") &&
                            generateReportsWindow.clientOrderedMoreThanField.getText().equals("") &&
                            generateReportsWindow.clientPriceHigherThanField.getText().equals("") &&
                            generateReportsWindow.productsOrderedMoreThanField.getText().equals("") &&
                            generateReportsWindow.productsOrderedInDayField.getText().equals("")){
                        generateReportsWindow.startHourField.setEnabled(true);
                        generateReportsWindow.endHourField.setEnabled(true);
                        generateReportsWindow.clientOrderedMoreThanField.setEnabled(true);
                        generateReportsWindow.clientPriceHigherThanField.setEnabled(true);
                        generateReportsWindow.productsOrderedMoreThanField.setEnabled(true);
                        generateReportsWindow.productsOrderedInDayField.setEnabled(true);
                        generateReportsWindow.generateReportButton.setEnabled(false);
                    }
                    if(!generateReportsWindow.startHourField.getText().equals("") ||
                            !generateReportsWindow.endHourField.getText().equals("")){
                        generateReportsWindow.clientOrderedMoreThanField.setEnabled(false);
                        generateReportsWindow.clientPriceHigherThanField.setEnabled(false);
                        generateReportsWindow.productsOrderedMoreThanField.setEnabled(false);
                        generateReportsWindow.productsOrderedInDayField.setEnabled(false);
                        try{
                            LocalTime.parse(generateReportsWindow.startHourField.getText());
                            LocalTime.parse(generateReportsWindow.endHourField.getText());
                            generateReportsWindow.generateReportButton.setEnabled(true);
                        } catch(DateTimeException e){
                            generateReportsWindow.generateReportButton.setEnabled(false);
                        }
                    }

                    if(!generateReportsWindow.clientOrderedMoreThanField.getText().equals("")){
                        generateReportsWindow.startHourField.setEnabled(false);
                        generateReportsWindow.endHourField.setEnabled(false);
                        generateReportsWindow.productsOrderedMoreThanField.setEnabled(false);
                        generateReportsWindow.productsOrderedInDayField.setEnabled(false);
                        try{
                            Integer.parseInt(generateReportsWindow.clientOrderedMoreThanField.getText());
                            generateReportsWindow.generateReportButton.setEnabled(true);
                        } catch(NumberFormatException e){
                            generateReportsWindow.generateReportButton.setEnabled(false);
                        }
                    }
                    if(!generateReportsWindow.clientPriceHigherThanField.getText().equals("")){
                        generateReportsWindow.startHourField.setEnabled(false);
                        generateReportsWindow.endHourField.setEnabled(false);
                        generateReportsWindow.productsOrderedMoreThanField.setEnabled(false);
                        generateReportsWindow.productsOrderedInDayField.setEnabled(false);
                        try{
                            Double.parseDouble(generateReportsWindow.clientPriceHigherThanField.getText());
                            generateReportsWindow.generateReportButton.setEnabled(true);
                        } catch(NumberFormatException e) {
                            generateReportsWindow.generateReportButton.setEnabled(false);
                        }
                    }

                    if(!generateReportsWindow.productsOrderedMoreThanField.getText().equals("")){
                        generateReportsWindow.startHourField.setEnabled(false);
                        generateReportsWindow.endHourField.setEnabled(false);
                        generateReportsWindow.clientOrderedMoreThanField.setEnabled(false);
                        generateReportsWindow.clientPriceHigherThanField.setEnabled(false);
                        try{
                            Integer.parseInt(generateReportsWindow.productsOrderedMoreThanField.getText());
                            generateReportsWindow.generateReportButton.setEnabled(true);
                        } catch(NumberFormatException | DateTimeException e){
                            generateReportsWindow.generateReportButton.setEnabled(false);
                        }
                    }
                    if(!generateReportsWindow.productsOrderedInDayField.getText().equals("")){
                        generateReportsWindow.startHourField.setEnabled(false);
                        generateReportsWindow.endHourField.setEnabled(false);
                        generateReportsWindow.clientOrderedMoreThanField.setEnabled(false);
                        generateReportsWindow.clientPriceHigherThanField.setEnabled(false);
                        try{
                            LocalDate.parse(generateReportsWindow.productsOrderedInDayField.getText());
                            generateReportsWindow.generateReportButton.setEnabled(true);
                        } catch(NumberFormatException | DateTimeException e){
                            generateReportsWindow.generateReportButton.setEnabled(false);
                        }
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateFields();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateFields();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateFields();
                }
            }

            public class OrdersReportWindowListener implements ActionListener{

                OrdersSelectedRowListener ordersSelectedRowListener = new OrdersSelectedRowListener();

                ViewOrderWindowListener viewOrderWindowListener = new ViewOrderWindowListener();

                class OrdersSelectedRowListener implements ListSelectionListener{

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int[] rows = ordersReportWindow.ordersTable.getSelectedRows();
                        ordersReportWindow.viewOrderButton.setEnabled(rows.length == 1);
                    }
                }

                class ViewOrderWindowListener implements ActionListener{

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = e.getActionCommand();
                        switch(command){
                            case "Back":
                                viewOrderWindow.dispose();
                                ordersReportWindow.setVisible(true);
                                break;
                        }
                    }
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    switch (command){
                        case "View Order":
                            ordersReportWindow.setVisible(false);
                            int row = ordersReportWindow.ordersTable.getSelectedRow();
                            Order order = new Order((String) ordersReportWindow.ordersTable.getValueAt(row, 0),
                                    (LocalDate) ordersReportWindow.ordersTable.getValueAt(row, 1),
                                    (LocalTime) ordersReportWindow.ordersTable.getValueAt(row, 2));
                            List<Product> products = deliveryService.orders.get(order);
                            viewOrderWindow = new ViewOrderWindow(products);
                            viewOrderWindow.deliverButton.setVisible(false);
                            viewOrderWindow.backButton.addActionListener(viewOrderWindowListener);
                            break;
                        case "Back":
                            ordersReportWindow.dispose();
                            generateReportsWindow.setVisible(true);
                            break;
                    }
                }
            }

            public class ClientsReportWindowListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    switch(command){
                        case "Back":
                            clientsReportWindow.dispose();
                            generateReportsWindow.setVisible(true);
                            break;
                    }
                }
            }

            public class ProductsReportWindowListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    switch(command){
                        case "Back":
                            productsReportWindow.dispose();
                            generateReportsWindow.setVisible(true);
                            break;
                    }
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                switch(command){
                    case "Generate Report":
                        generateReportsWindow.setVisible(false);
                        if(!generateReportsWindow.startHourField.getText().equals("")){
                            ordersReportWindow = new OrdersReportWindow(deliveryService.orders,
                                    LocalTime.parse(generateReportsWindow.startHourField.getText()),
                                    LocalTime.parse(generateReportsWindow.endHourField.getText()));
                            ordersReportWindow.viewOrderButton.addActionListener(ordersReportWindowListener);
                            ordersReportWindow.backButton.addActionListener(ordersReportWindowListener);
                            ordersReportWindow.ordersTable.getSelectionModel().addListSelectionListener(ordersReportWindowListener.ordersSelectedRowListener);
                        }
                        else if(!(generateReportsWindow.clientOrderedMoreThanField.getText().equals("") &&
                                generateReportsWindow.clientPriceHigherThanField.getText().equals(""))){
                            boolean field1 = !generateReportsWindow.clientOrderedMoreThanField.getText().equals("");
                            boolean field2 = !generateReportsWindow.clientPriceHigherThanField.getText().equals("");
                            int param1;
                            double param2;
                            if(field1 && field2){
                                param1 = Integer.parseInt(generateReportsWindow.clientOrderedMoreThanField.getText());
                                param2 = Double.parseDouble(generateReportsWindow.clientPriceHigherThanField.getText());
                            }
                            else if(field1){
                                param1 = Integer.parseInt(generateReportsWindow.clientOrderedMoreThanField.getText());
                                param2 = 0;
                            }
                            else{
                                param1 = 0;
                                param2 = Double.parseDouble(generateReportsWindow.clientPriceHigherThanField.getText());
                            }
                            clientsReportWindow = new ClientsReportWindow(deliveryService.orders, param1, param2);
                            clientsReportWindow.backButton.addActionListener(clientsReportWindowListener);
                        }
                        else{
                            boolean field1 = !generateReportsWindow.productsOrderedMoreThanField.getText().equals("");
                            boolean field2 = !generateReportsWindow.productsOrderedInDayField.getText().equals("");
                            if(field1 && field2){
                                productsReportWindow = new ProductsReportWindow(deliveryService.orders,
                                        Integer.parseInt(generateReportsWindow.productsOrderedMoreThanField.getText()),
                                        LocalDate.parse(generateReportsWindow.productsOrderedInDayField.getText()));
                            }
                            else if(field1){
                                productsReportWindow = new ProductsReportWindow(deliveryService.orders,
                                        Integer.parseInt(generateReportsWindow.productsOrderedMoreThanField.getText()),
                                        null);
                            }
                            else{
                                productsReportWindow = new ProductsReportWindow(deliveryService.orders,
                                        0,
                                        LocalDate.parse(generateReportsWindow.productsOrderedInDayField.getText()));
                            }
                            productsReportWindow.backButton.addActionListener(productsReportWindowListener);
                        }
                        break;
                    case "Back":
                        generateReportsWindow.dispose();
                        adminWindow.setVisible(true);
                        break;
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch(command){
                case "Import Products":
                    deliveryService.deleteOrders();
                    deliveryService.importProducts();
                    new SuccessWindow("Successfully imported products");
                    break;
                case "Manage Products":
                    adminWindow.setVisible(false);
                    manageProductsWindow = new ManageProductsWindow(deliveryService.menu);
                    manageProductsWindow.addBaseProductButton.addActionListener(manageProductsWindowListener);
                    manageProductsWindow.addCompositeProductButton.addActionListener(manageProductsWindowListener);
                    manageProductsWindow.modifyProductButton.addActionListener(manageProductsWindowListener);
                    manageProductsWindow.deleteProductButton.addActionListener(manageProductsWindowListener);
                    manageProductsWindow.backButton.addActionListener(manageProductsWindowListener);
                    manageProductsWindow.productsTable.getSelectionModel().addListSelectionListener(manageProductsWindowListener.adminSelectedRowListener);
                    break;
                case "Generate Reports":
                    adminWindow.setVisible(false);
                    generateReportsWindow = new GenerateReportsWindow();
                    generateReportsWindow.generateReportButton.addActionListener(generateReportsWindowListener);
                    generateReportsWindow.backButton.addActionListener(generateReportsWindowListener);
                    generateReportsWindow.startHourField.getDocument().addDocumentListener(generateReportsWindowListener.inputListener);
                    generateReportsWindow.endHourField.getDocument().addDocumentListener(generateReportsWindowListener.inputListener);
                    generateReportsWindow.clientOrderedMoreThanField.getDocument().addDocumentListener(generateReportsWindowListener.inputListener);
                    generateReportsWindow.clientPriceHigherThanField.getDocument().addDocumentListener(generateReportsWindowListener.inputListener);
                    generateReportsWindow.productsOrderedMoreThanField.getDocument().addDocumentListener(generateReportsWindowListener.inputListener);
                    generateReportsWindow.productsOrderedInDayField.getDocument().addDocumentListener(generateReportsWindowListener.inputListener);
                    break;
                case "Log Out":
                    adminWindow.dispose();
                    userTypeWindow.setVisible(true);
                    break;
            }
        }
    }

    public boolean userExists(String username){
        for(User user : clients){
            if(user.username.equals(username)) {
                return true;
            }
        }
        for(User user : employees){
            if(user.username.equals(username)) {
                return true;
            }
        }
        for(User user : admins){
            if(user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public int validCredentials(String username, String password, String password2){
        if(!password.equals(password2))
            return 1; // parole diferite
        else if(username.contains(" ") || password.contains(" "))
            return 2; // contine spatii
        else if(password.equals("") || username.equals(""))
            return 3; // nu toate campurile au fost completate
        else if(userExists(username))
            return 4; // username-ul exista deja
        else return 0; // OK!
    }

    public boolean validProductInput(String title, String ratingString, String caloriesString, String proteinString,
                                     String fatString, String sodiumString, String priceString){
        try{
            Double.parseDouble(ratingString);
            Integer.parseInt(caloriesString);
            Integer.parseInt(proteinString);
            Integer.parseInt(fatString);
            Integer.parseInt(sodiumString);
            Integer.parseInt(priceString);
            return !title.isEmpty() && !ratingString.isEmpty() && !caloriesString.isEmpty() && !proteinString.isEmpty() &&
                    !fatString.isEmpty() && !sodiumString.isEmpty() && !priceString.isEmpty();
        } catch(NumberFormatException e){
            return false;
        }
    }

}
