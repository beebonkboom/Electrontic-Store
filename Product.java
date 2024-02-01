//Base class for all products the store will sell
public abstract class Product implements Comparable<Product> {
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }
    public void setSoldQuantity(int sold) {
        this.soldQuantity = sold;
    }
    public void setStockQuantity (int stock){
        this.stockQuantity = stock;
    }

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }


    @Override
    public int compareTo(Product p) {
        if (p == null) {
            throw new NullPointerException("null is not comparable");
        }
        else if (this != null & p != null && this.soldQuantity == p.soldQuantity) {
            return 0;
        } else if (this != null & p != null && this.soldQuantity < p.getSoldQuantity()) {
            return -1;
        }
        return 1;
    }
}