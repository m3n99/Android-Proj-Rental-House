package maen.edu.rental_house.ui_Agency.edithouse;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import maen.edu.rental_house.DataBaseHelper;
import maen.edu.rental_house.Post_Info;
import maen.edu.rental_house.R;
import maen.edu.rental_house.SignIn;

public class ViewEditHouse extends AppCompatActivity {
    Button EditButton;
    Button DeleteButton;
    EditText CityNameEditText;
    EditText postalAdressEditText;
    EditText AreaEditText;
    EditText ConstructionYearEditText;
    EditText BedroomEditText;
    EditText PriceEditText;
    String  StatusEditText;
    EditText AvailablitiyDateEditText;
    EditText DescriptionEditText;
    CheckBox Furnished;
    CheckBox UnFurinshed;
    byte[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_house);
        int i=Edit.index;
        System.out.println(Edit.editList.get(i).toString());
        EditButton=(Button) findViewById(R.id.editButton);
        DeleteButton=(Button) findViewById(R.id.DeleteBtn);
        DescriptionEditText=(EditText) findViewById(R.id.DescriptionEditTextViewAgency);
        AvailablitiyDateEditText= (EditText) findViewById(R.id.AvailablitiyDateEditTextViewAgency);
        PriceEditText=(EditText) findViewById(R.id.PriceEditTextViewAgency);
        BedroomEditText=(EditText) findViewById(R.id.BedroomEditTextViewAgency);
        ConstructionYearEditText=(EditText) findViewById(R.id.ConstructionYearEditTextViewAgency);
        AreaEditText=(EditText) findViewById(R.id.AreaEditTextViewAgency);
        postalAdressEditText=(EditText)findViewById(R.id.AdressEditTextViewAgency);
        CityNameEditText=(EditText) findViewById(R.id.CityNameEditTextViewAgency);
        Furnished=(CheckBox)findViewById(R.id.furnishedViewAgency);
        UnFurinshed=(CheckBox)findViewById(R.id.UnFurnishedViewAgency);

        CityNameEditText.setText(Edit.editList.get(i).getCity());
        postalAdressEditText.setText(String.valueOf(Edit.editList.get(i).getPostal()));
        AreaEditText.setText(String.valueOf(Edit.editList.get(i).getSurfaceArea()));
        ConstructionYearEditText.setText(String.valueOf(Edit.editList.get(i).getYear()));
        AvailablitiyDateEditText.setText(Edit.editList.get(i).getDate());
        BedroomEditText.setText(String.valueOf(Edit.editList.get(i).getNum_bedroom()));
        PriceEditText.setText(String.valueOf(Edit.editList.get(i).getRental_price()));
        Furnished.setChecked(Boolean.parseBoolean(String.valueOf(Edit.editList.get(i).getFurnished())));
        //UnFurinshed.setChecked(Edit.editList.get(i).isUnfurnished());
        DescriptionEditText.setText(Edit.editList.get(i).getDescription());
        System.out.println(Edit.editList.get(i).getID()+"*************************");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(ViewEditHouse.this, "RentalHouse", null, 1);

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.DeleteHouse(Edit.editList.get(i).getID());
                System.out.println(Edit.editList.get(i).getID());
                Toast.makeText(ViewEditHouse.this, "SUCCESS Delete", Toast.LENGTH_SHORT).show();
            }
        });
        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AvailablitiyDateEditText.getText().length()==0
                        ||CityNameEditText.getText().length()==0||PriceEditText.getText().length()==0
                        ||BedroomEditText.getText().length()==0||ConstructionYearEditText.getText().length()==0||AreaEditText.getText().length()==0||
                        postalAdressEditText.getText().length()==0) {
                    Toast.makeText(ViewEditHouse.this, "Make Sure To Fill All Fields ", Toast.LENGTH_SHORT).show();
                }else {

                    Post_Info updatehouse = new Post_Info();
                    int id = Edit.editList.get(i).getID();
                    System.out.println(id +"tttttttttttttttttttttttttttttttttttt");
                    updatehouse.setCity(CityNameEditText.getText().toString());
                    updatehouse.setDescription(DescriptionEditText.getText().toString());
                    updatehouse.setRental_price(Double.parseDouble(PriceEditText.getText().toString()));
                    updatehouse.setSurfaceArea(Double.parseDouble(AreaEditText.getText().toString()));
                    updatehouse.setDate(AvailablitiyDateEditText.getText().toString());
                    updatehouse.setPostal(Integer.parseInt(postalAdressEditText.getText().toString()));
                    updatehouse.setYear(Integer.parseInt(ConstructionYearEditText.getText().toString()));
                    updatehouse.setNum_bedroom(Integer.parseInt(BedroomEditText.getText().toString()));
                    updatehouse.setEmailAgency(SignIn.EmailAddressAgency);
                    if(Furnished.isChecked() && !UnFurinshed.isChecked()){
                        updatehouse.setFurnished(String.valueOf(Furnished.isChecked()));
                    }
                    if(UnFurinshed.isChecked() && !Furnished.isChecked()){
                        updatehouse.setUnfurnished(String.valueOf(UnFurinshed.isChecked()));
                    }
                    System.out.println("ououououououououuououououo");
                    dataBaseHelper.updatePost(updatehouse,id);

                    Toast.makeText(ViewEditHouse.this, "Success Update !!", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(ViewEditHouse.this,Edit.class);
                    //startActivity(intent);
                }
            }
        });

    }

}
