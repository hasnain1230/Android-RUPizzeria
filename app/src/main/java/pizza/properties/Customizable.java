package pizza.properties;

/**
 * Interface allowing for adding and removing objects from {@code FX-RUPizzeria}.
 * @author Hasnain Ali, Carolette Saguil
 */
public interface Customizable {
    /**
     * @param obj Object to add.
     * @return Whether {@code obj} was added successfully.
     */
    boolean add(Object obj);

    /**
     * @param obj Object to remove.
     * @return Whether {@code obj} was removed successfully.
     */
    boolean remove(Object obj);
}
