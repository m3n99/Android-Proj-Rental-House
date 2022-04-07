package maen.edu.rental_house.ui_Agency.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;

public class notificationDetails extends AppCompatActivity {



    int index = maen.edu.rental_house.ui_Agency.notification.notificationAgency.NotificationAgencyIndex;
    List<Reserve> notificationAgencyhouse = notificationAgency.notificationAgencyhouse;
    TextView homeDetails;
    Button profile,history,approve,reject;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(notificationDetails.this, "RentalHouse", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        profile = (Button) findViewById(R.id.TenantProfileNotification);
        history = (Button) findViewById(R.id.HistoryTenantNotification);
        approve = (Button) findViewById(R.id.Approve);
        reject = (Button) findViewById(R.id.Reject);
        homeDetails = (TextView) findViewById(R.id.HomeDetails);

        System.out.println("__________________________________________________________________________");
//        System.out.println(notificationAgency.toString());
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notificationDetails.this, TenantInAgencyNotification.class);
                startActivity(intent);
                finish();
            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(notificationDetails.this, "Ok This Action Will Send To Tenant ", Toast.LENGTH_SHORT).show();
                notificationAgencyhouse.get(index).setApprove(1);
                System.out.println(notificationAgencyhouse.toString());
                dataBaseHelper.updateApprov(notificationAgencyhouse.get(index).getID(),1);
                //System.out.println("****************house id"+notificationAgency.get(index).getHouseID());
                //db.Delete(notificationAgency.get(index).getHouseID());
                Intent intent=new Intent(notificationDetails.this,notificationDetails.class);
                startActivity(intent);
                finish();
            }
        });

       /* history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate((R.id.nav_history));
            }
        });*/
    }
}