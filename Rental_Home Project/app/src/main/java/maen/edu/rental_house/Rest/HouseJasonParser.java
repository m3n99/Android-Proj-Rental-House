package maen.edu.rental_house.Rest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.House;

public class HouseJasonParser {
    public static List<House> getObjectFromJason(String jason) {
        List<House> Houses;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            Houses = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                House house = new House(); // object from class House
                house.setCity(jsonObject.getString("City"));
                house.setAddress(jsonObject.getString("Address"));
                house.setSpace(jsonObject.getDouble("SurfaceArea"));
                house.setRooms(jsonObject.getInt("NumberofRooms"));
                house.setBalcony(jsonObject.getString("Balcony"));
                house.setFurnished(jsonObject.getString("Furnished"));
                house.setPool(jsonObject.getString("Pool"));
                house.setGarden(jsonObject.getString("Garden"));
                house.setConstructingYear(jsonObject.getInt("ConstructingYear"));
                house.setPrice(jsonObject.getDouble("Price"));
                house.setType(jsonObject.getString("Type"));
                Houses.add(house);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return Houses;
    }


}
