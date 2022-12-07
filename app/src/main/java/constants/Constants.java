package constants;

/**
 * This class stores all the constants used in {@code FX-RUPizzeria} and its dependencies
 * @author Hasnain Ali, Carolette Saguil
 */
public class Constants {
    public static final double SMALL_DELUXE = 14.99;
    public static final double MEDIUM_DELUXE = 16.99;
    public static final double LARGE_DELUXE = 18.99;

    public static final double SMALL_BBQ_CHICKEN = 13.99;
    public static final double MEDIUM_BBQ_CHICKEN = 15.99;
    public static final double LARGE_BBQ_CHICKEN = 17.99;

    public static final double SMALL_MEATZZA = 15.99;
    public static final double MEDIUM_MEATZZA = 17.99;
    public static final double LARGE_MEATZZA = 19.99;

    public static final double SMALL_BUILD_YOUR_OWN = 8.99;
    public static final double MEDIUM_BUILD_YOUR_OWN = 10.99;
    public static final double LARGE_BUILD_YOUR_OWN = 12.99;

    public static final double ADDITIONAL_TOPPING_COST = 1.59;

    public static final int MAXIMUM_TOPPINGS = 7;

    public static final String[] TOPPINGS = {"Sausage", "Pepperoni", "BBQ Chicken", "Beef", "Ham", "Provolone", "Bacon", "Green Pepper", "Onion", "Mushroom", "Cheddar", "Olives", "Pineapple"};
    public static final String[] MEATZZA_TOPPINGS = {"Sausage", "Pepperoni", "Beef", "Ham"};
    public static final String[] BBQ_CHICKEN_TOPPINGS = {"BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"};
    public static final String[] DELUXE_TOPPINGS = {"Sausage", "Pepperoni","Green Pepper", "Onion", "Mushroom"};
    public static final String[] TYPES = {"Build your own", "Deluxe", "BBQ Chicken", "Meatzza"};

    public static double EMPTY_SUBTOTAL = 0.0;
    public static double SALES_TAX = (6.625 / 100);
    public static double CALCULATED_SALES_TAX = (1 + SALES_TAX);

    public static final double ERROR = -1;
}
