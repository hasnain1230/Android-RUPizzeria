package rutgers.rupizzeria;

import android.view.View;

/**
 * Recycler View Interface. An interface that allows recycler view items to be clicked.
 * @author Hasnain Ali, Carolette Saguil
 */
public interface RecyclerViewInterface {
    /**
     * Interface for clicking on build your own pizza.
     * @param position Position of clicked item.
     * @param view View that contains clicked item.
     */
    void onItemClickCustomize(int position, View view);

    /**
     * Interface for clicking on non-customizable pizza.
     * @param position  Position of clicked item.
     * @param view View that contains clicked item.
     */
    void onItemClickBasic(int position, View view);
}
