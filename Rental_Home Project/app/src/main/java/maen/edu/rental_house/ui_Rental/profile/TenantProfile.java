package maen.edu.rental_house.ui_Rental.profile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.HashPassword;
import maen.edu.rental_house.R;
import maen.edu.rental_house.SignIn;
import maen.edu.rental_house.Tenant;

public class TenantProfile extends Fragment {
    Button editTenant;
    TextView email;
    TextView firstName;
    TextView lastName;
    TextView nationality;
    TextView gender;
    EditText password;
    EditText country;
    EditText city;
    EditText phoneNumber;
    EditText monthlySalary;
    EditText occupation;
    EditText familySize;
    String emailTenant=null;
    String passwordTenant=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%{}!])(?=\\S+$).{8,15}$";
        View view= inflater.inflate(R.layout.fragment_tenant_profile, container, false);
        editTenant =(Button) view.findViewById(R.id.editTenant );
        email =(TextView) view.findViewById(R.id.EmailTenant);
        firstName=(TextView) view.findViewById(R.id.firstNameProflie);
        lastName=(TextView) view.findViewById(R.id.secondNameProfile);
        nationality=(TextView) view.findViewById(R.id.NationalityProfile);
        gender=(TextView) view.findViewById(R.id.GenderTenantProfile);
        password=(EditText) view.findViewById(R.id.PasswordTenantProfile);
        country=(EditText) view.findViewById(R.id.tenantCountryProfile);
        city=(EditText) view.findViewById(R.id.TenantCityProfile);
        phoneNumber=(EditText) view.findViewById(R.id.TenantPhoneNumberProfile);
        familySize=(EditText) view.findViewById(R.id.familySizeTenantProfile);
        monthlySalary=(EditText) view.findViewById(R.id.monthlySalaryTenantProfile);
        occupation=(EditText) view.findViewById(R.id.ocupationTenantProfile);

        emailTenant= SignIn.EmailAddressTenant;
        passwordTenant=SignIn.PasswordTenant;

        System.out.println(emailTenant);
        email.setText(emailTenant);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "RentalHouse", null, 1);
        Tenant search = dataBaseHelper.getTenant(emailTenant);

        firstName.setText(search.getFName());
        lastName.setText(search.getLName());
        password.setText(passwordTenant);
        nationality.setText(search.getNationality());
        country.setText(search.getCurrentRCountry());
        city.setText(search.getCity());
        occupation.setText(search.getOccupation());
        gender.setText(search.getGender());
        phoneNumber.setText(""+search.getPhone());
        familySize.setText(""+search.getFamilySize());
        monthlySalary.setText(""+search.getGrossMSalary());

        editTenant.setOnClickListener((view1) -> {
            if (password.getText().toString().length()==0||country.getText().toString().length()==0|| city.getText().toString().length()==0|| phoneNumber.getText().toString().length()==0||
                    monthlySalary.getText().toString().length()==0|| occupation.getText().toString().length()==0|| familySize.getText().toString().length()==0){
                Toast.makeText(getActivity(),"Make Sure To Fill All Fields",Toast.LENGTH_LONG).show();

            } else if (!familySize.getText().toString().matches(".*[0-9].*")){
                Toast.makeText(getActivity(), "Make Sure That Family Size Is Number ",Toast.LENGTH_LONG).show();

            }
            else if (!monthlySalary.getText().toString().matches(".*[0-9.].*")){
                Toast.makeText(getActivity(), "Make Sure That Monthly Salary Is Number ",Toast.LENGTH_LONG).show();

            }


            else if(!password.getText().toString().matches(PASSWORD_PATTERN)){
                Toast.makeText(getActivity(), "The Password Is Too Weak Or Short !!\n The Password Must Contain Upper and Lower case ,Numbers and Special Char ",Toast.LENGTH_LONG).show();
            }


            else {
                Tenant Utenant = new Tenant();
                Utenant.setEmail(email.getText().toString());
                Utenant.setPassword(HashPassword.hashPassword(password.getText().toString()));
                Utenant.setFName(firstName.getText().toString());
                Utenant.setLName(lastName.getText().toString());
                Utenant.setCurrentRCountry(country.getText().toString());
                Utenant.setCity(city.getText().toString());
                Utenant.setGender(gender.getText().toString());
                Utenant.setNationality(nationality.getText().toString());
                Utenant.setOccupation(occupation.getText().toString());
                Utenant.setPhone(Integer.parseInt(phoneNumber.getText().toString()));
                Utenant.setFamilySize(Integer.parseInt(familySize.getText().toString()));
                Utenant.setGrossMSalary(Double.parseDouble(monthlySalary.getText().toString()));

                dataBaseHelper.updateTenant(Utenant);
                Toast.makeText(getActivity(), " SUCCESS ",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}