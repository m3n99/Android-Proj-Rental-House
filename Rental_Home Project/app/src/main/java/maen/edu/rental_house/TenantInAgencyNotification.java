package maen.edu.rental_house;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TenantInAgencyNotification extends AppCompatActivity {
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
    }


}
