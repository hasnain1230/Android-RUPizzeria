package orders;

import pizza.properties.Customizable;
import pizza.properties.Pizza;

import java.util.HashSet;

/**
 * Order class for {@code FX-RUPizzeria}. This class will assign a random order number and allow for adding new pizzas to the order.
 * This class is an encapsulation of a typical pizza order.
 * @author Hasnain Ali, Carolette Saguil
 */
public class Order implements Customizable {
    /**
     * Order Number used to assign new, unique order ID
     */
    private static int orderNumber;
    /**
     * Order ID given to this instance of the order.
     */
    private final int orderID;
    /**
     * Using a HashSet for the order in case we need to search for Pizzas. This will allow for constant access time.
     */
    private final HashSet<Pizza> pizzasInOrder;

    /**
     * Instantiates the order ID and increments order number when a new order is created.
     */
    public Order() {
        this.orderID = orderNumber++;
        this.pizzasInOrder = new HashSet<>();
    }

    /**
     * Copy constructor for order.
     * @param order Order to copy.
     */
    public Order(Order order) {
        this.orderID = order.orderID;
        this.pizzasInOrder = new HashSet<>(order.pizzasInOrder);
    }

    /**
     * @return Returns the order ID.
     */
    public int getOrderID() {
        return this.orderID;
    }

    /**
     * @return Returns a HashSet of all the pizzas used in the order.
     */
    public HashSet<Pizza> getPizzasInOrder() {
        return this.pizzasInOrder;
    }

    /**
     * @param obj Object to add into the orders HashSet.
     * @return Whether {@code obj} was successfully able to be added into the orders HashSet.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            this.pizzasInOrder.add((Pizza) obj);
            return true;
        }

        return false;
    }

    /**
     * @param obj Object to remove into the orders HashSet.
     * @return Whether {@code obj} was successfully able to be removed from the orders HashSet.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            this.pizzasInOrder.remove((Pizza) obj);
            return true;
        }

        return false;
    }
}
