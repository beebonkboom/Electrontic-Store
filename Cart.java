import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> cartInfo;
    private double total;

    // defult constuctor
    public Cart (){
        this.cartInfo = new HashMap<>();
        this.total = 0.0;
    }

    // method to store cart string info
    public String[] getViewCart () {
        String[] viewDisplay = new String [cartInfo.keySet().size()];
        int i = 0;
        for (Product p : this.cartInfo.keySet()) {
            if (p!=null & cartInfo.get(p) > 0 &&
                    cartInfo.get(p)+ " x " + p.toString() != null)

            {
                viewDisplay[i] = cartInfo.get(p)+ " x " + p.toString();
                i ++;
            }

        }

        return viewDisplay;
    }

    // method to get cart info
    public HashMap<Product, Integer> getCartInfo (){return cartInfo;}

    public void addCart(Product p){
        if (this.cartInfo.containsKey(p)){
            int amount = this.cartInfo.get(p);
            this.cartInfo.put(p, amount + 1);
        }else{
            this.cartInfo.put(p, 1);
        }
        addCart(p.getPrice());

    }

    public void removeCart(Product p){
        if (this.cartInfo.containsKey(p)){
            int amount = this.cartInfo.get(p);
            this.cartInfo.put(p, amount - 1);
        }
        subCart(p.getPrice());
    }

    public void addCart (double amount){
        this.total += amount;
    }
    public void subCart (double amount){
        this.total -= amount;
    }

    public double getTotal() {return total;}
}
