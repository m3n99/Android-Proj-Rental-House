package maen.edu.rental_house.ui_Agency.edithouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.SignIn;


public class Edit extends Fragment {
    ScrollView sv;
    String emailAgency=null;
    public static List<Post_Info> editList;
    public static int index=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =inflater.inflate(R.layout.fragment_edit, container, false);
        sv=(ScrollView) view1.findViewById(R.id.sv);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);

        DataBaseHelper dataBaseHelper = new DataBaseHelper( getContext(), "RentalHouse", null, 1);
        emailAgency= SignIn.EmailAddressAgency;

        editList=new ArrayList<Post_Info>();

        editList=dataBaseHelper.getHouseForEdit(emailAgency);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(editList.toString());
        if (editList.size() ==0){
           Toast.makeText(getActivity(),"No Posted By This Agency !!!", Toast.LENGTH_SHORT).show();
        }
        else {
            for( int i=0;i<editList.size();i++){
                LinearLayout section = new LinearLayout(getActivity());
                section.setOrientation(LinearLayout.VERTICAL);
                Button btn= new Button(getActivity());
                btn.setLayoutParams(new LinearLayout.LayoutParams(250,160));
                //btn.setId(editList.get(i).getID());
                btn.setText("View "+i  );
                btn.setRight(0);
                TextView houseId=new TextView(getActivity());
                houseId.setText(editList.get(i).toString());
                houseId.setLayoutParams(new LinearLayout.LayoutParams(2500,200));
                section.addView(houseId);
                section.addView(btn);
                display.addView(section);

                btn.setOnClickListener(view2 -> {
                    System.out.println(btn.getText());
                    String btnName= (String) btn.getText();
                    String arr[]=btnName.split(" ");

                    index= Integer.parseInt(arr[1]);
                    Intent intent = new Intent(getActivity(),ViewEditHouse.class);
                    getActivity().startActivity(intent);

                });
            }
        }
        sv.addView(display);

        return view1;

    }
}