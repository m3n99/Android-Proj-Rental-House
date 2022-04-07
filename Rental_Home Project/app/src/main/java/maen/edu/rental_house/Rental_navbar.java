package maen.edu.rental_house;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import maen.edu.rental_house.databinding.ActivityRentalNavBinding;

public class Rental_navbar extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityRentalNavBinding binding;
    public static Tenant tenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null)
            tenant = (Tenant) getIntent().getSerializableExtra("user");
        binding = ActivityRentalNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarRentalNav.toolbar);
        binding.appBarRentalNav.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayoutRental;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                    R.id.nav_Search, R.id.nav_history)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_rental_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // my code start here
        View  header = navigationView.getHeaderView(0);
        ImageView imageView = header.findViewById(R.id.imageViewR);
        TextView  textView_email = header.findViewById(R.id.EmailR);
        TextView textView_name = header.findViewById(R.id.NameR);
        textView_email.setText( SignIn.EmailAddressTenant);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(Rental_navbar.this, "RentalHouse", null, 1);
        Tenant tenant = dataBaseHelper.getTenant(SignIn.EmailAddressTenant);
        String Fname = tenant.getFName();
        String Lname = tenant.getLName();
        String Name = Fname +" "+ Lname;
        textView_name.setText(Name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rental_nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_rental_nav);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
