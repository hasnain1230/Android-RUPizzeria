package rutgers.rupizzeria;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PizzaRecyclerViewAdapter extends RecyclerView.Adapter<PizzaRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<PizzaModel> pizzaModels;


    public PizzaRecyclerViewAdapter(Context context, ArrayList<PizzaModel> pizzaModels, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.pizzaModels = pizzaModels;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public PizzaRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new PizzaRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.pizzaType.setText(pizzaModels.get(position).getPizzaType());
        holder.pizzaCrust.setText(pizzaModels.get(position).getPizzaCrust());
        holder.pizzaToppings.setText(pizzaModels.get(position).getPizzaTopping());
        holder.pizzaImage.setImageResource(pizzaModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return pizzaModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView pizzaImage;
        TextView pizzaType, pizzaCrust, pizzaToppings;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            pizzaImage = itemView.findViewById(R.id.imageView);
            pizzaType = itemView.findViewById(R.id.textView12);
            pizzaCrust = itemView.findViewById(R.id.textView13);
            pizzaToppings = itemView.findViewById(R.id.textView14);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos, itemView);
                        }
                    }
                }
            });
        }
    }
}
