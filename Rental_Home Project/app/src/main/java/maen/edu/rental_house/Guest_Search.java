package maen.edu.rental_house;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import maen.edu.rental_house.ui_Rental.Search.MySearchRecyclerViewAdapter;
import maen.edu.rental_house.ui_Rental.Search.Search;

public class Guest_Search extends AppCompatActivity {
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

    public  static ArrayList<Post_Info> list = new ArrayList<Post_Info>();
    String FY,BY,GY;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Search_city =(EditText) findViewById(R.id.Search_city);
        Search_minArea =(EditText) findViewById(R.id.Search_minimumSurfaceArea);
        Search_maxArea =(EditText) findViewById(R.id.Search_maximumSurfaceArea);
        Search_minBed =(EditText) findViewById(R.id.Search_minBedRoom);
        Search_maxBed =(EditText) findViewById(R.id.Search_maxBedRoom);
        Search_minPrice =(EditText) findViewById(R.id.Search_minPrice);
        Search_maxPrice =(EditText) findViewById(R.id.Search_maxPrice);
        Search_furnished =(CheckBox) findViewById(R.id.furnished);
        Search_unfurnished =(CheckBox) findViewById(R.id.Unfurnished);
        Search_balcony =(CheckBox) findViewById(R.id.balcony);
        Search_garden =(CheckBox) findViewById(R.id.garden);
        Search_search =(Button) findViewById(R.id.Search);
        Search_back =(Button) findViewById(R.id.BackS);

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
                DataBaseHelper dataBaseHelper = new DataBaseHelper( Guest_Search.this, "RentalHouse", null, 1);

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
                        BY ="NO";
                    }
                    if(Search_garden.isChecked()) {
                        search.setGarden(Search_garden.isChecked());
                        GY ="YES";
                    }else{
                        search.setNo_garden(Search_garden.isChecked());
                        GY ="NO";
                    }
                    // dataBaseHelper.insertSEARCH(search);
                    list = dataBaseHelper.Search(Search_city.getText().toString(),Integer.parseInt(Search_minArea.getText().toString()),
                            Integer.parseInt(Search_maxArea.getText().toString()),
                            Integer.parseInt(Search_minBed.getText().toString()),
                            Integer.parseInt(Search_maxBed.getText().toString()),FY,BY,GY,
                            Integer.parseInt(Search_minPrice.getText().toString()),
                            Integer.parseInt(Search_maxPrice.getText().toString()));


                    Intent intent=new Intent(Guest_Search.this, Show_GuestSearch.class);
                    Guest_Search.this.startActivity(intent);
                    finish();

                }

            }
        });
        Search_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent=new Intent(Guest_Search.this, AfterConnection.class);
                Guest_Search.this.startActivity(intent);
                finish();

            }
        });
    }
}
