package maen.edu.rental_house;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

// class view content of registration of SigUp for Tenant

public class TenantSignup extends AppCompatActivity {
    EditText Email;
    EditText FName;
    EditText LName;
    EditText password;
    EditText CPassword;
    EditText GrossSalary;
    EditText Occupation;
    EditText FamilySize;
    EditText Phone;
    Button  SignUp;
    Button  Back;
    Spinner spinnerGender;
    Spinner Nationality;
    Spinner Country;
    Spinner City;
    Spinner Zip;

    // set Name and check it
    public static void setName(EditText Name, String name) {
        Name.addTextChangedListener(new LightTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (Name.getText().toString().length() < 3 || Name.getText().toString().length() > 20) {
                    Name.setError(name + " name minimum length is 3");
                }
                Name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });
    }


    // set password and check it
    public static void setPassword(EditText password) {
        password.addTextChangedListener(new LightTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                /*
                 * ^                   # start-of-string
                 * .*[0-9].*           # a digit must occur at least once
                 * .*[A-Za-z].*        # one character at least
                 * .*[!@#$%^&*()_+].*  # a special character must occur at least once
                 * .{8,15}             # anything, at least 8 places though to 15
                 * $                   # end-of-string
                 */
                String digitPattern = ".*[0-9].*";
                String characterPattern1 = ".*[A-Z].*";
                String characterPattern2 = ".*[a-z].*";
                String specialCharacterPattern = ".*[!@#$%^&*()_+].*";
                String stringLength = "^.{8,15}$";
                String checkedMark = "\u2713";
                String error = "password must include at least the following :\n" +
                        "\t minimum length is 8 and maximum 15 %s\n" +
                        "\t one small character  and one capital character %s\n" +
                        "\t one number %s\n" +
                        "\t one special character %s ";
                String[] placeHolder = {"", "", "", "", ""};
                if (password.getText().toString().matches(stringLength))
                    placeHolder[0] = checkedMark;
                if (password.getText().toString().matches(characterPattern1))
                    placeHolder[1] = checkedMark;
                if (password.getText().toString().matches(characterPattern2))
                    placeHolder[2] = checkedMark;
                if (password.getText().toString().matches(digitPattern))
                    placeHolder[3] = checkedMark;
                if (password.getText().toString().matches(specialCharacterPattern))
                    placeHolder[4] = checkedMark;
                int finish = 0;
                for (String value : placeHolder) {
                    if (value.compareTo(checkedMark) == 0) {
                        finish++;
                    }
                }
                if (finish != 5) {
                    password.setError(String.format(error, placeHolder[0], placeHolder[1], placeHolder[2], placeHolder[3], placeHolder[4]));
                }
                password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);

            }
        });
    }

    // Confirm Password
    public static void checkPassword(EditText CPassword, EditText password) {
        CPassword.addTextChangedListener(new LightTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (CPassword.getText().toString().compareTo(password.getText().toString()) != 0) {
                    CPassword.setError("password does not match ");
                }
                CPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);

            }
        });
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenant_signup);

        Email =(EditText) findViewById(R.id.EmailSignIn);
        FName =(EditText) findViewById(R.id.FirstNameSignin);
        setName(FName, "First Name");
        LName =(EditText) findViewById(R.id.LastNameSignin);
        setName(LName, "Last Name");
        password =(EditText) findViewById(R.id.PasswordSignin);
        setPassword(password);
        CPassword =(EditText) findViewById(R.id.PasswordConSignin);
        checkPassword(CPassword,password);
        GrossSalary =(EditText) findViewById(R.id.GrossMonthlySalary);
        Occupation =(EditText) findViewById(R.id.Occupation);
        FamilySize =(EditText) findViewById(R.id.FamilySize);
        Phone =(EditText) findViewById(R.id.Phonenumber);
        spinnerGender =(Spinner)findViewById(R.id.Gender) ;
        SignUp=(Button) findViewById(R.id.signupT);
        Back=(Button) findViewById(R.id.TBack);
        City =(Spinner)findViewById(R.id.city) ;
        Zip =(Spinner)findViewById(R.id.zip);
        Country =(Spinner)findViewById(R.id.Currentresidencecountry);
        Nationality =(Spinner)findViewById(R.id.Nationality) ;


        /*
        - enter true email
        */
        Email.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!Email.getText().toString().matches(pattern))
                    Email.setError("please check your email");
                Email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);

            }
        });

        String[] GenderOp = {"Gender","Male", "Female"};
        ArrayAdapter objGenderArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GenderOp);
        spinnerGender.setAdapter(objGenderArr);


        String[] NationalityOp = {"Nationality","Algerian", "Austrian","British", "Canadian","Egyptian", "Iraqi","Jordanian", "Libyan","Turkish", "Palestinian"};
        ArrayAdapter objNationalityArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, NationalityOp);
        Nationality.setAdapter(objNationalityArr);


        String[] CountryOp = {"Country","Algeria", "Austria","Britain", "Canada","Egypt", "Iraq","Jordan", "Libya","Turkey", "Palestine"};
        ArrayAdapter objCountryArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CountryOp);
        Country.setAdapter(objCountryArr);

        // list of city in each country
        String[] CityOP   ={"City"};
        String[] AlgeriaOP   ={"Algiers","Oran","Batna","Constantine"};
        String[] AustriaOP   ={"Vienna","Styria","Upper Austria","Salzburg"};
        String[] BritainOP   ={"Birmingham","Cambridge","Lancaster","London"};
        String[] CanadaOP    ={"Ottawa","Toronto","Victoria","Halifax"};
        String[] EgyptOP     ={"Cairo","Alexandria","Gizeh","Shubra El-Kheima"};
        String[] IraqOP      ={"Baghdad","Basra","Kirkuk","Karbala "};
        String[] JordanOP    ={"Amman","Zarqa","Irbid","Aqaba"};
        String[] LibyaOP     ={"Tripoli","Benghazi","Misrata","Al Bayda"};
        String[] TurkeyOP    ={"İstanbul","Ankara","İzmir","Bursa"};
        String[] PalestineOP ={"Jerusalem","Ramallah","Tulkarm","Nablus"};
        // list of ZIP PHONE CODE for country
        String[] ZIPCityOP   ={"++"};
        String[] ZIPAlgeriaOP   ={"+213"};
        String[] ZIPAustriaOP   ={"+43"};
        String[] ZIPBritainOP   ={"+44"};
        String[] ZIPCanadaOP    ={"+1"};
        String[] ZIPEgyptOP     ={"+20"};
        String[] ZIPIraqOP      ={"+964"};
        String[] ZIPJordanOP    ={"+962"};
        String[] ZIPLibyaOP     ={"+218"};
        String[] ZIPTurkeyOP    ={"+90"};
        String[] ZIPPalestineOP ={"+970"};

        //Spinner Dependencies
        Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    ArrayAdapter objCityArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CityOP);
                    City.setAdapter(objCityArr);
                    ArrayAdapter objZIPCityArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPCityOP);
                    Zip.setAdapter(objZIPCityArr);
                }
                if(position ==1){
                    ArrayAdapter objAlgeriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AlgeriaOP);
                    City.setAdapter(objAlgeriaArr);
                    ArrayAdapter objZIPAlgeriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPAlgeriaOP);
                    Zip.setAdapter(objZIPAlgeriaArr);
                }
                if(position ==2){
                    ArrayAdapter objAustriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AustriaOP);
                    City.setAdapter(objAustriaArr);
                    ArrayAdapter objZIPAustriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPAustriaOP);
                    Zip.setAdapter(objZIPAustriaArr);
                }
                if(position ==3){
                    ArrayAdapter objBritainArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, BritainOP);
                    City.setAdapter(objBritainArr);
                    ArrayAdapter objZIPBritainArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPBritainOP);
                    Zip.setAdapter(objZIPBritainArr);
                }
                if(position ==4){
                    ArrayAdapter objCanadaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CanadaOP);
                    City.setAdapter(objCanadaArr);
                    ArrayAdapter objZIPCanadaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPCanadaOP);
                    Zip.setAdapter(objZIPCanadaArr);
                }
                if(position ==5){
                    ArrayAdapter objEgyptArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, EgyptOP);
                    City.setAdapter(objEgyptArr);
                    ArrayAdapter objZIPEgyptArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPEgyptOP);
                    Zip.setAdapter(objZIPEgyptArr);
                }
                if(position ==6){
                    ArrayAdapter objIraqArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, IraqOP);
                    City.setAdapter(objIraqArr);
                    ArrayAdapter objZIPIraqArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPIraqOP);
                    Zip.setAdapter(objZIPIraqArr);
                }
                if(position ==7){
                    ArrayAdapter objJordanArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, JordanOP);
                    City.setAdapter(objJordanArr);
                    ArrayAdapter objZIPJordanArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPJordanOP);
                    Zip.setAdapter(objZIPJordanArr);
                }
                if(position ==8){
                    ArrayAdapter objLibyaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LibyaOP);
                    City.setAdapter(objLibyaArr);
                    ArrayAdapter objZIPLibyaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPLibyaOP);
                    Zip.setAdapter(objZIPLibyaArr);
                }
                if(position ==9){
                    ArrayAdapter objTurkeyArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, TurkeyOP);
                    City.setAdapter(objTurkeyArr);
                    ArrayAdapter objZIPTurkeyArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPTurkeyOP);
                    Zip.setAdapter(objZIPTurkeyArr);
                }
                if(position ==10){
                    ArrayAdapter objPalestineArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, PalestineOP);
                    City.setAdapter(objPalestineArr);
                    ArrayAdapter objZIPPalestineArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPPalestineOP);
                    Zip.setAdapter(objZIPPalestineArr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TenantSignup.this,SignUp.class);
                TenantSignup.this.startActivity(intent);
                finish();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean IsValid = true;

                if (Email.getText().toString().isEmpty()) {
                    Email.setHintTextColor(Color.RED);
                    Email.setHint("Email is Required");
                    IsValid = false;
                }
                if (FName.getText().toString().isEmpty()) {
                    FName.setHintTextColor(Color.RED);
                    FName.setHint("First Name Is Required");
                    IsValid = false;
                }
                if (LName.getText().toString().isEmpty()) {
                    LName.setHintTextColor(Color.RED);
                    LName.setHint("Last Name Is Required");
                    IsValid = false;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setHintTextColor(Color.RED);
                    password.setHint("Password Is Required");
                    IsValid = false;
                }
                if (CPassword.getText().toString().isEmpty()) {
                    CPassword.setHintTextColor(Color.RED);
                    CPassword.setHint("Confirm Password Is Required");
                    IsValid = false;
                }
                if(FamilySize.getText().toString().isEmpty()){
                    FamilySize.setHintTextColor(Color.RED);
                    FamilySize.setHint("Family Size Is Required");
                    IsValid = false;
                }
                if(GrossSalary.getText().toString().isEmpty()){
                    GrossSalary.setHintTextColor(Color.RED);
                    GrossSalary.setHint("Gross Salary Size Is Required");
                    IsValid = false;
                }
                if(Occupation.getText().toString().isEmpty()){
                    Occupation.setHintTextColor(Color.RED);
                    Occupation.setHint("Occupation Size Is Required");
                    IsValid = false;
                }
                if (Phone.getText().toString().isEmpty()) {
                    Phone.setHintTextColor(Color.RED);
                    Phone.setHint("Phone Number Is Required");
                    IsValid = false;
                }


                if (IsValid == true) {//then all the fields and requirements are correct
                    Tenant tenant = new Tenant();
                    tenant.setEmail(Email.getText().toString());
                    tenant.setFName(FName.getText().toString());
                    tenant.setLName(LName.getText().toString());
                    tenant.setGender(spinnerGender.getSelectedItem().toString());
                    tenant.setPassword(HashPassword.hashPassword(password.getText().toString()));
                    tenant.setNationality(Nationality.getSelectedItem().toString());
                    tenant.setGrossMSalary(Double.parseDouble(GrossSalary.getText().toString()));
                    tenant.setOccupation(Occupation.getText().toString());
                    tenant.setFamilySize(Integer.parseInt(FamilySize.getText().toString()));
                    tenant.setCurrentRCountry(Country.getSelectedItem().toString());
                    tenant.setCity(City.getSelectedItem().toString());
                    tenant.setPhone(Integer.parseInt(Phone.getText().toString()));
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(TenantSignup.this, "RentalHouse", null, 1);
                    dataBaseHelper.insertTenant(tenant);
                    dataBaseHelper.insertLOGIN(Email.getText().toString(),1);
                    Intent intent = new Intent(TenantSignup.this,SignIn.class);
                    TenantSignup.this.startActivity(intent);
                    finish();
                }
            }
        });
    }
}
