package Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import Model.*;
public class Serializator {

    public static void serializeUsers(List<User> users, UserType userType) {
        try{
            FileOutputStream fileOut = null;
            switch(userType){
                case Client:
                    fileOut = new FileOutputStream("src/main/java/Data/Clients.ser");
                    break;
                case Employee:
                    fileOut = new FileOutputStream("src/main/java/Data/Employees.ser");
                    break;
                case Admin:
                    fileOut = new FileOutputStream("src/main/java/Data/Administrators.ser");
                    break;
                    
            }
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<User> deserializeUsers(UserType userType){
        ArrayList<User> users;
        File file;
        try{
            FileInputStream fileIn = null;
            switch(userType){
                case Client:
                    file = new File("src/main/java/Data/Clients.ser");
                    file.createNewFile();
                    fileIn = new FileInputStream("src/main/java/Data/Clients.ser");
                    break;
                case Employee:
                    file = new File("src/main/java/Data/Employees.ser");
                    file.createNewFile();
                    fileIn = new FileInputStream("src/main/java/Data/Employees.ser");
                    break;
                case Admin:
                    file = new File("src/main/java/Data/Administrators.ser");
                    file.createNewFile();
                    fileIn = new FileInputStream("src/main/java/Data/Administrators.ser");
                    break;
            }
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            users = new ArrayList<>();
        }
        return users;
    }

    public static void serializeMenu(List<Product> products){
        try{
            FileOutputStream fileOut = new FileOutputStream("src/main/java/Data/menu.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(products);
            out.close();
            fileOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<Product> deserializeMenu(){
        List<Product> products;
        File file;
        try{
            file = new File("src/main/java/Data/menu.ser");
            file.createNewFile();
            FileInputStream fileIn = new FileInputStream("src/main/java/Data/menu.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            products = (List<Product>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            products = new ArrayList<>();
        }
        return products;
    }

    public static void serializeOrders(Map<Order, List<Product>> orders){
        try{
            FileOutputStream fileOut = new FileOutputStream("src/main/java/Data/orders.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orders);
            out.close();
            fileOut.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Map<Order, List<Product>> deserializeOrders(){
        Map<Order, List<Product>> orders;
        File file;
        try{
            file = new File("src/main/java/Data/orders.ser");
            file.createNewFile();
            FileInputStream fileIn = new FileInputStream("src/main/java/Data/orders.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            orders = (Map<Order, List<Product>>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            orders = new Hashtable<>();
        }
        return orders;
    }
}
