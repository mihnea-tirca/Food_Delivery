package Model;

import java.util.List;

public interface IDeliveryServiceProcessing {
    void importProducts();

    /*
     @pre product != null
     */
    void addMenuProduct(Product product);

    /*
    @pre index >= 0
     */
    void deleteMenuProductAt(int index);

    /*
    @pre index >= 0
     */
    void modifyMenuProductAt(int index, String title, double rating, int calories, int protein, int fat, int sodium, int price);

    /*
    @pre order != null && products != null
     */
    void addOrder(Order order, List<Product> products);

    /*
    @pre order != null
     */
    void removeOrder(Order order);

    void deleteOrders();
}
