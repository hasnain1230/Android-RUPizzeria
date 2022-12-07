package pizza;

import constants.Constants;
import pizza.properties.Crust;
import pizza.properties.Pizza;
import pizza.properties.Size;
import pizza.properties.Topping;

import java.util.ArrayList;

/**
 * This class is an encapsulation for any type of BuildYourOwn Pizza used in {@code FX-RUPizzeria}. Depending on the Crust Style,
 * this class can account for any type of BuildYourOwn Pizza. It inherits from {@code Pizza.java}.
 * @author Hasnain Ali, Carolette Saguil
 */
public class BuildYourOwn extends Pizza {
    /**
     * @param crust The type of crust for the Pizza.
     */
    public BuildYourOwn(Crust crust) {
        super(crust);
    }

    /**
     * @return The price of the Pizza depending on the size. Error if no size is defined.
     */
    @Override
    public double price() {
        ArrayList<Topping> toppings = getToppings();

        if (super.getSize() == Size.SMALL) {
            return Constants.SMALL_BUILD_YOUR_OWN + (toppings.size() * Constants.ADDITIONAL_TOPPING_COST);
        } else if (super.getSize() == Size.MEDIUM) {
            return Constants.MEDIUM_BUILD_YOUR_OWN + (toppings.size() * Constants.ADDITIONAL_TOPPING_COST);
        } else if (super.getSize() == Size.LARGE) {
            return Constants.LARGE_BUILD_YOUR_OWN + (toppings.size() * Constants.ADDITIONAL_TOPPING_COST);
        } else {
            return Constants.ERROR;
        }
    }
}
