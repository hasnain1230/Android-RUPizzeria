package rutgers.rupizzeria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Constants;

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
    public void onItemClick1(int position, View view) {
        showAlertDialogToppings(view);
    }

    public void onItemClick2(int position, View view) {
        showAlertDialogPrice(view);
    }

    private void showAlertDialogToppings(View view) {
        ArrayList<String> selectedToppings = new ArrayList<>();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle(R.string.select_topping_title);
        String[] toppings = getActivity().getResources().getStringArray(R.array.toppings);
        boolean[] checkedItems = new boolean[13];
        alertDialog.setMultiChoiceItems(toppings, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if (b) {
                    if (selectedToppings.size() == Constants.MAXIMUM_TOPPINGS) { //tried this with the R.integer.max_toppings and it didn't work but it works with this
                        Toast.makeText(view.getContext(), R.string.max_topping_warning, (Toast.LENGTH_SHORT)).show();
                        checkedItems[i] = false;
                        ((AlertDialog) dialogInterface).getListView().setItemChecked(i, false);
                    } else {
                        selectedToppings.add(toppings[i]);
                        //TODO: possibly make toast updating price but it was lowkey annoying me so decide if you want to
                    }
                } else {
                    selectedToppings.remove(toppings[i]);
                }
            }
        });

        alertDialog.setPositiveButton(R.string.continue_option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAlertDialogPrice(view);
            }
        });

        alertDialog.setNegativeButton(R.string.cancel_option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(view.getContext(), R.string.order_cancelled_notif, (Toast.LENGTH_SHORT)).show();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void showAlertDialogPrice(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle(R.string.confirm_order_title);
        alertDialog.setMessage(R.string.price_label);

        alertDialog.setPositiveButton(R.string.place_order_option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(view.getContext(), R.string.order_placed_notif, (Toast.LENGTH_SHORT)).show();
            }
        });

        alertDialog.setNegativeButton(R.string.cancel_option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(view.getContext(), R.string.order_cancelled_notif, (Toast.LENGTH_SHORT)).show();
            }
        });

       alertDialog.create().show();
    }
}