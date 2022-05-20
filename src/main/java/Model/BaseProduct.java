package Model;

public class BaseProduct extends Product{

    private static final long serialVersionUID = 1234567L;

    public BaseProduct(){}

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public double getRating() { return rating; }

    @Override
    public int getCalories() { return calories; }

    @Override
    public int getProtein() { return protein; }

    @Override
    public int getFat() { return fat; }

    @Override
    public int getSodium() { return sodium; }

    @Override
    public int getPrice() { return price; }


    public void setTitle(String title) { this.title = title; }

    public void setRating(double rating) { this.rating = rating; }

    public void setCalories(int calories) { this.calories = calories; }

    public void setProtein(int protein) { this.protein = protein; }

    public void setFat(int fat) { this.fat = fat; }

    public void setSodium(int sodium) { this.sodium = sodium; }

    public void setPrice(int price) { this.price = price; }


}
