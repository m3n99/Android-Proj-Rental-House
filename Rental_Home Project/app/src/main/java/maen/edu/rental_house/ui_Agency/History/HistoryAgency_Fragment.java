package maen.edu.rental_house.ui_Agency.History;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.SetOnRecyclerViewClickListener;
import maen.edu.rental_house.SignIn;


public class HistoryAgency_Fragment extends Fragment implements SetOnRecyclerViewClickListener {
    private RecyclerView recyclerView;
    private MyHistoryAgencyRecyclerViewAdapter myHistory;
    String email;
    String name;
    public HistoryAgency_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history__agency, container, false);
        recyclerView= view.findViewById(R.id.HouseListAModel);
        DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);
        List <Reserve> reserves = dataBaseHelper.getHistory();
        email = SignIn.EmailAddressAgency;
        name = dataBaseHelper.getAgency(email).getAName();
      /*  System.out.println("Name form History: "+ name +" "+ email);
        System.out.println("Query "+ dataBaseHelper.isReserved_Agency(name));
        System.out.println("Adapter:   "+ myHistory );
        System.out.println(reserves);
       */
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        if (dataBaseHelper.isReserved_Agency(name)){
            myHistory = new MyHistoryAgencyRecyclerViewAdapter(reserves,this);
            recyclerView.setAdapter(myHistory);
            myHistory.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void setOnRecyclerViewListClicked(View view, int position) {

    }
}