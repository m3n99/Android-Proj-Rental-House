package maen.edu.rental_house;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Fragment_Post extends Fragment {

    EditText post_city;
    EditText post_postal;
    EditText post_area;
    EditText post_bedrooms;
    EditText post_price;
    EditText post_year;
    EditText post_date;
    EditText post_description;
    CheckBox post_furnished;
    CheckBox post_unfurnished;
    CheckBox post_garden;
    CheckBox post_balcony;
    Button Post_Add;
    String emailAgency;
    Button add_photo;
    Button Post_back;
    byte [] bytes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post, container, false);
        super.onCreate(savedInstanceState);
        post_city = (EditText) view.findViewById(R.id.Post_city);
        post_postal = (EditText) view.findViewById(R.id.Post_Postal);
        post_area = (EditText) view.findViewById(R.id.Post_Area);
        post_bedrooms = (EditText) view.findViewById(R.id.Post_BedRooms);
        post_price = (EditText) view.findViewById(R.id.Post_Price);
        post_year = (EditText) view.findViewById(R.id.Post_Year);
        post_date = (EditText) view.findViewById(R.id.Post_Date);
        post_description = (EditText) view.findViewById(R.id.Post_Description);
        post_furnished=(CheckBox) view.findViewById(R.id.Post_furnished);
        post_unfurnished=(CheckBox) view.findViewById(R.id.Post_unfurnished);
        post_garden =(CheckBox) view.findViewById(R.id.Post_garden);
        post_balcony = (CheckBox) view.findViewById(R.id.Post_balcony);
        Post_Add =(Button)view.findViewById(R.id.Post_Add);
        Post_back =(Button)view.findViewById(R.id.Post_back);
        add_photo =(Button) view.findViewById(R.id.Post_AddPhoto);

        emailAgency =SignIn.EmailAddressAgency;
        // Check if the post requirements the conditions
        // set Name and check it

        post_city.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[a-zA-Z].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_city.getText().toString().matches(pattern))
                    post_city.setError("please check your City");
                post_city.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);

            }
        });
        post_postal.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_postal.getText().toString().matches(pattern))
                    post_postal.setError("please check your Postal Address");
                if(post_postal.getText().toString().length()!=5)
                    post_postal.setError("Postal Address must be 5 digit");
                post_postal.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);

            }
        });

        post_area.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_area.getText().toString().matches(pattern))
                    post_area.setError("please check your Surface Area ");
                if(post_area.getText().toString().length()<3)
                    post_area.setError("Surface Area must be at least 3 digit and in m^2 ex: 150 mean 150 m^2");
                post_area.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        post_bedrooms.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_bedrooms.getText().toString().matches(pattern))
                    post_bedrooms.setError("please check your Number of Bedrooms");
                post_bedrooms.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        post_price.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_price.getText().toString().matches(pattern))
                    post_price.setError("please check your Rental Price");
                if(post_price.getText().toString().length()<4)
                    post_price.setError("Rental Price must be at least 4 digit");
                post_price.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        post_year.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_year.getText().toString().matches(pattern))
                    post_year.setError("please check your Construction Years");
                post_year.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        post_date.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "[0-3][0-9]/[0-2][0-9]/[0-9][0-9][0-9][0-9].*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_date.getText().toString().matches(pattern))
                    post_date.setError("please check your Availability Date must be in format: day/month/ year ==> xx/xx/xxxx" +
                            "ex: 25/04/2004 or 03/03/2020");
                post_date.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
        post_description.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^.{20,200}$.*";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!post_description.getText().toString().matches(pattern))
                    post_description.setError("please check your Description");
                post_description.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });

        add_photo.setOnClickListener(new View.OnClickListener(){
        public void onClick(View view) {
            Intent intentPicture = new Intent(Intent.ACTION_GET_CONTENT);
            intentPicture.setType("image/*");
            startActivityForResult(intentPicture, 1);

        }

        });
        Post_Add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean IsValid = true;

                if (post_city.getText().toString().isEmpty()) {
                    post_city.setHintTextColor(Color.RED);
                    post_city.setHint("City is Required");
                    IsValid = false;
                }
                if (post_postal.getText().toString().isEmpty()) {
                    post_postal.setHintTextColor(Color.RED);
                    post_postal.setHint(" Postal Number is Required");
                    IsValid = false;
                }
                if (post_area.getText().toString().isEmpty()) {
                    post_area.setHintTextColor(Color.RED);
                    post_area.setHint("Surface Area is Required");
                    IsValid = false;
                }
                if (post_bedrooms.getText().toString().isEmpty()) {
                    post_bedrooms.setHintTextColor(Color.RED);
                    post_bedrooms.setHint("Number of Bedrooms is Required");
                    IsValid = false;
                }
                if (post_price.getText().toString().isEmpty()) {
                    post_price.setHintTextColor(Color.RED);
                    post_price.setHint(" Rental Price is Required");
                    IsValid = false;
                }
                if (post_year.getText().toString().isEmpty()) {
                    post_year.setHintTextColor(Color.RED);
                    post_year.setHint("construction Year is Required");
                    IsValid = false;
                }
                if (post_date.getText().toString().isEmpty()) {
                    post_date.setHintTextColor(Color.RED);
                    post_date.setHint(" Availability Date is Required");
                    IsValid = false;
                }
                if (post_description.getText().toString().isEmpty()) {
                    post_description.setHintTextColor(Color.RED);
                    post_description.setHint("Description about Rental is Required");
                    IsValid = false;
                }

                if((!post_furnished.isChecked() && !post_unfurnished.isChecked()) ||
                        (post_furnished.isChecked() && post_unfurnished.isChecked())   ){
                    post_furnished.setHintTextColor(Color.RED);
                    Toast.makeText(getContext(), "Please Check Furnished Status select one of them", Toast.LENGTH_LONG).show();
                    IsValid = false;
                }


                if (IsValid == true) {//then all the fields and requirements are correct

                    Post_Info post = new Post_Info();
                    post.setCity(post_city.getText().toString());
                    post.setDescription(post_description.getText().toString());
                    post.setRental_price(Double.parseDouble(post_price.getText().toString()));
                    post.setSurfaceArea(Double.parseDouble(post_area.getText().toString()));
                    post.setDate(post_date.getText().toString());
                    post.setPostal(Integer.parseInt(post_postal.getText().toString()));
                    post.setYear(Integer.parseInt(post_year.getText().toString()));
                    post.setNum_bedroom(Integer.parseInt(post_bedrooms.getText().toString()));

                    if(post_furnished.isChecked() && !post_unfurnished.isChecked()){
                        post.setFurnished("YES");
                    }
                    if(post_unfurnished.isChecked() && !post_furnished.isChecked()){
                        post.setFurnished("NO");
                    }
                    if (post_balcony.isChecked()){
                        post.setBalcony("YES");
                    }
                    if (!post_balcony.isChecked())
                    {
                        post.setBalcony("NO");
                    }
                    if (post_garden.isChecked()){
                        post.setGarden("YES");
                    }
                    if (!post_garden.isChecked())
                    {
                        post.setGarden("NO");
                    }
                    post.setEmailAgency(emailAgency);

                    if (bytes != null){
                        if (bytes.length != 0){
                            post.setPhoto(bytes);
                        }
                    }
                    DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);
                    dataBaseHelper.insertPOST(post);
                    Toast.makeText(getContext(), "Post Insert successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Post_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_home);
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            assert data != null;
            Uri uriImage = data.getData();
            try (InputStream inputStream = requireActivity().getContentResolver().openInputStream(uriImage)) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                bytes = byteArrayOutputStream.toByteArray();

            } catch (Exception e) {
                Log.d("photo", "onActivityResult: " + e);
            }
        }
    }


}