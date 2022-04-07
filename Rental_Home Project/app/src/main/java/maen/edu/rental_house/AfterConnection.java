package maen.edu.rental_house;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AfterConnection extends AppCompatActivity {
    Button Sign_in;
    Button Sign_up;
    Button SearchG;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterconnection);
        Sign_in = (Button)findViewById(R.id.SIGNIN);
        Sign_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AfterConnection.this,SignIn.class);
                AfterConnection.this.startActivity(intent);
                finish();
            }
        });
        Sign_up = (Button)findViewById(R.id.SIGNUP);
        Sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AfterConnection.this,SignUp.class);
                AfterConnection.this.startActivity(intent);
                finish();
            }
        });
        SearchG = (Button)findViewById(R.id.SEARCH);
        SearchG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AfterConnection.this,Guest_Search.class);
                AfterConnection.this.startActivity(intent);
                finish();
            }
        });
    }
}
