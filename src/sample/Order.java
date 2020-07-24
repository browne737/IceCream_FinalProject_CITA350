package sample;

public class Order {
    private String customerName, flavor;
    private double taxRate;
    private double discount;
    private int quantity;

    Order(String name, double tax, double disc, int qty, String flavor)
    {
        this.customerName = name;
        this.taxRate = tax;
        this.discount = disc;
        this.quantity = qty;
        this.flavor = flavor;
    }

    //getter methods
    public double getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTaxRate() {
        return taxRate;
    }
    //Sets the price of the flavors
    public double getFlavorPrice()
    {
        double price = 0;
        if(flavor.toLowerCase().equals("c"))
            price = 2;
        else if(flavor.toLowerCase().equals("v"))
            price = 1;
        else if(flavor.toLowerCase().equals("s"))
            price = 2.50;
        return price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String toString() {
        return "Name = " + customerName +
                " quantity = " + quantity;
    }
}
