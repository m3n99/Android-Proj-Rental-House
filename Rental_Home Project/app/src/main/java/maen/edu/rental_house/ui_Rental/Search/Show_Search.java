package maen.edu.rental_house.ui_Rental.Search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.SetOnRecyclerViewClickListener;
import maen.edu.rental_house.databinding.FragmentHomeBinding;

public class Show_Search extends Fragment implements SetOnRecyclerViewClickListener {
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private MySearchRecyclerViewAdapter mySearch;
    public static ArrayList<Post_Info> new_search= new ArrayList<Post_Info>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_rental, container, false);
        recyclerView = view.findViewById(R.id.HouseListModel);

        Fragment_Search search = new Fragment_Search();
        ArrayList<Post_Info> SEARCH = search.list;
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        int size_info = SEARCH.size();
        mySearch = new MySearchRecyclerViewAdapter(SEARCH, this);
        recyclerView.setAdapter(mySearch);
        mySearch.notifyDataSetChanged();
        return view;
    }

    @Override
    public void setOnRecyclerViewListClicked(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("House_Info", mySearch.getItem(position));
        Navigation.findNavController(view).navigate(R.id.nav_getPostHouse,bundle);
    }
}
