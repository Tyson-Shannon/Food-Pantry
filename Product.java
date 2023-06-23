public class Product {
    public int amount;
    public String name;

    public Product(){
        amount = 0;
        name = "";
    }
    /**
     * Sets name and number of a specific product
     * @param amount number of a product
     * @param name name of product
     */
    public Product(int amount, String name){
        this.amount = amount;
        this.name = name;
    }
    /**
     * Adds to total of a product
     * @param increase Amount added
     */
    public void add(int increase){
        amount += increase;
        System.out.printf("%d %s added to inventory.\n", increase, this.getName());
    }
    /**
     * Subtracts from total of product and calls for product to be removed from inventory if amount is decreased passed 0
     * @param decrease Amount subtracted
     */
    public void sub(int decrease){
        amount -= decrease;
        System.out.printf("%d %s subtracted from inventory.\n", decrease, this.getName());
        if (amount <= 0){
            Main.remove(this);
        }
    }
    public int getAmount(){
        return amount;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return "    "+amount+"    "+name;
    }
}
