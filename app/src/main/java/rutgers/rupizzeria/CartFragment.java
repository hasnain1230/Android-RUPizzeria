package rutgers.rupizzeria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import orders.Order;
import orders.StoreOrders;
import pizza.properties.Pizza;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements View.OnClickListener {
    /**
     * The StoreOrders object that contains all the orders.
     */
    private StoreOrders storeOrders;
    /**
     * The current order object containing the pizzas.
     */
    private Order currentOrder;
    /**
     * The TextView that displays the order number
     */
    private TextView orderNumber;
    /**
     * The TextView that displays the subtotal price of the order
     */
    private TextView subtotalTextView;
    /**
     * The TextView that displays the tax price of the order
     */
    private TextView taxTextView;
    /**
     * The TextView that displays the total price of the order
     */
    private TextView totalTextView;
    /**
     * The ListView that displays the pizzas in the order
     */
    private ListView orderList;
    /**
     * The ArrayAdapter that displays the pizzas in the order
     */
    private ArrayAdapter<Pizza> adapter;
    /**
     * The place order button
     */
    private Button placeOrderButton;
    /**
     * The cancel order button
     */
    private Button removeOrderButton;
    /**
     * The clear all pizzas button
     */
    private Button clearOrderButton;

    /**
     * This method will initialize some basic non-GUI states of the Cart Fragment.
     * @param savedInstanceState The saved instance state of the fragment that we want to create.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentOrder = ((MainActivity) requireActivity()).getCurrentOrder();
        this.storeOrders = MainActivity.getStoreOrders();

        if (this.currentOrder == null) {
            return;
        }

        this.adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_activated_1, new ArrayList<>(this.currentOrder.getPizzasInOrder()));
    }

    /**
     * This method will initialize the GUI components of the Cart Fragment.
     * @param inflater The LayoutInflater that will inflate the layout with the GUI components.
     * @param container The ViewGroup that will contain the layout.
     * @param savedInstanceState The saved instance state of the fragment that we want to create.
     * @return The View that will be displayed.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        this.orderNumber = view.findViewById(R.id.cart_order_number);
        this.orderNumber.setText(String.valueOf(this.currentOrder.getOrderID()));
        this.subtotalTextView = view.findViewById(R.id.subtotal_text_view);
        this.taxTextView = view.findViewById(R.id.sales_tax_text_view);
        this.totalTextView = view.findViewById(R.id.order_total_text_view);
        this.updatePrices();
        this.orderList = view.findViewById(R.id.cart_order_list);
        this.orderList.setAdapter(adapter);
        this.placeOrderButton = view.findViewById(R.id.place_order_button);
        this.placeOrderButton.setOnClickListener(this);
        this.removeOrderButton = view.findViewById(R.id.remove_order_button);
        this.removeOrderButton.setOnClickListener(this);
        this.clearOrderButton = view.findViewById(R.id.clear_order_button);
        this.clearOrderButton.setOnClickListener(this);

        return view;
    }

    /**
     * This method will update the prices of the order based on the corresponding GUI button that is pressed.
     * @param view The View that was clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.place_order_button:
                if (this.currentOrder.getPizzasInOrder().isEmpty()) {
                    Toast.makeText(requireActivity(), getResources().getString(R.string.empty_order), Toast.LENGTH_SHORT).show();
                    return;
                }
                this.storeOrders.add(this.currentOrder);
                ((MainActivity) requireActivity()).setNewOrder();
                this.currentOrder = ((MainActivity) requireActivity()).getCurrentOrder();
                this.adapter.clear();
                this.updatePrices();
                break;
            case R.id.remove_order_button:
                SparseBooleanArray checked = this.orderList.getCheckedItemPositions();
                List<Pizza> pizzasToRemove = new ArrayList<>();

                for (int i = 0; i < checked.size(); i++) {
                    if (checked.valueAt(i)) {
                        pizzasToRemove.add(this.adapter.getItem(checked.keyAt(i)));
                    }
                }

                for (Pizza pizza : pizzasToRemove) {
                    this.currentOrder.remove(pizza);
                    this.adapter.remove(pizza);
                }

                this.adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), getResources().getString(R.string.remove_selected_items), Toast.LENGTH_SHORT).show();
                this.updatePrices();
                break;
            case R.id.clear_order_button:
                this.currentOrder.getPizzasInOrder().clear();
                this.adapter.clear();
                this.updatePrices();
                break;
        }
    }

    /**
     * Updates the subtotal, tax, and total prices of the order on the GUI.
     */
    private void updatePrices() {
        double subtotal = 0.0;
        for (Pizza pizza : this.currentOrder.getPizzasInOrder()) {
            subtotal += pizza.price();
        }

        this.subtotalTextView.setText(String.format(Locale.US, getResources().getString(R.string.money_format_string), subtotal));
        this.taxTextView.setText(String.format(Locale.US, getResources().getString(R.string.money_format_string), subtotal * (6.625 / 100)));
        this.totalTextView.setText(String.format(Locale.US, getResources().getString(R.string.money_format_string), subtotal * (1 + (6.625 / 100))));
    }
}