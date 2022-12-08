package rutgers.rupizzeria;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import orders.Order;
import pizza.ChicagoPizza;
import pizza.NYPizza;
import pizza.properties.Pizza;
import pizza.properties.Size;
import pizza.properties.Topping;

public class OrderPizzaFragment extends Fragment implements RecyclerViewInterface {
    private RadioGroup radioGroup;
    private Pizza pizza;

    ArrayList<PizzaModel> pizzaModels = new ArrayList<>();
    int[] pizzaImages = {R.drawable.chicagodefault, R.drawable.chicagodeluxe, R.drawable.chicagobbq, R.drawable.chicagomeat,
            R.drawable.nydefault, R.drawable.nydeluxe, R.drawable.nybbq, R.drawable.nymeatzza};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_pizza, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.pizzaRecyclerView);
        setUpPizzaModels();
        PizzaRecyclerViewAdapter pizzaRecyclerViewAdapter = new PizzaRecyclerViewAdapter(view.getContext(), pizzaModels, this);
        recyclerView.setAdapter(pizzaRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.radioGroup = view.findViewById(R.id.radioSizeGroup);

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
        createPizza(position);
        showAlertDialogToppings(position, view);
    }

    @Override
    public void onItemClick2(int position, View view) {
        createPizza(position);
        showAlertDialogPrice(position, view);
    }

    private void createPizza(int position) {
        this.radioGroup = requireView().findViewById(R.id.radioSizeGroup);
        int selectedId = this.radioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSize = requireView().findViewById(selectedId);

        switch (position) {
            case 0:
                this.pizza = new ChicagoPizza().createBuildYourOwn();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 1:
                this.pizza = new ChicagoPizza().createDeluxe();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 2:
                this.pizza = new ChicagoPizza().createBBQChicken();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 3:
                this.pizza = new ChicagoPizza().createMeatzza();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 4:
                this.pizza = new NYPizza().createBuildYourOwn();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 5:
                this.pizza = new NYPizza().createDeluxe();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 6:
                this.pizza = new NYPizza().createBBQChicken();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
            case 7:
                this.pizza = new NYPizza().createMeatzza();
                this.pizza.setSize(Size.valueOf(radioButtonSize.getText().toString().toUpperCase()));
                break;
        }
    }

    private void showAlertDialogToppings(int position, View view) {
        ArrayList<String> selectedToppings = new ArrayList<>();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle(R.string.select_topping_title);
        String[] toppings = requireActivity().getResources().getStringArray(R.array.toppings);
        boolean[] checkedItems = new boolean[13];

        alertDialog.setMultiChoiceItems(toppings, checkedItems, (dialogInterface, i, selected) -> {
            if (selected) {
                if (selectedToppings.size() == getResources().getInteger(R.integer.max_toppings)) {
                    Toast.makeText(view.getContext(), R.string.max_topping_warning, (Toast.LENGTH_SHORT)).show();
                    checkedItems[i] = false;
                    ((AlertDialog) dialogInterface).getListView().setItemChecked(i, false);
                } else {
                    selectedToppings.add(toppings[i]);
                    Topping toppingToAdd = Topping.returnToppingEnumFromString(toppings[i]);
                    this.pizza.add(toppingToAdd);
                }
            } else {
                selectedToppings.remove(toppings[i]);
                this.pizza.remove(Topping.returnToppingEnumFromString(toppings[i]));
            }

            String formattedPrice = String.format(Locale.US, "Pizza Subtotal: $%.2f", this.pizza.price());
            Toast.makeText(view.getContext(), formattedPrice, Toast.LENGTH_SHORT).show();
        });

        alertDialog.setPositiveButton(R.string.continue_option, (dialogInterface, i) -> showAlertDialogPrice(position, view));

        alertDialog.setNegativeButton(R.string.cancel_option, (dialogInterface, i) -> Toast.makeText(view.getContext(), R.string.order_cancelled_notif, (Toast.LENGTH_SHORT)).show());

        String formattedPrice = String.format(Locale.US, "Current Pizza Subtotal: $%.2f", this.pizza.price());
        Toast.makeText(view.getContext(), formattedPrice, Toast.LENGTH_SHORT).show();
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void showAlertDialogPrice(int position, View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle(R.string.confirm_order_title);
        alertDialog.setMessage(String.format(Locale.US, "%s %.2f", getResources().getString(R.string.price_label), this.pizza.price()));

        alertDialog.setPositiveButton(R.string.place_order_option, (dialogInterface, i) -> {
            Toast.makeText(view.getContext(), R.string.order_placed_notif, (Toast.LENGTH_SHORT)).show();
            Order order = ((MainActivity) requireActivity()).getCurrentOrder();
            order.add(this.pizza);
        });

        alertDialog.setNegativeButton(R.string.cancel_option, (dialogInterface, i) -> Toast.makeText(view.getContext(), R.string.order_cancelled_notif, (Toast.LENGTH_SHORT)).show());

       alertDialog.create().show();
    }

}