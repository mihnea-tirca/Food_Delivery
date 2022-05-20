package Model;

import Data.Serializator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    public List<Product> menu;
    public Map<Order, List<Product>> orders;

    public DeliveryService(){
        menu = new ArrayList<>();
        orders = new Hashtable<>();
    }

    @Override
    public void importProducts(){
        try {
            menu = Files.lines(Paths.get("src/main/java/Data/products.csv")).
                    skip(1).
                    distinct().
                    map(l -> l.split(",")).
                    map(m -> new BaseProduct(m[0].trim(), Double.parseDouble(m[1]), Integer.parseInt(m[2]),
                    Integer.parseInt(m[3]), Integer.parseInt(m[4]), Integer.parseInt(m[5]), Integer.parseInt(m[6]))).
                    collect(Collectors.toList());
        } catch(IOException e){
            e.printStackTrace();
        }
        Serializator.serializeMenu(menu);
    }

    @Override
    public void addMenuProduct(Product product) {
        assert product != null;
        menu.add(product);
        Serializator.serializeMenu(menu);
    }

    @Override
    public void deleteMenuProductAt(int index) {
        assert index > 0;
        menu.remove(index);
        Serializator.serializeMenu(menu);
    }

    @Override
    public void modifyMenuProductAt(int index, String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert index > 0;
        Product product = menu.get(index);
        if(product instanceof BaseProduct){
            ((BaseProduct) product).setTitle(title);
            ((BaseProduct) product).setRating(rating);
            ((BaseProduct) product).setCalories(calories);
            ((BaseProduct) product).setProtein(protein);
            ((BaseProduct) product).setFat(fat);
            ((BaseProduct) product).setSodium(sodium);
            ((BaseProduct) product).setPrice(price);
        }
        else if(product instanceof CompositeProduct){

        }
        Serializator.serializeMenu(menu);
    }

    @Override
    public void addOrder(Order order, List<Product> products){
        assert order != null && products != null;
        orders.put(order, products);
        Serializator.serializeOrders(orders);
        setChanged();
        notifyObservers();
    }

    @Override
    public void removeOrder(Order order){
        assert order != null;
        orders.remove(order);
        Serializator.serializeOrders(orders);
    }

    @Override
    public void deleteOrders(){
        try {
            orders.clear();
            File file = new File("src/main/java/Data/orders.ser");
            file.createNewFile();
            file.delete();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
