package rutgers.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import orders.Order;
import orders.StoreOrders;
import rutgers.rupizzeria.databinding.ActivityMainBinding;

/**
 * Main Activity for the Rutgers University Pizzeria App. This activity contains the main navigation view and all the fragments
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The {@code Order} object that is used to store all pizzas in an order
     */
    private Order currentOrder;
    /**
     * The {@code StoreOrders} object that is used to store all the orders made with pizzas
     */
    private static StoreOrders storeOrders;

    /**
     * Creates the main activity. This method is called when the app is first opened.
     * @param savedInstanceState The saved instance state of where ever the user came from in case they want to return to it. We save their previous context to come back to it.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rutgers.rupizzeria.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new OrderPizzaFragment());
        this.currentOrder = new Order();
        storeOrders = new StoreOrders();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.orderPizza:
                    replaceFragment(new OrderPizzaFragment());
                    break;
                case R.id.storeOrders:
                    replaceFragment(new StoreOrdersFragment());
                    break;
                case R.id.cart:
                    replaceFragment(new CartFragment());
                    break;
            }
            return true;
        });
    }

    /**
     * @return Returns the current order.
     */
    public Order getCurrentOrder() {
        return this.currentOrder;
    }

    /**
     * @return Returns the current store orders.
     */
    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }

    /**
     * Initializes a new store order that can be used. Is only called once the user places an order.
     */
    public void setNewOrder() {
        currentOrder = new Order();
    }

    /**
     * Replaces the current fragment with a new fragment and commits it.
     * @param fragment The fragment to replace the current fragment with.
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, null).addToBackStack(null).commit();
    }

    /**
     * Creates a new alert dialog asking the user if they want to exit.
     */
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", (dialog, which) -> finish());
            builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
