public class Limited extends Product {
    private int limit;
    public Limited(int amount, String name, int limit){
        super.amount = amount;
        super.name = name;
        this.limit = limit;
    }
    /**
     * Adds to total of a limited product
     * @param increase Amount added
     */
    public void add(int increase){
        amount += increase;
        System.out.printf("%d %s added to inventory limited %d per person.\n", increase, this.getName(), limit);
    }
    /**
     * Subtracts from total of product as long as its not greater than the product limit and calls for product to be removed from inventory if amount is decreased passed 0
     * @param decrease Amount subtracted
     */
    public void sub(int decrease){
        if (decrease <= limit){
            amount -= decrease;
            System.out.printf("%d %s subtracted from inventory.\n", decrease, this.getName());
            if (amount <= 0){
                Main.remove(this);
            }
        }
        else{
            System.out.printf("Limit of %d per person. Command failed could not subtract %d from inventory.\n", limit, decrease);
        }
    }
    public String toString(){
        return "    "+amount+"    "+name+"    "+limit+" limit per person";
    }
}
