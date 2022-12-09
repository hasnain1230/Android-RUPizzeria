package rutgers.rupizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Pizza Recycler View Adapter binds the app's data set, in this case types of pizzas, to pizza item views displayed in the recycler view.
 * @author Hasnain Ali, Carolette Saguil
 */
public class PizzaRecyclerViewAdapter extends RecyclerView.Adapter<PizzaRecyclerViewAdapter.MyViewHolder> {
    /**
     * The interface to implement for the RecyclerView
     */
    private final RecyclerViewInterface recyclerViewInterface;
    /**
     * The context of the activity
     */
    Context context;
    /**
     * The Pizza Models for the RecyclerView
     */
    ArrayList<PizzaModel> pizzaModels;

    /**
     * Constructor that takes in the context, pizza models, and recycler view interface to create new pizza recycler view adapter.
     * @param context Context of app environment.
     * @param pizzaModels Data set of pizza types.
     * @param recyclerViewInterface Interface for recycler view item clicks.
     */
    public PizzaRecyclerViewAdapter(Context context, ArrayList<PizzaModel> pizzaModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.pizzaModels = pizzaModels;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    /**
     * Inflating the layout and give a look to rows in recycler view.
     * @param parent Parent is the View Group new view will be added to.
     * @param viewType View Type of new view.
     * @return new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public PizzaRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new PizzaRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    /**
     * Assign values to views we create in the recycler view row layout file based on position of recycler view.
     * @param holder View Holder that will be updated.
     * @param position Position of item in recycler view.
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.pizzaType.setText(pizzaModels.get(position).getPizzaType());
        holder.pizzaCrust.setText(pizzaModels.get(position).getPizzaCrust());
        holder.pizzaToppings.setText(pizzaModels.get(position).getPizzaTopping());
        holder.pizzaImage.setImageResource(pizzaModels.get(position).getImage());
    }

    /**
     * @return Returns number of items we want displayed in recycler view, in this case, number of pizza types.
     */
    @Override
    public int getItemCount() {
        return pizzaModels.size();
    }

    /**
     * This class works with the adapter to update the screen for individual items.
     * @author Hasnain Ali, Carolette Saguil
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * Image View for pizza image.
         */
        ImageView pizzaImage;

        /**
         * Text View for pizza types.
         */
        TextView pizzaType;

        /**
         * Text View for pizza crust.
         */
        TextView pizzaCrust;

        /**
         * Text View for pizza toppings.
         */
        TextView pizzaToppings;

        /**
         * Constructor for view holder that instantiates all the views in a recycler view row.
         * @param itemView View of item in recycler view.
         * @param recyclerViewInterface Interface for recycler view.
         */
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {

            super(itemView);
            pizzaImage = itemView.findViewById(R.id.imageView);
            pizzaType = itemView.findViewById(R.id.textView12);
            pizzaCrust = itemView.findViewById(R.id.textView13);
            pizzaToppings = itemView.findViewById(R.id.textView14);

            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * Sets click methods for certain positions in recycler view.
                 * @param view View that is pressed.
                 */
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos == view.getResources().getInteger(R.integer.position_chicago_buildyourown) || pos == view.getResources().getInteger(R.integer.position_ny_buildyourown)) {
                            recyclerViewInterface.onItemClickCustomize(pos, itemView);
                        } else if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClickBasic(pos, itemView);
                        }
                    }
                }
            });
        }
    }
}
