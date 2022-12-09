package rutgers.rupizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import orders.Order;
import orders.StoreOrders;
import rutgers.rupizzeria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Order currentOrder;
    private static StoreOrders storeOrders;

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

    public Order getCurrentOrder() {
        return this.currentOrder;
    }
    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }
    public static void setStoreOrders(StoreOrders storeOrders) {
        MainActivity.storeOrders = storeOrders;
    }
    public void setNewOrder() {
        currentOrder = new Order();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, null).addToBackStack(null).commit();
    }

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
