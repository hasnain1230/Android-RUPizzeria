package pizza;

import constants.Constants;
import pizza.properties.Crust;
import pizza.properties.Pizza;
import pizza.properties.Size;
import pizza.properties.Topping;

/**
 * This class is an encapsulation for any type of BBQChicken used in {@code FX-RUPizzeria}. Depending on the Crust Style,
 * this class can account for any type of BBQChicken Pizza. It inherits from {@code Pizza.java}.
 * @author Hasnain Ali, Carolette Saguil
 */
public class BBQChicken extends Pizza {
    /**
     * @param crust The type of crust for the Pizza.
     */
    public BBQChicken(Crust crust) {
        super(crust);
        super.add(Topping.BBQ_CHICKEN);
        super.add(Topping.GREEN_PEPPER);
        super.add(Topping.PROVOLONE);
        super.add(Topping.CHEDDAR);
    }

    /**
     * @return The price of the Pizza depending on the size. Error if no size is defined.
     */
    @Override
    public double price() {
        if (super.getSize() == Size.SMALL) {
            return Constants.SMALL_BBQ_CHICKEN;
        } else if (super.getSize() == Size.MEDIUM) {
            return Constants.MEDIUM_BBQ_CHICKEN;
        } else if (super.getSize() == Size.LARGE) {
            return Constants.LARGE_BBQ_CHICKEN;
        } else {
            return Constants.ERROR;
        }
    }
}
