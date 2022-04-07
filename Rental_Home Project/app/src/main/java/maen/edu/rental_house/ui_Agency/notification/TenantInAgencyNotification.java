package maen.edu.rental_house.ui_Agency.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.Tenant;

public class TenantInAgencyNotification extends AppCompatActivity {
    int index = maen.edu.rental_house.ui_Agency.notification.notificationAgency.NotificationAgencyIndex;
    List<Reserve> notificationAgencyhouse = notificationAgency.notificationAgencyhouse;
    Button backTenant;
    TextView email;
    TextView firstName;
    TextView lastName;
    TextView nationality;
    TextView gender;
    TextView country;
    TextView city;
    TextView phoneNumber;
    TextView monthlySalary;
    TextView occupation;
    TextView familySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_in_agency_notification);
        backTenant =(Button) findViewById(R.id.editTenantNotifiactionAgency );
        email =(TextView) findViewById(R.id.EmailTenantNotifiactionAgency);
        firstName=(TextView) findViewById(R.id.firstNameProflieNotifiactionAgency);
        lastName=(TextView) findViewById(R.id.secondNameProfileNotifiactionAgency);
        nationality=(TextView)findViewById(R.id.NationalityProfileNotifiactionAgency);
        gender=(TextView) findViewById(R.id.GenderTenantProfileNotifiactionAgency);
        country=(TextView) findViewById(R.id.tenantCountryProfileNotifiactionAgency);
        city=(TextView) findViewById(R.id.TenantCityProfileNotifiactionAgency);
        phoneNumber=(TextView) findViewById(R.id.TenantPhoneNumberProfileNotifiactionAgency);
        familySize=(TextView) findViewById(R.id.familySizeTenantProfileNotifiactionAgency);
        monthlySalary=(TextView) findViewById(R.id.monthlySalaryTenantProfileNotifiactionAgency);
        occupation=(TextView) findViewById(R.id.ocupationTenantProfileNotifiactionAgency);
        System.out.println(notificationAgencyhouse.get(index).getEmailTenant());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(TenantInAgencyNotification.this, "RentalHouse", null, 1);
        Tenant search = dataBaseHelper.getTenant(notificationAgencyhouse.get(index).getEmailTenant());

        email.setText(notificationAgencyhouse.get(index).getEmailTenant());
        firstName.setText(search.getFName());
        lastName.setText(search.getLName());
        nationality.setText(search.getNationality());
        country.setText(search.getCurrentRCountry());
        city.setText(search.getCity());
        occupation.setText(search.getOccupation());
        gender.setText(search.getGender());
        phoneNumber.setText(""+search.getPhone());
        familySize.setText(""+search.getFamilySize());
        monthlySalary.setText(""+search.getGrossMSalary());
        backTenant.setOnClickListener((new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TenantInAgencyNotification.this, notificationDetails.class);
                startActivity(intent);
                finish();
            }
        }));

    }


}
