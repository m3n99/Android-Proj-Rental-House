package maen.edu.rental_house.ui_Agency.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.House;
import maen.edu.rental_house.MainActivity;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.SetOnRecyclerViewClickListener;
import maen.edu.rental_house.databinding.FragmentHomeBinding;
import maen.edu.rental_house.ui_Rental.home.MyHouseRecyclerViewAdapter;
import maen.edu.rental_house.ui_Rental.home.MyPostRecyclerViewAdapter;

public class HomeAgencyFragment extends Fragment implements SetOnRecyclerViewClickListener {

    private FragmentHomeBinding binding;
    private  RecyclerView recyclerView;
    private MyHouseRecyclerViewAdapter myHouse ;
    private MyPostRecyclerViewAdapter myPost;
    public static ArrayList<House> new_house = new ArrayList<House>();
    public static ArrayList<Post_Info> new_post= new ArrayList<Post_Info>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= view.findViewById(R.id.HouseListModel);

        DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);
        List<Post_Info> list = dataBaseHelper.gethouse();
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        myPost = new MyPostRecyclerViewAdapter(new ArrayList<Post_Info>(),this);
        myHouse = new MyHouseRecyclerViewAdapter(new ArrayList<House>(),this);

        int size_info = list.size();
        int size = MainActivity.house.size();
        new_house.clear();
        new_post.clear();
        if(size_info >= 5 ){
            for (int j=1 ; j < 6; j++) {
                new_post.add(list.get(size_info -j));
            }
            myPost = new MyPostRecyclerViewAdapter(new_post,this);
            recyclerView.setAdapter(myPost);
            myPost.notifyDataSetChanged();
        }
        if (size_info < 5 && size_info > 0) {
            myPost = new MyPostRecyclerViewAdapter(list, this);
            recyclerView.setAdapter(myPost);
            myPost.notifyDataSetChanged();
        }

        if (size_info == 0){
            if(size > 5){
                for (int i=1 ; i< MainActivity.house.size(); i++) {
                    new_house.add(MainActivity.house.get(size - i));
                }
                myHouse = new MyHouseRecyclerViewAdapter(new_house,this);
                recyclerView.setAdapter(myHouse);
                myHouse.notifyDataSetChanged();
            }else {
                myHouse = new MyHouseRecyclerViewAdapter(MainActivity.house,this);
                recyclerView.setAdapter(myHouse);
                myHouse.notifyDataSetChanged();
            }
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setOnRecyclerViewListClicked(View view, int position) {

      Bundle bundle = new Bundle();
        if (myHouse.getItemCount() != 0 ){
            bundle.putSerializable("House_Agency", myHouse.getItem(position));
            Navigation.findNavController(view).navigate(R.id.nav_getHouse,bundle);
        } else {
            bundle.putSerializable("House_Info_Agency", myPost.getItem(position));
            Navigation.findNavController(view).navigate(R.id.nav_getPostHouse,bundle);
        }
    }
}
