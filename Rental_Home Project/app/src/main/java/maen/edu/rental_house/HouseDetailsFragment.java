package maen.edu.rental_house;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class HouseDetailsFragment extends Fragment {
    public static ArrayList<House> house = new ArrayList<House>();


    public HouseDetailsFragment() {
        // Required empty public constructor
    }

    public static String getCurrentDateTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("EEE-dd/MM/yyyy\n\t\tHH:mm a");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house_details_agnecy, container, false);
        Bundle bundle = this.getArguments();
        TextView textViewCity = view.findViewById(R.id.textViewCity);
        TextView textViewAddress=view.findViewById(R.id.textViewAddress);
        TextView textViewsSpace = view.findViewById(R.id.textViewSpace);
        TextView textViewsRooms = view.findViewById(R.id.textViewRooms);
        TextView textViewsBalcony = view.findViewById(R.id.textViewBaclony);
        TextView textViewsFurnished = view.findViewById(R.id.textViewFurnished);
        TextView textViewsPool = view.findViewById(R.id.textViewPool);
        TextView textViewsGarden = view.findViewById(R.id.textViewGarden);
        TextView textViewsConstYear = view.findViewById(R.id.textViewConstYear);
        TextView textViewsPrice = view.findViewById(R.id.textViewPrice);
        TextView textViewsType = view.findViewById(R.id.textViewType);
        Button buttonReserve = view.findViewById(R.id.buttonReserve);

        assert bundle != null;
        House house = (House) bundle.getSerializable("House");
        textViewCity.setText(house.getCity());
        textViewAddress.setText(house.getAddress());
        textViewsSpace.setText(String.valueOf(house.getSpace()));
        textViewsRooms.setText(String.valueOf(house.getRooms()));
        textViewsBalcony.setText(house.getBalcony());
        textViewsFurnished.setText(house.getFurnished());
        textViewsPool.setText(house.getPool());
        textViewsGarden.setText(house.getGarden());
        textViewsConstYear.setText(String.valueOf(house.getConstructingYear()));
        textViewsPrice.setText(String.valueOf(house.getPrice()));
        textViewsType.setText(house.getType());


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "RentalHouse", null, 1);

        buttonReserve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Reserve reserve = new Reserve();
                if (!dataBaseHelper.isReserved_House(textViewCity.getText().toString(),textViewAddress.getText().toString())){
                    reserve.setCity(textViewCity.getText().toString());
                    reserve.setAddress(textViewAddress.getText().toString());
                    String date = getCurrentDateTime();
                    reserve.setDate(date);
                    dataBaseHelper.insertReserve(reserve);
                    house.setReserved(true);
                    Toast.makeText(getContext(), "reserved successfully", Toast.LENGTH_SHORT).show();
                }else{
                   Toast.makeText(getContext(), "It's already Reserved", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}