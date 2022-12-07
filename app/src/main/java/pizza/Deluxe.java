package pizza;

import constants.Constants;
import pizza.properties.Crust;
import pizza.properties.Pizza;
import pizza.properties.Size;
import pizza.properties.Topping;

/**
 * This class is an encapsulation for any type of Deluxe used in {@code FX-RUPizzeria}. Depending on the Crust Style,
 * this class can account for any type of Deluxe Pizza. It inherits from {@code Pizza.java}.
 * @author Hasnain Ali, Carolette Saguil
 */
public class Deluxe extends Pizza {
    /**
     * @param crust The type of crust for the Pizza.
     */
    public Deluxe(Crust crust) {
        super(crust);
        super.add(Topping.SAUSAGE);
        super.add(Topping.PEPPERONI);
        super.add(Topping.GREEN_PEPPER);
        super.add(Topping.ONION);
        super.add(Topping.MUSHROOM);
    }


    /**
     * @return The price of the Pizza depending on the size. Error if no size is defined.
     */
    @Override
    public double price() {
        if (super.getSize() == Size.SMALL) {
            return Constants.SMALL_DELUXE;
        } else if (super.getSize() == Size.MEDIUM) {
            return Constants.MEDIUM_DELUXE;
        } else if (super.getSize() == Size.LARGE) {
            return Constants.LARGE_DELUXE;
        } else {
            return Constants.ERROR;
        }
    }
}
