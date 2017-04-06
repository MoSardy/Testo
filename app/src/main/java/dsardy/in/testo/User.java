package dsardy.in.testo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham on 4/6/2017.
 */

public class User {

    String mb;
    String name;
    String password;
    List<Product> orders;

    public User(String mb,String name,String pass){

        this.mb = mb;
        this.name = name;
        this.password = pass;
        this.orders= new ArrayList<>();

    }

    public User(){

    }

    public void addProduct(Product product){
        this.orders.add(product);
    }

    public String getMb() {
        return mb;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrders(List<Product> orders) {
        this.orders = orders;
    }

    public List<Product> getOrders() {
        return orders;
    }

}
