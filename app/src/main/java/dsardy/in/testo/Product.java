package dsardy.in.testo;

/**
 * Created by Shubham on 4/6/2017.
 */

public class Product {

    int stock;
    String pName;
    String pManuf;
    String pType;
    Long price;
    String code;
    int date = 00;
    String month = "null";

    public void Product(){

    }


    public void setDate(int date) {
        this.date = date;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public Long getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCode() {
        return code;
    }

    public String getpManuf() {
        return pManuf;
    }

    public String getpName() {
        return pName;
    }

    public String getpType() {
        return pType;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setpManuf(String pManuf) {
        this.pManuf = pManuf;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
