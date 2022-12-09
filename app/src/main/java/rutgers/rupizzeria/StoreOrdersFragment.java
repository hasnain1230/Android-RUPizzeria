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

public class StoreOrdersFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private StoreOrders storeOrders;
    private Spinner orderSpinner;
    private ListView orderList;
    private ArrayAdapter<Pizza> pizzaArrayAdapter;
    private ArrayAdapter<Integer> orderAdapter;
    private TextView totalTextView;
    private Button removeOrderButton;

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.orderList = requireView().findViewById(R.id.store_order_list_view);
        int orderNumber = (int) adapterView.getItemAtPosition(i);
        HashSet<Pizza> orders = Objects.requireNonNull(this.storeOrders.getOrders().get(orderNumber)).getPizzasInOrder();
        this.pizzaArrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, new ArrayList<>(orders));
        this.orderList.setAdapter(this.pizzaArrayAdapter);
        this.updatePrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void updatePrice() {
        HashSet<Pizza> pizzasInOrder = Objects.requireNonNull(this.storeOrders.getOrders().get((int) this.orderSpinner.getSelectedItem())).getPizzasInOrder();
        double total = 0;
        for (Pizza pizza : pizzasInOrder) {
            total += pizza.price();
        }
        // Add 6.625% tax to total
        total += total * 0.06625;
        this.totalTextView.setText(String.format(Locale.US, "$%.2f", total));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.store_order_cancel_order_button) {
            int orderNumber = (int) this.orderSpinner.getSelectedItem();
            Order orderToRemove = this.storeOrders.getOrders().get(orderNumber);

            assert this.storeOrders.remove(orderToRemove);
            this.orderAdapter.remove(orderNumber);
            this.orderAdapter.notifyDataSetChanged();
            this.pizzaArrayAdapter.clear();
            this.pizzaArrayAdapter.notifyDataSetChanged();
            this.totalTextView.setText("$0.00");

            Toast.makeText(requireActivity(), String.format(Locale.ROOT, "Order %d Removed", orderNumber), Toast.LENGTH_SHORT).show();
        }
    }
}