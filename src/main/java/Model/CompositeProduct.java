package Model;

import java.util.ArrayList;

public class CompositeProduct extends Product{
    public ArrayList<Product> products;

    public CompositeProduct(){
        products = new ArrayList<>();
    }

    private static final long serialVersionUID = 1234567L;

    @Override
    public String getTitle(){
        if(products.isEmpty())
            return "";
        String totalTitle = "";
        for(Product product : products){
            totalTitle += product.getTitle() + " | ";
        }
        totalTitle = totalTitle.substring(0, totalTitle.length() - 3);
        return totalTitle;
    }

    @Override
    public double getRating(){
        double totalRating = 0;
        for(Product product : products){
            totalRating += product.getRating();
        }
        totalRating /= products.size();
        return totalRating;
    }

    @Override
    public int getCalories() {
        int totalCalories = 0;
        for(Product product : products) {
            totalCalories += product.getCalories();
        }
        return totalCalories;
    }

    @Override
    public int getProtein() {
        int totalProtein = 0;
        for(Product product : products) {
            totalProtein += product.getProtein();
        }
        return totalProtein;
    }

    @Override
    public int getFat() {
        int totalFat = 0;
        for(Product product : products) {
            totalFat += product.getFat();
        }
        return totalFat;
    }

    @Override
    public int getSodium() {
        int totalSodium = 0;
        for(Product product : products) {
            totalSodium += product.getSodium();
        }
        return totalSodium;
    }

    @Override
    public int getPrice() {
        int totalPrice = 0;
        for(Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
