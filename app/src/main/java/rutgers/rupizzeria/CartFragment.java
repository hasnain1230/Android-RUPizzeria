package rutgers.rupizzeria;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import orders.Order;
import pizza.properties.Pizza;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements View.OnClickListener {
    private Order currentOrder;
    private TextView orderNumber;
    private ListView orderList;
    private ArrayAdapter<Pizza> adapter;
    private Button placeOrderButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentOrder = ((MainActivity) getActivity()).getCurrentOrder();

        if (this.currentOrder == null) {
            return;
        }

        this.adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, new ArrayList<>(this.currentOrder.getPizzasInOrder()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        this.orderNumber = view.findViewById(R.id.cart_order_number);
        this.orderList = view.findViewById(R.id.cart_order_list);
        this.orderList.setAdapter(adapter);
        this.placeOrderButton = view.findViewById(R.id.place_order_button);
        this.placeOrderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.place_order_button:
                System.out.println("Order Placed");
                break;
        }
    }
}