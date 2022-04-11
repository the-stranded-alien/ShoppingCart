package step1.ShoppingCart;

import java.util.HashMap;

public class Cart {

    public HashMap<String, Double> products;
    public HashMap<String, Double> cartItems;
    public HashMap<String, Integer> quantityMap;

    public Cart() {
        products = new HashMap<>();
        cartItems = new HashMap<>();
        quantityMap = new HashMap<>();
    }

    public void addProduct(String name, double unitPrice) {
        this.products.put(name, unitPrice);
    }

    public void addCartItem(String name, Integer quantity) {
        double unitPrice = this.products.get(name);
        if(this.cartItems.containsKey(name)) {
            Integer newQuan = quantity + this.quantityMap.get(name);
            this.quantityMap.put(name, newQuan);
            double subTotal = newQuan.doubleValue() * unitPrice;
            subTotal = (double) Math.round(subTotal * 100) / 100;
            this.cartItems.put(name, subTotal);
        } else {
            double subTotal = quantity.doubleValue() * unitPrice;
            subTotal = (double) Math.round(subTotal * 100) / 100;
            this.quantityMap.put(name, quantity);
            this.cartItems.put(name, subTotal);
        }
    }
    public HashMap<Double, Integer> showCartItem(String name) {
        double up = this.products.get(name);
        int qu = this.quantityMap.get(name);
        HashMap<Double, Integer> res = new HashMap<>();
        res.put(up, qu);
        return res;
    }

    public double showItemSubTotal(String name) {
        return this.cartItems.get(name);
    }
}
