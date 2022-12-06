package rutgers.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import rutgers.rupizzeria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new OrderPizzaFragment());

        binding.bottomNavigationView.setOnItemSelectedListener( item -> {
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

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}