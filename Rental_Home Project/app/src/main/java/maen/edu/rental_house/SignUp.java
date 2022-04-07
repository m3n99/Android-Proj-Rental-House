package maen.edu.rental_house;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    Button Tenentsignin;
    Button Agency;
    Button Back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Tenentsignin =(Button)findViewById(R.id.Tenant) ;
        Tenentsignin.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view){
                Intent intent = new Intent(SignUp.this, TenantSignup.class);
                SignUp.this.startActivity(intent);
                finish();
            }
        });

        Agency =(Button)findViewById(R.id.Renting) ;
        Agency.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view){
                Intent intent = new Intent(SignUp.this, AgencySignUP.class);
                SignUp.this.startActivity(intent);
                finish();
            }
        });
        Back =(Button)findViewById(R.id.BackSA) ;
        Back.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view){
                Intent intent = new Intent(SignUp.this, AfterConnection.class);
                SignUp.this.startActivity(intent);
                finish();
            }
        });

    }

}
