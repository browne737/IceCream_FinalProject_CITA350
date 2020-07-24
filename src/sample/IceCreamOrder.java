package sample;

import javax.swing.border.Border;
import java.util.Arrays;

public class IceCreamOrder extends Order {
    double flavorPrice, toppingPrice;
    double subtotal, total, totalTax,totalDiscount;
    public boolean peanuts;
    public boolean sprinkles;
    public boolean fudge;
    Boolean[] toppings = new Boolean[3];


    public IceCreamOrder(String name, double tax, double disc, int qty, String flavor) {
        super(name, tax, disc, qty, flavor);
    }
    //Calculates the subtotal
    public void getSubtotal()
    {
        setVariables();
        subtotal = 0;
        subtotal = (super.getFlavorPrice() + getToppingPrice())*super.getQuantity();
    }
    //Calculates all the totals
    public void calculateTotal()
    {
        getSubtotal();
        totalTax = subtotal*super.getTaxRate();
        totalDiscount = (subtotal*super.getDiscount());

        total = (subtotal + totalTax - totalDiscount);
    }

    public void setVariables()
    {
        toppings[0] = peanuts;
        toppings[1] = sprinkles;
        toppings[2] = fudge;

        peanuts = false;
        sprinkles = false;
        fudge = false;

        toppingPrice = .25;
    }
    public double getTotal(){return total;}
    public double getSub(){return subtotal;}
    public double getToppingPrice()
    {
        double sub = 0;
        for(int i = 0; i <= (toppings.length-1); i++)
        {
            if(toppings[i] = true)
            {
                sub += toppingPrice;
            }
        }
        return sub;
    }

    @Override
    public String toString() {
        String str = "Bowl Order Receipt:" + "\n" +"------------------------"+ "\n" + super.toString() + "\n" +
                "subtotal = " + String.format( "$%.2f", subtotal) + "\n"+
                "Tax = " + String.format( "$%.2f", totalTax) + "\n"+
                "Discount = " + String.format( "$%.2f", totalDiscount) + "\n"+
                "total = " + String.format( "$%.2f", total) + "\n";
        return str;
    }
}
