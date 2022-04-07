package maen.edu.rental_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.Rest.ConnectionAsyncTask;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<House> house = new ArrayList<House>();

    Button connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgress(false);
        connect = (Button)findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
                connectionAsyncTask.execute("https://run.mocky.io/v3/5b1bc50f-7950-4627-a109-33410da750ee");
                Intent intent = new Intent(MainActivity.this,AfterConnection.class);
                MainActivity.this.startActivity(intent);
                finish();
            }

        });
    }

    public void fillHouses(List<House> Houses) {
        connect=(Button)findViewById(R.id.connect);
        if (Houses==null) {
            connect.setText("Retry Connection");
            connect.setTextColor(Color.RED);
            final Toast toast = Toast.makeText(MainActivity.this,"Unable To Connect to Server :(",Toast.LENGTH_LONG);
            toast.show();
        }else
        {
            house = (ArrayList<House>) Houses;

            final Toast toast = Toast.makeText(MainActivity.this," Connect to Server Successful:)",Toast.LENGTH_LONG);
            toast.show();
            connect.setText("Sign In");
            connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,AfterConnection.class);
                    MainActivity.this.startActivity(intent);
                    finish();
                }
            });
        }

    }

    // Progress par
    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    // if connection ok no error Async Task
    public void setButtonText(String text) {
        connect = (Button)findViewById(R.id.connect);
        connect.setText(text);
        connect.setTextColor(Color.WHITE);
    }

}