package orders;

import constants.Constants;
import pizza.properties.Customizable;
import pizza.properties.Pizza;

import java.io.*;
import java.util.HashMap;

/**
 * Encapsulation that stores all the orders placed in the {@code FX-RUPizzeria} store
 * @authors Hasnain Ali, Carolette Saguil
 */
public class StoreOrders implements Customizable {
    /**
     * Using a HashSet for all the orders in case we want quick look up times for a specific order.
     */
    private final HashMap<Integer, Order> orders;

    /**
     * Constructor that initializes the HashSet for {@code orders}.
     */
    public StoreOrders() {
        this.orders = new HashMap<>();
    }

    /**
     * This method will take all current orders and write them into a file.
     * @return A String with the file path of where {@code orders.export} was written.
     */
    public String export() {
        try {
            File fileToWrite = new File("orders.export");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));

            for (Order order : this.orders.values()) {
                StringBuilder outputString = new StringBuilder();
                outputString.append(String.format("Order ID: %d\n", order.getOrderID()));
                double totalPrice = 0.0;

                for (Pizza pizza : order.getPizzasInOrder()) {
                    outputString.append(String.format("\t%s\n", pizza.toString()));
                    totalPrice += pizza.price();
                }

                bufferedWriter.write(outputString.toString());
                bufferedWriter.write(String.format("Total Price: (without tax) $%.2f\n", totalPrice));
                bufferedWriter.write(String.format("Total Price (with tax): $%.2f\n\n", totalPrice * Constants.CALCULATED_SALES_TAX));
            }

            bufferedWriter.flush();
            bufferedWriter.close();

            return fileToWrite.getAbsolutePath();

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return null;
    }

    /**
     * @return A HashMap of all the orders in the store.
     */
    public HashMap<Integer, Order> getOrders() {
        return this.orders;
    }

    /**
     * @param obj An order to add into {@code StoreOrders}.
     * @return Whether the order was successfully added into {@code StoreOrders}.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            this.orders.put(((Order) obj).getOrderID(), (Order) obj);
            return true;
        }

        return false;
    }

    /**
     * @param obj An order to remove from {@code StoreOrders}.
     * @return Whether the order was successfully removed from {@code StoreOrders}.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            this.orders.remove(((Order) obj).getOrderID());
            return true;
        }

        return false;
    }
}
