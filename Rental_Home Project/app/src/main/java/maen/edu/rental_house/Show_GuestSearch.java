package maen.edu.rental_house;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import maen.edu.rental_house.databinding.FragmentHomeBinding;
import maen.edu.rental_house.ui_Rental.Search.Fragment_Search;
import maen.edu.rental_house.ui_Rental.Search.MySearchRecyclerViewAdapter;

public class Show_GuestSearch extends AppCompatActivity implements SetOnRecyclerViewClickListener {

    private RecyclerView recyclerView;
    private MySearchGuestRecyclerViewAdapter mySearch;
    Button Back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_home_search);
        recyclerView = findViewById(R.id.HouseListModelViewSearch);
        Context context = Show_GuestSearch.this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Guest_Search search = new Guest_Search();
        ArrayList<Post_Info> SEARCH = search.list;

        System.out.println("From view:  " + SEARCH);

        mySearch = new MySearchGuestRecyclerViewAdapter(SEARCH, this);
        recyclerView.setAdapter(mySearch);
        mySearch.notifyDataSetChanged();

        Back = (Button) findViewById(R.id.Back_to_Search);
        Back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent=new Intent(Show_GuestSearch.this, Guest_Search.class);
                Show_GuestSearch.this.startActivity(intent);
                finish();
            }
        });
    }



    @Override
    public void setOnRecyclerViewListClicked(View view, int position) {
        Intent intent=new Intent(Show_GuestSearch.this, SignIn.class);
        Show_GuestSearch.this.startActivity(intent);
        Toast.makeText(Show_GuestSearch.this, "Please Sign in at First", Toast.LENGTH_SHORT).show();
        finish();

    }
}
