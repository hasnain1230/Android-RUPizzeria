package pizza.properties;

import java.util.Locale;

/**
 * Enum class representing all possible pizza sizes.
 * @author Hasnain Ali, Carolette Saguil
 */
public enum Size {
    SMALL, MEDIUM, LARGE;

    @Override
    public String toString() {
        String name = this.name().toLowerCase(Locale.ROOT);
        return name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
    }
}
