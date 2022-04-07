package maen.edu.rental_house;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AgencyProfile extends Fragment {
    Button edit;
    TextView email;
    EditText newAgencyPassword;
    EditText newAgencyName;
    EditText newAgencyCity;
    EditText newAgencyCountry;
    EditText newAgencyPhone;
    String emailAgency=null;
    String passwordAgency=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_agency_profile, container, false);
       String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%{}!])(?=\\S+$).{8,15}$";
       edit = (Button) view.findViewById(R.id.editAgency);
       email = (TextView) view.findViewById((R.id.EmailAgency));
       newAgencyPassword = (EditText) view.findViewById(R.id.newAgencyPassword);
       newAgencyName = (EditText) view.findViewById(R.id.newAgencyName);
       newAgencyCity = (EditText) view.findViewById(R.id.newAgencyCity);
       newAgencyCountry = (EditText) view.findViewById(R.id.newAgencyCountry);
       newAgencyPhone = (EditText) view.findViewById(R.id.newAgencyPhone);
       emailAgency=SignIn.EmailAddressAgency;
       passwordAgency=SignIn.PasswordAgency;

       System.out.println(emailAgency);
       email.setText(emailAgency);


       DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "RentalHouse", null, 1);
       RentingAgency search = dataBaseHelper.getAgency(emailAgency);
       System.out.println(search.getmPhone());
       System.out.println(search.getCountry());
       newAgencyName.setText(search.getAName());
       newAgencyPassword.setText(passwordAgency);
       newAgencyCountry.setText(search.getCountry());
       newAgencyCity.setText(search.getCity());
       newAgencyPhone.setText(""+search.getmPhone());
//------------------------------------------------------------
       edit.setOnClickListener((view1) -> {
           if (newAgencyName.getText().toString().length()==0||newAgencyPassword.getText().toString().length()==0|| newAgencyCountry.getText().toString().length()==0|| newAgencyCity.getText().toString().length()==0|| newAgencyPhone.getText().toString().length()==0){
               Toast.makeText(getActivity(),"Make Sure To Fill All Fields",Toast.LENGTH_LONG).show();

           }
           else if (!newAgencyName.getText().toString().matches(".*[a-zA-Z].*")){
               Toast.makeText(getActivity(), "Check Agency Name",Toast.LENGTH_LONG).show();

           }
           else if (newAgencyName.getText().length()>20 ){
               Toast.makeText(getActivity(), "Agency Name is Too Long !!!",Toast.LENGTH_LONG).show();
           }

           else if(!newAgencyPassword.getText().toString().matches(PASSWORD_PATTERN)){
               Toast.makeText(getActivity(), "The Password Is Too Weak Or Short !!\n The Password Must Contain Upper and Lower case ,Numbers and Special Char ",Toast.LENGTH_LONG).show();
           }


           else {
               RentingAgency Uagency = new RentingAgency();
               Uagency.setEmail(email.getText().toString());
               Uagency.setAName(newAgencyName.getText().toString());
               Uagency.setPassword(HashPassword.hashPassword(newAgencyPassword.getText().toString()));
               Uagency.setCountry(newAgencyCountry.getText().toString());
               Uagency.setCity(newAgencyCity.getText().toString());
               Uagency.setmPhone(Integer.parseInt(newAgencyPhone.getText().toString()));

               dataBaseHelper.updateAgency(Uagency);
               Toast.makeText(getActivity(), " SUCCESS ",Toast.LENGTH_LONG).show();
           }
       });

       return view;
   }





}