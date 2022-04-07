package maen.edu.rental_house;


import android.content.SharedPreferences;
import android.content.Context;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "My Shared Preference";
    private static final int SHARED_PREF_PRIVATE = Context.MODE_PRIVATE;

    private static SharedPrefManager ourInstance = null;
    private static SharedPreferences sharedPreferences = null;
    private  SharedPreferences.Editor editor = null;

    static SharedPrefManager getInstance(Context context){
        if(ourInstance!=null)
            return ourInstance;
        ourInstance = new SharedPrefManager(context);
        return ourInstance;
    }
    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                SHARED_PREF_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public boolean writeString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }


    public String readString(String key, String defaultValue) {
        try{
            sharedPreferences.getString(key,defaultValue);
            return sharedPreferences.getString(key, defaultValue);
        }catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }


}
