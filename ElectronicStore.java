//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    private String name;
    private Product[] stock; //Array to hold all products
    private double revenue;
    // cart class is exactly like customer class in A03 - just to store hashmap
    private Cart curCart;
    private int sales;
    private double average;
    private  List<Product> soldCart;


    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new Product[MAX_PRODUCTS];
        curProducts = 0;
        curCart = new Cart();
        sales = 0;
        average = 0.0;
        soldCart = new ArrayList<Product>();

    }

    // getters methods
    public String getName() {return this.name;}
    public Cart getCurCart() {return this.curCart;}
    public void setCurCart(Cart c) {this.curCart = c;}
    public double getRevenue () {return this.revenue;}
    public int getCurProducts () {return this.curProducts;}
    public int getSales(){return  this.sales;}
    public double getAverage(){return  this.average;}

    public void updateRevenue (double amount){
        revenue += amount;
    }

    public void updateSale (int amount){
        sales += amount;
    }

    public void updateAverage (double rev, int sales){
        average = (rev/sales);
    }
    public Product[] getStock() {return stock;}
    public List<Product> getSoldCart () {return this.soldCart;}

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        // if product is already in stock change sock quantity if not add to stock
        boolean found = false;
        for (Product p: stock){
            if (p != null && p.equals(newProduct)){
                p.setStockQuantity(p.getStockQuantity()+1);
                found = true;
            }
        }
        if (found == false && curProducts < MAX_PRODUCTS) {
            stock[curProducts] = newProduct;
            curProducts++;
            return true;
        }
        return false;
    }

    // method to sell products
    public boolean sellProduct(Product pro) {
        for (Product p : stock) {
            if (p.equals(pro)) {
                p.setStockQuantity(pro.getStockQuantity() - 1);
                return true;
            }
        }
        return false;

    }
    
    // to find matched products
    public Product searchProducts(String x) {
        String search = x.toLowerCase();
        search = search.substring(4);
        Product foundProduct = null;
        for (Product p: stock){
            if (p != null){
                if (p.toString().toLowerCase().contains(search)){
                    foundProduct = p;
                }
            }
        }
        return foundProduct;
    }
    public void sellCart(){
        for (Product p: curCart.getCartInfo().keySet()){
            for (Product pro: stock){
                if (pro!=null && p != null && pro.equals(p))
                    pro.setSoldQuantity(pro.getSoldQuantity()-1);
                    soldCart.add(pro);
            }

        }
    }

    public ArrayList<Product> calcPopular (){
        HashSet<Product> soldProducts = new HashSet<Product>(soldCart);
        ArrayList<Product> populars = new ArrayList<Product>();
        ArrayList<Product> topProducts = new ArrayList<Product>();

        for (Product p: soldProducts){
            populars.add(p);
        }


        Product largest = populars.get(1);

        for (int l =0; l<3; l ++) {
            for (int i = 0; i < populars.size(); i++) {
                if (populars.get(i) != null
                        && populars.get(i).getSoldQuantity() > largest.getSoldQuantity())
                    largest = populars.get(i);
            }
            populars.remove(largest);
            topProducts.add(largest);

        }

        return topProducts;
    }


    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 3, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 