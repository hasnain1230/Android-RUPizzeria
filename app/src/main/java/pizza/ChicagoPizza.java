package pizza;

import pizza.properties.Crust;
import pizza.properties.Pizza;
import pizza.properties.Size;

/**
 * Implements a Chicago Style Pizza. Implements from Pizza Factory which allows for either Deluxe, Meatzza, BBQChicken, or BuildYourOwnPizza
 * to be created in Chicago Crust Style.
 * @author Hasnain Ali, Carolette Saguil
 */
public class ChicagoPizza implements PizzaFactory {
    /**
     * @return A Deluxe Chicago Style Pizza
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH);
    }

    /**
     * @return A Meatzza Chicago Style Pizza
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * @return A BBQChicken Chicago Style Pizza
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * @return A BuildYourOwn Chicago Style Pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}
