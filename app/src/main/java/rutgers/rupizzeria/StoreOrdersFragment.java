package rutgers.rupizzeria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

import orders.Order;
import orders.StoreOrders;
import pizza.properties.Pizza;

/**
 * This fragment is used to display the different orders that have been placed by the different users.
 * A simple {@link Fragment} subclass that contains the store orders fragment logic.
 */
public class StoreOrdersFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    /**
     * The {@code StoreOrders} object that is used to store all the {@code Orders} made.
     */
    private StoreOrders storeOrders;
    /**
     * The Spinner that stores all the order numbers.
     */
    private Spinner orderSpinner;
    /**
     * The ListView that stores all the {@code Pizza} objects to the user as Strings.
     */
    private ListView orderList;
    /**
     * The Adapter for the {@code orderList} that is used to display the {@code Pizza} objects.
     */
    private ArrayAdapter<Pizza> pizzaArrayAdapter;
    /**
     * The {@code ArrayAdapter} that stores all the order numbers for the spinner.
     */
    private ArrayAdapter<Integer> orderAdapter;
    /**
     * TextView that displays the total price of the order.
     */
    private TextView totalTextView;
    /**
     * Button that allows the user to delete the specified order.
     */
    private Button removeOrderButton;

    /**
     * Creates the store orders fragment. Also initializes the non-GUI related data.
     * @param savedInstanceState The saved instance state of where ever the user came from. In this case, the MainActivity, this provides a restore point to go back to on the stack frame.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.storeOrders = MainActivity.getStoreOrders();

        if (this.storeOrders == null) {
            return;
        }

        orderAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, new ArrayList<>(storeOrders.getOrders().keySet()));
        this.orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * Initializes the GUI components for the store orders fragment.
     * @param inflater The LayoutInflater object that can be used to inflate any views and GUI elements in the the fragment.
     * @param container The parent view group that the fragment's UI should be attached to.
     * @param savedInstanceState The saved instance state of where ever the user came from. In this case, the MainActivity, this provides a restore point to go back to on the stack frame.
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_orders, container, false);
        this.orderSpinner = view.findViewById(R.id.store_order_spinner);
        this.orderSpinner.setAdapter(this.orderAdapter);
        this.orderSpinner.setOnItemSelectedListener(this);
        this.removeOrderButton = view.findViewById(R.id.store_order_cancel_order_button);
        this.totalTextView = view.findViewById(R.id.store_order_total_text_view);

        this.removeOrderButton.setOnClickListener(this);

        return view;
    }

    /**
     * This method is called when the user selects an order number from the spinner. The ListView is then populated with the {@code Pizza} objects that are in the order.
     * @param adapterView The AdapterView where the selection happened.
     * @param view The view within the AdapterView that was clicked.
     * @param i The position of the view in the adapter.
     * @param l The row id of the item that is selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.orderList = requireView().findViewById(R.id.store_order_list_view);
        int orderNumber = (int) adapterView.getItemAtPosition(i);
        HashSet<Pizza> orders = Objects.requireNonNull(this.storeOrders.getOrders().get(orderNumber)).getPizzasInOrder();
        this.pizzaArrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, new ArrayList<>(orders));
        this.pizzaArrayAdapter.notifyDataSetChanged();
        this.orderList.setAdapter(this.pizzaArrayAdapter);
        this.updatePrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This method is called when the current selected order from the spinner changes. The price TextView also changes as a result.
     */
    private void updatePrice() {
        HashSet<Pizza> pizzasInOrder = Objects.requireNonNull(this.storeOrders.getOrders().get((int) this.orderSpinner.getSelectedItem())).getPizzasInOrder();
        double total = 0;
        for (Pizza pizza : pizzasInOrder) {
            total += pizza.price();
        }

        total += (total * 0.06625);
        this.totalTextView.setText(String.format(Locale.US, getResources().getString(R.string.money_format_string), total));
    }

    /**
     * This method is called when the user clicks the remove order button. The order is then removed from the {@code StoreOrders} object and the spinner is updated along with the ListView of all the Pizzas to that specific order.
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.store_order_cancel_order_button) {
            // Check if there are any orders
            if (this.storeOrders.getOrders().size() == 0) {
                Toast.makeText(requireActivity(), getResources().getString(R.string.no_orders_to_remove), Toast.LENGTH_SHORT).show();
                return;
            }

            int orderNumber = (int) this.orderSpinner.getSelectedItem();
            Order orderToRemove = this.storeOrders.getOrders().get(orderNumber);

            assert this.storeOrders.remove(orderToRemove);
            this.orderAdapter.remove(orderNumber);
            this.orderAdapter.notifyDataSetChanged();
            this.pizzaArrayAdapter.clear();

            if (this.storeOrders.getOrders().size() > 0) {
                this.orderSpinner.setSelection(0);
                this.pizzaArrayAdapter.addAll(Objects.requireNonNull(this.storeOrders.getOrders().get((int) this.orderSpinner.getSelectedItem())).getPizzasInOrder());
            }
            this.pizzaArrayAdapter.notifyDataSetChanged();
            this.totalTextView.setText("$0.00");

            Toast.makeText(requireActivity(), String.format(Locale.ROOT, getResources().getString(R.string.order_removed), orderNumber), Toast.LENGTH_SHORT).show();
        }
    }
}