package maen.edu.rental_house;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import maen.edu.rental_house.ui_Rental.History.MyHistoryRecyclerViewAdapter;


public class History_Fragment extends Fragment implements SetOnRecyclerViewClickListener {
    private RecyclerView recyclerView;
    private MyHistoryRecyclerViewAdapter myHistory;

    public History_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_history__rental, container, false);
        recyclerView= view.findViewById(R.id.HouseListModel);
        DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);
        List <Reserve> reserves = dataBaseHelper.getHistory();
        myHistory = new MyHistoryRecyclerViewAdapter(reserves,this);
        System.out.println(reserves);
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myHistory);
        myHistory.notifyDataSetChanged();
        return view;
    }

    @Override
    public void setOnRecyclerViewListClicked(View view, int position) {

    }
}