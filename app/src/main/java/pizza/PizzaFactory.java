package pizza;

import pizza.properties.Pizza;

/**
 * Pizza Factory Interface. An interface that allows for the creation of Deluxe, Meatzza, BBQChicken, and BuildYourOwn pizzas.
 * @author Hasnain Ali, Carolette Saguil
 */
public interface PizzaFactory {
    /**
     * @return Interface for a deluxe pizza
     */
    Pizza createDeluxe();

    /**
     * @return Interface for Meatzza Pizza
     */
    Pizza createMeatzza();

    /**
     * @return Interface for Chicken Pizza
     */
    Pizza createBBQChicken();

    /**
     * @return Interface for BuildYourOwn Pizza
     */
    Pizza createBuildYourOwn();
}
