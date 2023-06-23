//By Tyson Shannon
//This program allows you to add and subtract items from a inventory.
//Ex. add 4 oranges 5 pears close
//Ex. sub 2 oranges 2 pears close
//It will also let you add specialty items with purchase limits per customer
//Ex. limited 8 apples 2 close
//The above adds 8 apples and only lets you remove 2 at a time
//You can also display the inventory list and see how much of each item is still available.


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
    private static ArrayList<Product> inventory;
    private static ArrayList<String> inventoryNames;
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean continueProgram = true;
        System.out.println("Welcome to the Maverick Food Pantry inventory program");
        System.out.println("-----------------------------------------------------\n");
        inventory = new ArrayList<Product>();
        inventoryNames = new ArrayList<String>();
        while (continueProgram){
            System.out.print("Enter command or H for help: ");
            String nxt = scnr.next();
            if (nxt.equals("Q") || nxt.equals("q")){
                System.out.println("Program quit.");
                continueProgram = false;
            }
            if (nxt.equals("H") || nxt.equals("h")){
                System.out.printf("%16s\n%11s\n%24s\n%128s\n%131s\n%150s\n\n", "H: help menu", "Q: quit", "D: display inventory", "add *number* *productname* close: input amounts and product names after command to add items. Enter close to end add command", "sub *number* *productname* close: input amounts and product names after command to remove items. Enter close to end sub command", "limited *number* *productname* *limit* close: Adds specified amount of specialty product with a purchase limit. Enter close to end limited command");
            }
            if (nxt.equals("add")){
                while (scnr.hasNextInt()){
                    int amount = scnr.nextInt();
                    String product = scnr.next();
                    if (!inventoryNames.contains(product)){
                        Product newProduct = new Product(amount, product);
                        inventory.add(newProduct);
                        inventoryNames.add(product);
                    }
                    else{
                        Product[] inventoryArray = new Product[inventory.size()];
                        for (int i = 0; i < inventory.size(); i++){
                            if (inventory.get(i) instanceof Product){
                                inventoryArray[i] = (Product)inventory.get(i);
                            }
                        }
                        findProduct(inventoryArray, product, amount, nxt);
                    }
                }
            }
            if (nxt.equals("limited")){
                while (scnr.hasNextInt()){
                    int amount = scnr.nextInt();
                    String product = scnr.next();
                    int limit = scnr.nextInt();
                    if (!inventoryNames.contains(product)){
                        Limited newLimitedProduct = new Limited(amount, product, limit);
                        inventory.add(newLimitedProduct);
                        inventoryNames.add(product);
                    }
                    else{
                        Product[] inventoryArray = new Product[inventory.size()];
                        for (int i = 0; i < inventory.size(); i++){
                            if (inventory.get(i) instanceof Product){
                                inventoryArray[i] = (Product)inventory.get(i);
                            }
                        }
                        findProduct(inventoryArray, product, amount, "add");
                    }
                }
            }
            if (nxt.equals("sub")){
                while (scnr.hasNextInt()){
                    int amount = scnr.nextInt();
                    String product = scnr.next();
                    if (!inventoryNames.contains(product)){
                        System.out.printf("%s is not in inventory.\n", product);
                        continue;
                    }
                    else{
                        Product[] inventoryArray = new Product[inventory.size()];
                        for (int j = 0; j < inventory.size(); j++){
                            if (inventory.get(j) instanceof Product){
                                inventoryArray[j] = (Product)inventory.get(j);
                            }
                        }
                        findProduct(inventoryArray, product, amount, nxt);
                    }
                }
            }
            if (nxt.equals("D") || nxt.equals("d")){
                System.out.println("Food Pantry Inventory List:");
                for (int k = 0; k < inventory.size(); k++){
                    Product current = inventory.get(k);
                    System.out.println(current.toString());
                }
                System.out.println("");
            }
        }
        scnr.close();
    }
    /**
     * Finds specific product recursively and adds or subtracts to its amount in stock depending on command type (add/sub)
     * @param inventory the array with all the products in the inventory
     * @param target the specific product needed to be updated
     * @param amount the amount being incremented or decremented from the product amount
     * @param type whether it is adding or subtracting from the product amount
     */
    public static void findProduct(Product[] inventory, String target, int amount, String type){
        // Boolean stops recursion by turning to false once array length equals 1
        boolean recursion = true;
        if (inventory.length == 1 && inventory[0].getName().equals(target) && type.equals("add")){
            inventory[0].add(amount);
            recursion = false;
        }
        if (inventory.length == 1 && inventory[0].getName().equals(target) && type.equals("sub")){
            inventory[0].sub(amount);
            recursion = false;
        }
        if (inventory.length == 1 && !inventory[0].getName().equals(target)){
            recursion = false;
        }
        Product[] first = Arrays.copyOfRange(inventory, 0, inventory.length/2);
        Product[] second = Arrays.copyOfRange(inventory, first.length, inventory.length-first.length);
        // Recursion only occurs if split array has length of one or greater and the boolean recursion is still true
        if (first.length > 0 && recursion){
            findProduct(first, target, amount, type);   
        }
        if (second.length > 0 && recursion){
            findProduct(second, target, amount, type);
        }     
    }

    /**
     * Removes product from inventory when amount is decremented to or past 0
     * @param x product to be removed
     */
    public static void remove(Product x){
        inventory.remove(x);
        inventoryNames.remove(x.getName());
    }
}
