package pizza.properties;

import pizza.BBQChicken;
import pizza.BuildYourOwn;
import pizza.Deluxe;
import pizza.Meatzza;

import java.util.ArrayList;

/**
 * Abstract class defining some basic properties of all Pizzas used in {@code FX-RUPizzeria}.
 * @author Hasnain Ali, Carolette Saguil
 */
public abstract class Pizza implements Customizable {
    /**
     * ArrayList of all toppings on this pizza.
     */
    private final ArrayList<Topping> toppings;
    /**
     * The type of crust used on this pizza.
     */
    private final Crust crust;
    /**
     * The size of this pizza.
     */
    private Size size;

    /**
     * @param crust Setting the crust and instantiating the ArrayList of toppings for this pizza.
     */
    public Pizza(Crust crust) {
        this.toppings = new ArrayList<>();
        this.crust = crust;
    }

    /**
     * @param obj Topping to add.
     * @return Whether topping was added successfully.
     */
    public boolean add(Object obj) {
        if (obj instanceof Topping) {
            this.toppings.add((Topping) obj);
            return true;
        }
        return false;
    }

    /**
     * @param obj Topping to remove
     * @return Whether topping was removed successfully.
     */
    public boolean remove(Object obj) {
        if (obj instanceof Topping) {
            toppings.remove((Topping) obj);
            return true;
        }
        return false;
    }

    /**
     * @param size Size to set.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * @return The crust type.
     */
    public Crust getCrust() {
        return this.crust;
    }

    /**
     * @return ArrayList of all toppings on this pizza.
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * @return Current size of pizza.
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * @return Returns style of the pizza in string representation.
     */
    public String getStyle() {
        if (this instanceof Deluxe) {
            if (this.crust == Crust.DEEP_DISH) {
                return "Chicago Style (Deluxe)";
            } else if (this.crust == Crust.BROOKLYN) {
                return "New York Style (Deluxe)";
            }
        } else if (this instanceof BBQChicken) {
            if (this.crust == Crust.PAN) {
                return "Chicago Style (BBQChicken)";
            } else if (this.crust == Crust.THIN) {
                return "New York Style (BBQChicken)";
            }
        } else if (this instanceof Meatzza) {
            if (this.crust == Crust.STUFFED) {
                return "Chicago Style (Meatzza)";
            } else if (this.crust == Crust.HAND_TOSSED) {
                return "New York Style (Meatzza)";
            }
        } else if (this instanceof BuildYourOwn) {
            if (this.crust == Crust.PAN) {
                return "Chicago Style (Build Your Own)";
            } else if (this.crust == Crust.HAND_TOSSED) {
                return "New York Style (Build Your Own)";
            }
        }

        return null;
    }

    /**
     * @return Abstract method intended to return the price of the current pizza with all the toppings, but does not include sales tax. That is calculated last.
     */
    public abstract double price();

    /**
     * @return A string listing the properties of the pizza.
     */
    @Override
    public String toString() {
        return String.format("%s | Size: %s, Crust: %s, Toppings: %s Price: $ %.2f", this.getStyle(), this.size, this.crust, this.toppings, price());
    }
}

