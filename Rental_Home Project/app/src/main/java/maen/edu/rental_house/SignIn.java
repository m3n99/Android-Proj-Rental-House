package maen.edu.rental_house;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class SignIn extends AppCompatActivity {
    Button SignIn;
    Button SignUp;
    Button GoSearch;
    EditText SEmail;
    EditText SPassword;
    CheckBox remember;
    ImageView imageLog;
    SharedPrefManager sharedPrefManager;
    public static String EmailAddressAgency;
    public static String EmailAddressTenant;
    public static String PasswordAgency;
    public static String PasswordTenant;
    public static boolean IsSignIn=false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        SignUp = (Button)findViewById(R.id.signup);
        SignIn = (Button)findViewById(R.id.signin);
        GoSearch= (Button)findViewById(R.id.GoSearch);
        remember = (CheckBox) findViewById(R.id.remember);
        SEmail = (EditText) findViewById(R.id.Email);
        SPassword = (EditText) findViewById(R.id.Password);
        imageLog =(ImageView) findViewById(R.id.imageViewsLoginA) ;
        sharedPrefManager = SharedPrefManager.getInstance(this);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(SignIn.this, "RentalHouse", null, 1);

        // when check box and rerun program put in shared Pre
       if ( SEmail.getText().toString().isEmpty() && SPassword.getText().toString().isEmpty())
        {
            SEmail.setText(sharedPrefManager.readString("Email", SEmail.getText().toString()));
            SPassword.setText( sharedPrefManager.readString("password", SPassword.getText().toString()));
            Toast.makeText(SignIn.this, "Load Data done", Toast.LENGTH_SHORT).show();
        }

        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = SEmail.getText().toString().trim();
                String password = SPassword.getText().toString().trim();

                if (dataBaseHelper.logInAuthentication(email, password,dataBaseHelper.isTenant(email)?1:0)) {
                    TransitionDrawable transitionDrawable = (TransitionDrawable)
                            imageLog.getDrawable();
                    transitionDrawable.startTransition(15000);
                    transitionDrawable.reverseTransition(15000);
                    transitionDrawable.startTransition(15000);
                    transitionDrawable.reverseTransition(15000);
                    if (remember.isChecked()) {
                        sharedPrefManager.writeString("Email", SEmail.getText().toString());
                        sharedPrefManager.writeString("password", SPassword.getText().toString());
                        Toast.makeText(SignIn.this, "Login saved", Toast.LENGTH_SHORT).show();
                    }
                    if (!dataBaseHelper.isTenant(email)) {
                        Intent intentHomeAgency = new Intent(SignIn.this, Home_nav.class);
                        startActivity(intentHomeAgency);
                        EmailAddressAgency=SEmail.getText().toString();
                        PasswordAgency=SPassword.getText().toString();
                        finish();
                    } else {
                        Intent intent = new Intent(SignIn.this,Rental_navbar.class);
                        SignIn.this.startActivity(intent);
                        EmailAddressTenant=SEmail.getText().toString();
                        PasswordTenant=SPassword.getText().toString();
                        finish();
                    }
                } else {
                    Toast.makeText(SignIn.this, "Please Check your Email and Password", Toast.LENGTH_LONG).show();
                }
            }
        });

        GoSearch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, Guest_Search.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });
    }
}
