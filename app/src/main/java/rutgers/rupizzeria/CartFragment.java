package rutgers.rupizzeria;

import android.content.Context;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import orders.Order;
import orders.StoreOrders;
import pizza.properties.Pizza;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements View.OnClickListener {
    private StoreOrders storeOrders;
    private Order currentOrder;
    private TextView orderNumber;
    private TextView subtotalTextView;
    private TextView taxTextView;
    private TextView totalTextView;
    private ListView orderList;
    private ArrayAdapter<Pizza> adapter;
    private Button placeOrderButton;
    private Button removeOrderButton;
    private Button clearOrderButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentOrder = ((MainActivity) requireActivity()).getCurrentOrder();
        this.storeOrders = ((MainActivity) requireActivity()).getStoreOrders();

        if (this.currentOrder == null) {
            return;
        }

        this.adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_activated_1, new ArrayList<>(this.currentOrder.getPizzasInOrder()));
    }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.place_order_button:
                if (this.currentOrder.getPizzasInOrder().isEmpty()) {
                    Toast.makeText(requireActivity(), "Empty Order!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Order placedOrder = new Order(this.currentOrder);
                this.storeOrders.add(placedOrder);
                ((MainActivity) requireActivity()).setNewOrder();
                this.adapter.clear();
                this.updatePrices();
                Toast.makeText(requireActivity(), "Order Placed!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_order_button:
                for (Iterator<Pizza> iterator = this.currentOrder.getPizzasInOrder().iterator(); iterator.hasNext(); ) {
                    Pizza pizza = iterator.next();
                    if (this.orderList.getCheckedItemPositions().get(this.adapter.getPosition(pizza))) {
                        iterator.remove();
                        this.adapter.remove(pizza);
                    }
                }

                Toast.makeText(getContext(), "Removed Selected Items", Toast.LENGTH_SHORT).show();
                this.updatePrices();
                break;
            case R.id.clear_order_button:
                this.currentOrder.getPizzasInOrder().clear();
                this.adapter.clear();
                this.updatePrices();
                break;
        }
    }

    private void updatePrices() {
        double subtotal = 0.0;
        for (Pizza pizza : this.currentOrder.getPizzasInOrder()) {
            subtotal += pizza.price();
        }

        this.subtotalTextView.setText(String.format(Locale.ROOT, "$%.2f", subtotal));
        this.taxTextView.setText(String.format(Locale.ROOT, "$%.2f", subtotal * (6.625 / 100)));
        this.totalTextView.setText(String.format(Locale.ROOT, "$%.2f", subtotal * (1 + (6.625 / 100))));
    }
}