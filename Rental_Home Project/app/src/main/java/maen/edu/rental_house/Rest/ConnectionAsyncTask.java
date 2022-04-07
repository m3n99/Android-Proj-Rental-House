package maen.edu.rental_house.Rest;


import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.House;
import maen.edu.rental_house.MainActivity;

public class ConnectionAsyncTask extends AsyncTask <String, String, String> {
    Activity activity;

    public static ArrayList<House> house = new ArrayList<House>();

    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ((MainActivity) activity).setButtonText("connecting");
        ((MainActivity) activity).setProgress(true);
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ((MainActivity) activity).setProgress(false);
        ((MainActivity) activity).setButtonText("connected");
        List<House> house = HouseJasonParser.getObjectFromJason(s);
        ((MainActivity) activity).fillHouses(house);
    }

}
