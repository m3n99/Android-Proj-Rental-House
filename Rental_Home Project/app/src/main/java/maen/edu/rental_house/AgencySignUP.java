package maen.edu.rental_house;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AgencySignUP  extends AppCompatActivity {
    EditText AEmail;
    EditText AName;
    EditText APassword;
    EditText ACPassword;
    EditText APhone;
    Button Agency_SignUp;
    Button Agency_Back;
    Spinner ACountry;
    Spinner ACity;
    Spinner AZip;

//--------------------------------------------------------------------

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
                 * .{5,}               # anything, at least five places though
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
        setContentView(R.layout.renting_agency_singup);

        AEmail =(EditText) findViewById(R.id.Agency_Email);
        AName =(EditText) findViewById(R.id.Agency_Name);
        setName(AName, "Agency Name");
        APassword =(EditText) findViewById(R.id.Agency_Password);
        setPassword(APassword);
        ACPassword =(EditText) findViewById(R.id.Agency_CPassword);
        checkPassword(ACPassword,APassword);
        APhone =(EditText) findViewById(R.id.Agency_Phone);
        ACountry=(Spinner)findViewById(R.id.Agency_Country);
        ACity=(Spinner)findViewById(R.id.Agency_City);
        AZip=(Spinner)findViewById(R.id.Agency_zip);
        Agency_SignUp=(Button) findViewById(R.id.Agency_signup);
        Agency_Back=(Button) findViewById(R.id.Agency_back);

          /*
        - enter true email
        */
        AEmail.addTextChangedListener(new LightTextWatcher() {
            final String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!AEmail.getText().toString().matches(pattern))
                    AEmail.setError("please check your email");
                AEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success_green_check_mark, 0);
            }
        });


        String[] CountryOp = {"Country","Algeria", "Austria","Britain", "Canada","Egypt", "Iraq","Jordan", "Libya","Turkey", "Palestine"};
        ArrayAdapter objCountryArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CountryOp);
        ACountry.setAdapter(objCountryArr);

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
        ACountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    ArrayAdapter objCityArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CityOP);
                    ACity.setAdapter(objCityArr);
                    ArrayAdapter objZIPCityArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPCityOP);
                    AZip.setAdapter(objZIPCityArr);
                }
                if(position ==1){
                    ArrayAdapter objAlgeriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AlgeriaOP);
                    ACity.setAdapter(objAlgeriaArr);
                    ArrayAdapter objZIPAlgeriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPAlgeriaOP);
                    AZip.setAdapter(objZIPAlgeriaArr);
                }
                if(position ==2){
                    ArrayAdapter objAustriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AustriaOP);
                    ACity.setAdapter(objAustriaArr);
                    ArrayAdapter objZIPAustriaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPAustriaOP);
                    AZip.setAdapter(objZIPAustriaArr);
                }
                if(position ==3){
                    ArrayAdapter objBritainArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, BritainOP);
                    ACity.setAdapter(objBritainArr);
                    ArrayAdapter objZIPBritainArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPBritainOP);
                    AZip.setAdapter(objZIPBritainArr);
                }
                if(position ==4){
                    ArrayAdapter objCanadaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CanadaOP);
                    ACity.setAdapter(objCanadaArr);
                    ArrayAdapter objZIPCanadaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPCanadaOP);
                    AZip.setAdapter(objZIPCanadaArr);
                }
                if(position ==5){
                    ArrayAdapter objEgyptArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, EgyptOP);
                    ACity.setAdapter(objEgyptArr);
                    ArrayAdapter objZIPEgyptArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPEgyptOP);
                    AZip.setAdapter(objZIPEgyptArr);
                }
                if(position ==6){
                    ArrayAdapter objIraqArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, IraqOP);
                    ACity.setAdapter(objIraqArr);
                    ArrayAdapter objZIPIraqArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPIraqOP);
                    AZip.setAdapter(objZIPIraqArr);
                }
                if(position ==7){
                    ArrayAdapter objJordanArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, JordanOP);
                    ACity.setAdapter(objJordanArr);
                    ArrayAdapter objZIPJordanArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPJordanOP);
                    AZip.setAdapter(objZIPJordanArr);
                }
                if(position ==8){
                    ArrayAdapter objLibyaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LibyaOP);
                    ACity.setAdapter(objLibyaArr);
                    ArrayAdapter objZIPLibyaArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPLibyaOP);
                    AZip.setAdapter(objZIPLibyaArr);
                }
                if(position ==9){
                    ArrayAdapter objTurkeyArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, TurkeyOP);
                    ACity.setAdapter(objTurkeyArr);
                    ArrayAdapter objZIPTurkeyArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPTurkeyOP);
                    AZip.setAdapter(objZIPTurkeyArr);
                }
                if(position ==10){
                    ArrayAdapter objPalestineArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, PalestineOP);
                    ACity.setAdapter(objPalestineArr);
                    ArrayAdapter objZIPPalestineArr = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ZIPPalestineOP);
                    AZip.setAdapter(objZIPPalestineArr);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Agency_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgencySignUP.this,SignUp.class);
                AgencySignUP.this.startActivity(intent);
                finish();
            }
        });

        Agency_SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean IsValid = true;

                if (AEmail.getText().toString().isEmpty()) {
                    AEmail.setHintTextColor(Color.RED);
                    AEmail.setHint("Email is Required");
                    IsValid = false;
                }
                if (AName.getText().toString().isEmpty()) {
                    AName.setHintTextColor(Color.RED);
                    AName.setHint("First Name Is Required");
                    IsValid = false;
                }

                if (APassword.getText().toString().isEmpty()) {
                    APassword.setHintTextColor(Color.RED);
                    APassword.setHint("Password Is Required");
                    IsValid = false;
                }
                if (ACPassword.getText().toString().isEmpty()) {
                    ACPassword.setHintTextColor(Color.RED);
                    ACPassword.setHint("Confirm Password Is Required");
                    IsValid = false;
                }

                if (APhone.getText().toString().isEmpty()) {
                    APhone.setHintTextColor(Color.RED);
                    APhone.setHint("Phone Number Is Required");
                    IsValid = false;
                }


                if (IsValid == true) {//then all the fields and requirements are correct
                    RentingAgency agency = new RentingAgency();
                    agency.setEmail(AEmail.getText().toString());
                    agency.setAName(AName.getText().toString());
                    agency.setPassword(HashPassword.hashPassword(APassword.getText().toString()));
                    agency.setCountry(ACountry.getSelectedItem().toString());
                    agency.setCity(ACity.getSelectedItem().toString());
                    agency.setmPhone(Integer.parseInt(APhone.getText().toString()));
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AgencySignUP.this, "RentalHouse", null, 1);
                    dataBaseHelper.insertAgency(agency);
                    dataBaseHelper.insertLOGIN(AEmail.getText().toString(),0);
                    Intent intent = new Intent(AgencySignUP.this,SignIn.class);
                    AgencySignUP.this.startActivity(intent);
                    finish();
                }
            }
        });
    }
}
