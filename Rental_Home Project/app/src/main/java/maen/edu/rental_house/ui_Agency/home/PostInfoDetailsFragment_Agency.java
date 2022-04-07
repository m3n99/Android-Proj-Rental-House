package maen.edu.rental_house.ui_Agency.home;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.SignIn;


public class PostInfoDetailsFragment_Agency extends Fragment {
    public static ArrayList<Post_Info> post_inf = new ArrayList<Post_Info>();
    String emailAgency = null;
    String emailTenant =null;
    String FirstName= null;
    String LastName =null;

    public PostInfoDetailsFragment_Agency() {
        // Required empty public constructor
    }

    public static String getCurrentDateTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("EEE-dd/MM/yyyy\n\t\t HH:mm a");
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
        View view = inflater.inflate(R.layout.fragment_postinfo_details_agency, container, false);
        Bundle bundle = this.getArguments();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "RentalHouse", null, 1);


        TextView textViewPostalCity = view.findViewById(R.id.textViewPostCity);
        TextView textViewPostal=view.findViewById(R.id.textViewPostalAddress);
        TextView textViewsPostalSpace = view.findViewById(R.id.textViewPostalSpace);
        TextView textViewsPostalRooms = view.findViewById(R.id.textViewPostalRooms);
        TextView textViewsPostalFurnished = view.findViewById(R.id.textViewPostalFurnished);
        TextView textViewPostalBalcony = view.findViewById(R.id.textViewPostalBalcony);
        TextView textViewPostalGarden = view.findViewById(R.id.textViewPostalGarden);
        TextView textViewsPostalYear = view.findViewById(R.id.textViewPostalYear);
        TextView textViewsPostalPrice = view.findViewById(R.id.textViewRental);
        TextView textViewPostalDate = view.findViewById(R.id.textViewDate);
        TextView textViewsTPostalDescription = view.findViewById(R.id.textViewDescription);
        ImageView Image = view.findViewById(R.id.PhotoUpload);
        assert bundle != null;
        Post_Info post_inf = (Post_Info) bundle.getSerializable("House_Info_Agency");
        textViewPostalCity.setText(post_inf.getCity());
        textViewPostal.setText(String.valueOf(post_inf.getPostal()));
        textViewsPostalSpace.setText(String.valueOf(post_inf.getSurfaceArea()));
        textViewsPostalRooms.setText(String.valueOf(post_inf.getNum_bedroom()));
        textViewsPostalFurnished.setText(post_inf.getFurnished());
        textViewPostalBalcony.setText(post_inf.getBalcony());
        textViewPostalGarden.setText(post_inf.getGarden());
        textViewsPostalYear.setText(String.valueOf(post_inf.getYear()));
        textViewsPostalPrice.setText(String.valueOf(post_inf.getRental_price()));
        textViewPostalDate.setText(post_inf.getDate());
        textViewsTPostalDescription.setText(post_inf.getDescription());
        if (post_inf.getPhoto()!=null){
            Image.setImageBitmap(BitmapFactory.decodeByteArray(post_inf.getPhoto(), 0, post_inf.getPhoto().length));
        }

        return view;
    }
}