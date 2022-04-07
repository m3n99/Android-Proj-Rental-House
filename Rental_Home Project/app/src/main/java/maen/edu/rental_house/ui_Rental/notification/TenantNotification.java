package maen.edu.rental_house.ui_Rental.notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.SignIn;
import maen.edu.rental_house.ui_Agency.edithouse.ViewEditHouse;


public class TenantNotification extends Fragment {
    ScrollView sv;
    String emailTenant = SignIn.EmailAddressTenant;
    public List<Reserve> reserveList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =inflater.inflate(R.layout.fragment_tenant_notification, container, false);
        sv=(ScrollView) view1.findViewById(R.id.sv);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);

        DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);


        reserveList=new ArrayList<Reserve>();

        reserveList = dataBaseHelper.getEmailTNotification(emailTenant);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(reserveList.toString());
        if (reserveList.size() ==0){
            Toast.makeText(getActivity(),"No Notification !!!", Toast.LENGTH_SHORT).show();
        }
        else {

            for( int i=0;i<reserveList.size();i++){
                if (reserveList.get(i).getApprove() == 1) {
                    LinearLayout section = new LinearLayout(getActivity());
                    section.setOrientation(LinearLayout.VERTICAL);
                    TextView houseId = new TextView(getActivity());
                    TextView msg = new TextView(getActivity());
                    houseId.setText(reserveList.get(i).toString());
                    msg.setText("Congratulations, the agency has approved to rent the house to you");
                    houseId.setLayoutParams(new LinearLayout.LayoutParams(2500, 200));
                    section.addView(houseId);
                    section.addView(msg);
                    display.addView(section);
                }

            }
        }
        sv.addView(display);

        return view1;

    }
}


