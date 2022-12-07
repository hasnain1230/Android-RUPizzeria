package rutgers.rupizzeria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderPizzaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderPizzaFragment extends Fragment implements RecyclerViewInterface {

    ArrayList<PizzaModel> pizzaModels = new ArrayList<>();
    int[] pizzaImages = {R.drawable.chicagodefault, R.drawable.chicagodeluxe, R.drawable.chicagobbq, R.drawable.chicagomeat,
            R.drawable.nydefault, R.drawable.nydeluxe, R.drawable.nybbq, R.drawable.nymeatzza};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderPizzaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderPizzaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderPizzaFragment newInstance(String param1, String param2) {
        OrderPizzaFragment fragment = new OrderPizzaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_pizza, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.pizzaRecyclerView);
        setUpPizzaModels();
        PizzaRecyclerViewAdapter adapter = new PizzaRecyclerViewAdapter(view.getContext(), pizzaModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    private void setUpPizzaModels() {
        String[] pizzaTypes = getResources().getStringArray(R.array.pizza_types);
        String[] pizzaCrusts = getResources().getStringArray(R.array.pizza_crust);
        String[] pizzaToppings = getResources().getStringArray(R.array.pizza_toppings);

        for (int i = 0; i < pizzaTypes.length; i++) {
            pizzaModels.add(new PizzaModel(pizzaTypes[i], pizzaCrusts[i], pizzaToppings[i], pizzaImages[i]));
        }
    }

    @Override
    public void onItemClick(int position, View view) {
        showAlertDialog(view);
    }

    private void showAlertDialog(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle("Select Toppings");
        String[] toppings = {"Sausage", "Pepperoni", "BBQ Chicken", "Beef", "Ham", "Provolone", "Bacon", "Green Pepper", "Onion", "Mushroom", "Cheddar", "Olives", "Pineapple"};
        boolean[] checkedItems = {false, false, false, false, false,false, false, false, false, false, false, false, false};
        alertDialog.setMultiChoiceItems(toppings, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                switch (which) {
                    case 0:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Sausage", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Pepperoni", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on BBQ Chicken", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Beef", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Ham", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Provolone", Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Bacon", Toast.LENGTH_LONG).show();
                        break;
                    case 7:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Green Pepper", Toast.LENGTH_LONG).show();
                        break;
                    case 8:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Onion", Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Mushroom", Toast.LENGTH_LONG).show();
                        break;
                    case 10:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Cheddar", Toast.LENGTH_LONG).show();
                        break;
                    case 11:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Olives", Toast.LENGTH_LONG).show();
                        break;
                    case 12:
                        if(isChecked)
                            Toast.makeText(view.getContext(), "Clicked on Pineapple", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}