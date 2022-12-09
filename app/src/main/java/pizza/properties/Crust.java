package pizza.properties;

import java.util.Locale;

/**
 * Enum defining the following crusts: DEEP DISH, PAN, STUFFED, BROOKLYN, THIN, HAND TOSSED.
 * @author Hasnain Ali, Carolette Saguil
 */
public enum Crust {
    DEEP_DISH, PAN, STUFFED, BROOKLYN, THIN, HAND_TOSSED;

    /**
     * @return String representation of the crust with some formatting for case sensitivity.
     */
    @Override
    public String toString() {
        String name = this.name().toLowerCase(Locale.ROOT).replace("_", " ");
        int spaceIndex = name.indexOf(" ");
        if (spaceIndex != -1) {
            name = name.substring(0, spaceIndex + 1) + name.substring(spaceIndex + 1, spaceIndex + 2).toUpperCase(Locale.ROOT) + name.substring(spaceIndex + 2);
        }
        return name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
    }
}
