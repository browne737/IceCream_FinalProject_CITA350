package sample;

public class SandwichOrder extends Order{
    double cookies, waffles, wafers;
    double subtotal, total, totalTax,totalDiscount;
    String breadType;
    String[] breads = new String[3];



    public SandwichOrder(String name, double tax, double disc, int qty, String flavor) {
        super(name, tax, disc, qty, flavor);
        //initialize array
        breads[0] = "Cookies";
        breads[1] = "Wafers";
        breads[2] = "Waffles";
    }
    //gets the subtototal
    public void getSubtotal()
    {
        setVariables();
        subtotal = 0;
        subtotal = (super.getFlavorPrice() + getBreadPrice())*super.getQuantity();
    }
    //gets all the totals
    public void calculateTotal()
    {
        getSubtotal();
        totalTax = subtotal*super.getTaxRate();
        totalDiscount = (subtotal*super.getDiscount());

        total = (subtotal + totalTax - totalDiscount);
    }

    public void setVariables()
    {
        cookies = 3;
        wafers = 1;
        waffles = 2;
    }

    public double getBreadPrice()
    {
        double price = 0;
        if(breadType.toLowerCase().equals("cookies")){
            price = cookies;
            return price;
        }
        if (breadType.toLowerCase().equals("wafers")) {
            price = wafers;
            return price;
        }
        if (breadType.toLowerCase().equals("waffles")){
            price = waffles;
            return price;
        }
        return price;
    }
    // use this method to set breadtype so we later can find the price for it
    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public double getSub(){return subtotal;}
    public double getTotal(){return total;}

    @Override
    public String toString() {
        String str = "Sandwich Order Receipt:" + "\n" +"------------------------"+ "\n" + super.toString() + "\n" +
                "subtotal = " + String.format( "$%.2f", subtotal) + "\n"+
                "Tax = " + String.format( "$%.2f", totalTax) + "\n"+
                "Discount = " + String.format( "$%.2f", totalDiscount) + "\n"+
                "total = " + String.format( "$%.2f", total) + "\n";
        return str;
    }
}
