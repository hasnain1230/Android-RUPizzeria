package rutgers.rupizzeria;

/**
 * This class is to make a model of a pizza with all it's information.
 * @author Hasnain Ali, Carolette Saguil
 */
public class PizzaModel {
    /**
     * Type of pizza.
     */
    String pizzaType;

    /**
     * Crust of pizza.
     */
    String pizzaCrust;

    /**
     * Toppings of pizza.
     */
    String pizzaTopping;

    /**
     * Image of pizza.
     */
    int image;

    /**
     * Constructor that instantiates model of pizza with all the given information.
     * @param pizzaType Type of Pizza.
     * @param pizzaCrust Crust of Pizza.
     * @param pizzaTopping Toppings of Pizza.
     * @param image Image of Pizza.
     */
    public PizzaModel(String pizzaType, String pizzaCrust, String pizzaTopping, int image) {
        this.pizzaType = pizzaType;
        this.pizzaCrust = pizzaCrust;
        this.pizzaTopping = pizzaTopping;
        this.image = image;
    }

    /**
     * @return Returns type of pizza.
     */
    public String getPizzaType() {
        return pizzaType;
    }

    /**
     * @return Returns crust of pizza.
     */
    public String getPizzaCrust() {
        return pizzaCrust;
    }

    /**
     * @return Returns image of pizza.
     */
    public int getImage() {
        return image;
    }

    /**
     * @return Returns toppings of pizza.
     */
    public String getPizzaTopping() {
        return pizzaTopping;
    }
}
