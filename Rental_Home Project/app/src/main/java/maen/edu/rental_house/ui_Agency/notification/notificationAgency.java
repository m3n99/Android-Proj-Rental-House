package maen.edu.rental_house.ui_Agency.notification;

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

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.SignIn;


public class notificationAgency extends Fragment {


    public static List<Reserve> notificationAgencyhouse =new ArrayList<Reserve>();
    ScrollView sv;
    public static int NotificationAgencyIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification_agency, container, false);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "RentalHouse", null, 1);

       // NotificationAgencyClassDB notification=new NotificationAgencyClassDB(getActivity());
        String emailAgency= SignIn.EmailAddressAgency;;

        notificationAgencyhouse = dataBaseHelper.getEmailNotification(emailAgency);
        System.out.println(notificationAgencyhouse.toString());

        sv=(ScrollView) view.findViewById(R.id.svNotificationTenant);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);
        //RentingAgencyDB db=new RentingAgencyDB(getActivity());
        System.out.println(notificationAgencyhouse.toString());

        for( int i=0;i<notificationAgencyhouse.size();i++){
            LinearLayout section = new LinearLayout(getActivity());
            section.setOrientation(LinearLayout.VERTICAL);
            Button btn= new Button(getActivity());
            btn.setLayoutParams(new LinearLayout.LayoutParams(300,180));
            btn.setId(notificationAgencyhouse.get(i).getID());
            btn.setText("View Details ");
            btn.setRight(0);
            TextView houseId=new TextView(getActivity());
            houseId.setText(notificationAgencyhouse.get(i).toString());
            houseId.setLayoutParams(new LinearLayout.LayoutParams(1000,1000));
            section.addView(houseId);
            section.addView(btn);
            display.addView(section);

            btn.setOnClickListener(view2 -> {
                System.out.println(btn.getText());
                String btnName= (String) btn.getText();
                String arr[]=btnName.split(" ");
                NotificationAgencyIndex= Integer.parseInt(arr[1]);
                Intent intent = new Intent(getActivity(),notificationDetails.class);
                getActivity().startActivity(intent);
                //id=editList.get(i).getID();
            });
        }

        sv.addView(display);



        return view;
    }
}