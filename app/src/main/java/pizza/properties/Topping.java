package pizza.properties;

/**
 * Enum class listing all the toppings supported by {@code FX-RUPizzeria}.
 * @author Hasnain Ali, Carolette Saguil
 */
public enum Topping {
    SAUSAGE, PEPPERONI, GREEN_PEPPER, ONION, MUSHROOM, BBQ_CHICKEN, PROVOLONE, CHEDDAR, BEEF, HAM, BACON, OLIVES, PINEAPPLE;

    /**
     * @param string The string to return the topping of.
     * @return An enum based on the string parameter for the toppings.
     */   
    public static Topping returnToppingEnumFromString(String string) {
        if (string.equalsIgnoreCase("sausage")) {
            return Topping.SAUSAGE;
        } else if (string.equalsIgnoreCase("pepperoni")) {
            return Topping.PEPPERONI;
        } else if (string.equalsIgnoreCase("green pepper")) {
            return Topping.GREEN_PEPPER;
        } else if (string.equalsIgnoreCase("onion")) {
            return Topping.ONION;
        } else if (string.equalsIgnoreCase("mushroom")) {
            return Topping.MUSHROOM;
        } else if (string.equalsIgnoreCase("bbq chicken")) {
            return Topping.BBQ_CHICKEN;
        } else if (string.equalsIgnoreCase("provolone")) {
            return Topping.PROVOLONE;
        } else if (string.equalsIgnoreCase("cheddar")) {
            return Topping.CHEDDAR;
        } else if (string.equalsIgnoreCase("beef")) {
            return Topping.BEEF;
        } else if (string.equalsIgnoreCase("pineapple")) {
            return Topping.PINEAPPLE;
        } else if (string.equalsIgnoreCase("olives")) {
            return Topping.OLIVES;
        } else if (string.equalsIgnoreCase("bacon")) {
            return Topping.BACON;
        } else if (string.equalsIgnoreCase("ham")) {
            return Topping.HAM;
        } else {
            return null;
        }
    }
}


