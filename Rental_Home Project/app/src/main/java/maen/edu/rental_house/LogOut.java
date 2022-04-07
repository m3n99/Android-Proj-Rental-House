package maen.edu.rental_house;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import maen.edu.rental_house.SignIn;

public class LogOut extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            Intent intent =new Intent(getActivity(), SignIn.class);
            startActivity(intent);
        return  null;
    }
}