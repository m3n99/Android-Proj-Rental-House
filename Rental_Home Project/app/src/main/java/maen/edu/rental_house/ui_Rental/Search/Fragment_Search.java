package maen.edu.rental_house.ui_Rental.Search;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import maen.edu.rental_house.AfterConnection;
import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.Guest_Search;
import maen.edu.rental_house.LightTextWatcher;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Show_GuestSearch;
import maen.edu.rental_house.ui_Rental.Search.Search;


public class Fragment_Search extends Fragment {
    EditText Search_city;
    EditText Search_minArea;
    EditText Search_maxArea;
    EditText Search_minBed;
    EditText Search_maxBed;
    EditText Search_minPrice;
    EditText Search_maxPrice;
    CheckBox Search_furnished;
    CheckBox Search_unfurnished;
    CheckBox Search_balcony;
    CheckBox Search_garden;
    Button Search_search;
    Button Search_back;

    String FY,BY,GY;

    public static ArrayList<Post_Info> list = new ArrayList<Post_Info>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.search, container, false);
        super.onCreate(savedInstanceState);
        Search_city =(EditText) view.findViewById(R.id.Search_city);
        Search_minArea =(EditText) view.findViewById(R.id.Search_minimumSurfaceArea);
        Search_maxArea =(EditText) view.findViewById(R.id.Search_maximumSurfaceArea);
        Search_minBed =(EditText) view.findViewById(R.id.Search_minBedRoom);
        Search_maxBed =(EditText) view.findViewById(R.id.Search_maxBedRoom);
        Search_minPrice =(EditText) view.findViewById(R.id.Search_minPrice);
        Search_maxPrice =(EditText) view.findViewById(R.id.Search_maxPrice);
        Search_furnished =(CheckBox) view.findViewById(R.id.furnished);
        Search_unfurnished =(CheckBox) view.findViewById(R.id.Unfurnished);
        Search_balcony =(CheckBox) view.findViewById(R.id.balcony);
        Search_garden =(CheckBox) view.findViewById(R.id.garden);
        Search_search =(Button) view.findViewById(R.id.Search);
        Search_back =(Button) view.findViewById(R.id.BackS);



        Search_city.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[a-zA-Z].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_city.getText().toString().matches(pattern))
                    Search_city.setError("please check your City");
                Search_city.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        Search_minArea.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_minArea.getText().toString().matches(pattern))
                    Search_minArea.setError("please check your Minimum Area");
                if(Search_minArea.getText().toString().length()< 2)
                    Search_minArea.setError("Minimum Areas must at least 3 digit");
                Search_minArea.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        Search_maxArea.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_maxArea.getText().toString().matches(pattern))
                    Search_maxArea.setError("please check your Maximum Area");
                if(Search_maxArea.getText().toString().length()< 2)
                    Search_maxArea.setError("Maximum Areas must at least 3 digit");
                Search_maxArea.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        Search_minBed.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_minBed.getText().toString().matches(pattern))
                    Search_minBed.setError("please check your Minimum Bedrooms number");
                Search_minBed.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });


        Search_maxBed.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_maxBed.getText().toString().matches(pattern))
                    Search_maxBed.setError("please check your Maximum Bedrooms number");
                Search_maxBed.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        Search_minPrice.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_minPrice.getText().toString().matches(pattern))
                    Search_minPrice.setError("please check your Minimum Rental Price");
                Search_minPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });


        Search_maxPrice.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Search_maxPrice.getText().toString().matches(pattern))
                    Search_maxPrice.setError("please check your Maximum Rental price");
                Search_maxPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });


        Search_search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                boolean IsValid = true;
                DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);

               if (Search_city.getText().toString().isEmpty()) {
                    Search_city.setHintTextColor(Color.RED);
                    Search_city.setHint("City is Required");
                    IsValid = false;
                }
                if (Search_minArea.getText().toString().isEmpty()) {
                    Search_minArea.setHintTextColor(Color.RED);
                    Search_minArea.setHint("Min Area is Required");
                    IsValid = false;
                }
                if (Search_maxArea.getText().toString().isEmpty()) {
                    Search_maxArea.setHintTextColor(Color.RED);
                    Search_maxArea.setHint("Max Area is Required");
                    IsValid = false;
                }
                if (Search_minBed.getText().toString().isEmpty()) {
                    Search_minBed.setHintTextColor(Color.RED);
                    Search_minBed.setHint("Min number of Bedrooms is Required");
                    IsValid = false;
                }
                if (Search_maxBed.getText().toString().isEmpty()) {
                    Search_maxBed.setHintTextColor(Color.RED);
                    Search_maxBed.setHint("Max number of Bedrooms is Required");
                    IsValid = false;
                }
                if (Search_minPrice.getText().toString().isEmpty()) {
                    Search_minArea.setHintTextColor(Color.RED);
                    Search_minArea.setHint("Min Rental Price is Required");
                    IsValid = false;
                }
                if (Search_maxPrice.getText().toString().isEmpty()) {
                    Search_maxArea.setHintTextColor(Color.RED);
                    Search_maxArea.setHint("Max Rental Price is Required");
                    IsValid = false;
                }

                if (IsValid == true) {//then all the fields and requirements are correct
                    Search search = new Search();
                    search.setCity(Search_city.getText().toString());
                    search.setMin_area(Integer.parseInt(Search_minArea.getText().toString()));
                    search.setMax_area(Integer.parseInt(Search_maxArea.getText().toString()));
                    search.setMin_bedrooms(Integer.parseInt(Search_minBed.getText().toString()));
                    search.setMax_bedrooms(Integer.parseInt(Search_maxBed.getText().toString()));
                    search.setMin_rentalPrice(Integer.parseInt(Search_minPrice.getText().toString()));
                    search.setMax_rentalPrice(Integer.parseInt(Search_maxPrice.getText().toString()));

                    if(Search_furnished.isChecked() && !Search_unfurnished.isChecked()){
                        search.setFurnished(Search_furnished.isChecked());
                        FY ="YES";
                    }
                    if(Search_unfurnished.isChecked() && !Search_furnished.isChecked()){
                        search.setUnfurnished(Search_unfurnished.isChecked());
                        FY ="NO";
                    }
                    if(Search_balcony.isChecked()) {
                        search.setBalcony(Search_balcony.isChecked());
                        BY ="YES";
                    }else{
                        search.setNo_balcony(Search_balcony.isChecked());
                        BY= "NO";
                    }
                    if(Search_garden.isChecked()) {
                        search.setGarden(Search_garden.isChecked());
                        GY ="YES";
                    }else{
                        search.setNo_garden(Search_garden.isChecked());
                        GY ="NO";
                    }

                    list = dataBaseHelper.Search(Search_city.getText().toString(),Integer.parseInt(Search_minArea.getText().toString()),
                            Integer.parseInt(Search_maxArea.getText().toString()),
                            Integer.parseInt(Search_minBed.getText().toString()),
                            Integer.parseInt(Search_maxBed.getText().toString()),FY,BY,GY,
                            Integer.parseInt(Search_minPrice.getText().toString()),
                            Integer.parseInt(Search_maxPrice.getText().toString()));

                    Navigation.findNavController(view).navigate(R.id.nav_View_Search);
                }
            }

        });
        Search_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_home);
            }
        });
        return view;
    }
}

