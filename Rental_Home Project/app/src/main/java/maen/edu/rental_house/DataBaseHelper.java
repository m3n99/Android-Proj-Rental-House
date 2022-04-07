package maen.edu.rental_house;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.List;

import maen.edu.rental_house.ui_Rental.Search.Search;

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TENANT(EMAIL TEXT PRIMARY KEY," +
                "FIRSTNAME TEXT,LASTNAME TEXT,GENDER TEXT,PASSWORD TEXT,NATIONALITY TEXT,GROSSSALARY DOUBLE,OCCUPATION TEXT," +
                "FAMILYSIZE INT,CURRENTRCOUNTRY TEXT,CITY TEXT, PHONE INT)");

        db.execSQL("CREATE TABLE AGENCY(EMAIL TEXT PRIMARY KEY," +
                "NAME TEXT,PASSWORD TEXT,COUNTRY TEXT,CITY TEXT, PHONE INT)");

        db.execSQL("CREATE TABLE POST(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,City TEXT,Description TEXT,Price DOUBLE,Area DOUBLE,Date TEXT,postal INTEGER,Year INTEGER," +
                "BedRooms INTEGER,Furnished TEXT,UFurnished TEXT,BALCONY TEXT, GARDEN TEXT, AgencyEmail TEXT,IMAGE BLOB)");

        db.execSQL( "CREATE TABLE LOGIN(EMAIL1 TEXT DEFAULT NULL ,EMAIL2 TEXT DEFAULT NULL, ISTENANT INTGER DEFAULT 0 ," +
                    "PRIMARY KEY (EMAIL1,EMAIL2)," +
                    "FOREIGN KEY(EMAIL1) REFERENCES AGENCY(EMAIL) ON DELETE CASCADE," +
                    "FOREIGN KEY(EMAIL2) REFERENCES TENANT(EMAIL) ON DELETE CASCADE)");

        db.execSQL("CREATE TABLE SEARCH(CITY TEXT  ,MIN_AREA INTGER,MAX_AREA INTGER,MIN_BED INTGER, MAX_BED INTGER, " +
                "MIN_PRICE INTGER, MAX_PRICE INTGER, FURNISHED BOOLEAN,UNFURNISHED BOOLEAN,BALCONY BOOLEAN," +
                "NOBALCONY BOOLEAN,GARDEN BOOLEAN,NOGARDEN BOOLEAN )");

        db.execSQL ("CREATE TABLE RESERVE (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CITY TEXT,DATE TEXT,POSTAL INTEGER DEFAULT 09990,AgencyName TEXT DEFAULT NULL," +
                "First_Name DEFAULT NULL, Last_Name DEFAULT NULL,ADDRESS TEXT,Approve INTEGER,EMAILAGENCY TEXT, EMAILTENANT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    public Cursor getAllTenant() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TENANT ", null);
    }

    public Cursor getAllAgency() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM AGENCY ", null);
    }
    public Cursor getAllPOST() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM POST ", null);
    }

    public boolean insertReserve (Reserve reserve){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",reserve.getID());
        contentValues.put("CITY", reserve.getCity());
        contentValues.put("POSTAL", reserve.getPostal());
        contentValues.put("DATE", reserve.getDate());
        contentValues.put("AgencyName",reserve.getAgencyName());
        contentValues.put("First_Name",reserve.getFirstName());
        contentValues.put("Last_Name",reserve.getLastName());
        contentValues.put("ADDRESS", reserve.getAddress());
        contentValues.put("EMAILAGENCY",reserve.getEmailAgency());
        contentValues.put("EMAILTENANT",reserve.getEmailTenant());
        contentValues.put("Approve",reserve.getApprove());
        try {
            sqLiteDatabase.insert("RESERVE", null, contentValues);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTenant(Tenant tenant) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", tenant.getEmail());
        contentValues.put("FIRSTNAME", tenant.getFName());
        contentValues.put("LASTNAME", tenant.getLName());
        contentValues.put("GENDER", tenant.getGender());
        contentValues.put("PASSWORD", tenant.getPassword());
        contentValues.put("NATIONALITY", tenant.getNationality());
        contentValues.put("GROSSSALARY", tenant.getGrossMSalary());
        contentValues.put("OCCUPATION", tenant.getOccupation());
        contentValues.put("FAMILYSIZE", tenant.getFamilySize());
        contentValues.put("CURRENTRCOUNTRY", tenant.getCurrentRCountry());
        contentValues.put("CITY", tenant.getCity());
        contentValues.put("PHONE", tenant.getPhone());
        try {
            sqLiteDatabase.insert("Tenant", null, contentValues);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//-------------------------------------------------------------
public List<Reserve> getEmailNotification(String emailAgency) {
    SQLiteDatabase db = this.getReadableDatabase();
    List<Reserve> list=new ArrayList<Reserve>();
    Cursor cursor = db.rawQuery("SELECT * FROM RESERVE WHERE EMAILAGENCY LIKE ?", new String[]{emailAgency},null);
    if (cursor.moveToFirst()) {
        do {
            Reserve reservehouse = new Reserve();
            reservehouse.setCity(cursor.getString(cursor.getColumnIndexOrThrow("CITY")));
            reservehouse.setDate(cursor.getString(cursor.getColumnIndexOrThrow("DATE")));
            reservehouse.setPostal(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("POSTAL"))));
            reservehouse.setAgencyName(cursor.getString(cursor.getColumnIndexOrThrow("AgencyName")));
            reservehouse.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow("First_Name")));
            reservehouse.setLastName(cursor.getString(cursor.getColumnIndexOrThrow("Last_Name")));
            reservehouse.setEmailAgency(cursor.getString(cursor.getColumnIndexOrThrow("EMAILAGENCY")));
            reservehouse.setEmailTenant(cursor.getString(cursor.getColumnIndexOrThrow("EMAILTENANT")));
            int id = Integer.parseInt(cursor.getString(0));
            reservehouse.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("ID"))));
            list.add(reservehouse);
        } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    System.out.println(list.toString());
    return list;
}
//-------------------------------------------------------------
public List<Reserve> getEmailTNotification(String emailTenant) {
    SQLiteDatabase db = this.getReadableDatabase();
    List<Reserve> list=new ArrayList<Reserve>();
    Cursor cursor = db.rawQuery("SELECT * FROM RESERVE WHERE EMAILAGENCY LIKE ?", new String[]{emailTenant},null);
    if (cursor.moveToFirst()) {
        do {
            Reserve reservehouse = new Reserve();
            reservehouse.setCity(cursor.getString(cursor.getColumnIndexOrThrow("CITY")));
            reservehouse.setDate(cursor.getString(cursor.getColumnIndexOrThrow("DATE")));
            reservehouse.setPostal(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("POSTAL"))));
            reservehouse.setAgencyName(cursor.getString(cursor.getColumnIndexOrThrow("AgencyName")));
            reservehouse.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow("First_Name")));
            reservehouse.setLastName(cursor.getString(cursor.getColumnIndexOrThrow("Last_Name")));
            reservehouse.setEmailAgency(cursor.getString(cursor.getColumnIndexOrThrow("EMAILAGENCY")));
            reservehouse.setEmailTenant(cursor.getString(cursor.getColumnIndexOrThrow("EMAILTENANT")));
            reservehouse.setApprove(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Approve"))));
            int id = Integer.parseInt(cursor.getString(0));
            reservehouse.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("ID"))));
            list.add(reservehouse);
        } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    System.out.println(list.toString());
    return list;
}
//-------------------------------------------------------------

    public boolean insertAgency(RentingAgency agency) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", agency.getEmail());
        contentValues.put("NAME", agency.getAName());
        contentValues.put("PASSWORD", agency.getPassword());
        contentValues.put("COUNTRY", agency.getCountry());
        contentValues.put("CITY", agency.getCity());
        contentValues.put("PHONE", agency.getmPhone());
        try {
            sqLiteDatabase.insert("Agency", null, contentValues);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void updateAgency(RentingAgency agency) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", agency.getEmail());
        contentValues.put("NAME", agency.getAName());
        contentValues.put("PASSWORD", agency.getPassword());
        contentValues.put("COUNTRY", agency.getCountry());
        contentValues.put("CITY", agency.getCity());
        contentValues.put("PHONE", agency.getmPhone());
        sqLiteDatabase.update("Agency", contentValues,"EMAIL ="+"\""+ agency.getEmail()+"\"",null);
    }

    public void updateTenant(Tenant tenant) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", tenant.getEmail());
        contentValues.put("FIRSTNAME", tenant.getFName());
        contentValues.put("LASTNAME", tenant.getLName());
        contentValues.put("GENDER", tenant.getGender());
        contentValues.put("PASSWORD", tenant.getPassword());
        contentValues.put("NATIONALITY", tenant.getNationality());
        contentValues.put("GROSSSALARY", tenant.getGrossMSalary());
        contentValues.put("OCCUPATION", tenant.getOccupation());
        contentValues.put("FAMILYSIZE", tenant.getFamilySize());
        contentValues.put("CURRENTRCOUNTRY", tenant.getCurrentRCountry());
        contentValues.put("CITY", tenant.getCity());
        contentValues.put("PHONE", tenant.getPhone());
        sqLiteDatabase.update("Tenant", contentValues,"EMAIL ="+"\""+ tenant.getEmail()+"\"",null);
    }


    public boolean insertPOST(Post_Info post) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",post.getID());
        contentValues.put("City", post.getCity());
        contentValues.put("Description", post.getDescription());
        contentValues.put("Price", post.getRental_price());
        contentValues.put("Area", post.getSurfaceArea());
        contentValues.put("Date", post.getDate());
        contentValues.put("postal", post.getPostal());
        contentValues.put("Year", post.getYear());
        contentValues.put("BedRooms", post.getNum_bedroom());
        contentValues.put("Furnished", post.getFurnished());
        contentValues.put("UFurnished", post.getUnfurnished());
        contentValues.put("BALCONY",post.getBalcony());
        contentValues.put("GARDEN",post.getGarden());
        contentValues.put("AgencyEmail", post.getEmailAgency());
        contentValues.put("IMAGE",post.getPhoto());
        try {
            sqLiteDatabase.insert("POST", null, contentValues);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//--------------------------------------------------------------------
public void updatePost(Post_Info post, int id) {
    SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    //contentValues.put("ID", post.id);
    System.out.println("ououououououououuououououo********************");
    System.out.println(post.getID());
    System.out.println(id);
    System.out.println(post.getCity());
    System.out.println(post.getEmailAgency());
    contentValues.put("City", post.getCity());
    contentValues.put("Description", post.getDescription());
    contentValues.put("Price", post.getRental_price());
    contentValues.put("Area", post.getSurfaceArea());
    contentValues.put("Date", post.getDate());
    contentValues.put("postal", post.getPostal());
    contentValues.put("Year", post.getYear());
    contentValues.put("BedRooms", post.getNum_bedroom());
    contentValues.put("Furnished", post.getFurnished());
    contentValues.put("UFurnished", post.getUnfurnished());
    // contentValues.put("UFurnished", post.isUnfurnished());
    contentValues.put("AgencyEmail",post.getEmailAgency());
    sqLiteDatabase.update("POST", contentValues,"ID = " + id,null);
}
//--------------------------------------------------------------------
    public boolean insertSEARCH(Search search) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", search.getCity());
        contentValues.put("MIN_AREA",search.getMin_area());
        contentValues.put("MAX_AREA",search.getMax_area());
        contentValues.put("MIN_BED",search.getMin_bedrooms());
        contentValues.put("MAX_BED",search.getMax_bedrooms());
        contentValues.put("MIN_PRICE",search.getMin_rentalPrice());
        contentValues.put("MAX_PRICE",search.getMax_rentalPrice());
        contentValues.put("FURNISHED", search.isFurnished());
        contentValues.put("UNFURNISHED", search.isUnfurnished());
        contentValues.put("BALCONY", search.isBalcony());
        contentValues.put("NOBALCONY", search.isNo_balcony());
        contentValues.put("GARDEN", search.isGarden());
        contentValues.put("NOGARDEN", search.isNo_garden());
        try {
            sqLiteDatabase.insert("SEARCH", null, contentValues);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//---------------------------------------------------------------
public List<Post_Info> getHouseForEdit(String emailAgency){
    SQLiteDatabase db=this.getReadableDatabase();
    ArrayList<Post_Info> list = new ArrayList<Post_Info>();
    Cursor cursor = db.rawQuery("SELECT * FROM POST WHERE AgencyEmail LIKE ?", new String[]{emailAgency},null);
    if(cursor.getCount() != 0){
        if (cursor.moveToFirst()) {

            do {
                Post_Info house = new Post_Info();
                house.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("ID"))));
                house.setCity(cursor.getString(cursor.getColumnIndexOrThrow("City")));
                house.setDate(cursor.getString(cursor.getColumnIndexOrThrow("Date")));
                house.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("Description")));
                house.setPostal(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("postal"))));
                house.setNum_bedroom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("BedRooms"))));
                house.setRental_price(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Price"))));
                house.setSurfaceArea(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Area"))));
                //house.setUnfurnished (cursor.getInt(9) == 1 ? true : false);
                house.setFurnished(cursor.getString(cursor.getColumnIndexOrThrow("Furnished")));
                house.setYear(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Year"))));
                house.setEmailAgency(cursor.getString(cursor.getColumnIndexOrThrow("AgencyEmail")));
                list.add(house);
            }
            while (cursor.moveToNext());
        }}

    cursor.close();
    db.close();
    return list;
}



//-----------------------------------------------------------------
public void DeleteHouse(int id){
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    System.out.println("uuuuuuuuuuuuuuuyyyyyyyyyyyyyyuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
    System.out.println(id);
    sqLiteDatabase.execSQL("DELETE FROM POST Where ID= " +id);

}
//----------------------------------------------------------------
public void DeleteReservedHouse(int id){
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
    System.out.println(id);
    sqLiteDatabase.execSQL("DELETE FROM RESERVE Where ID= " +id);

}
//-----------------------------------------------------------------


    public boolean insertLOGIN(String email , int log) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(log == 0){ // 0 Agency 1 Tenant
            contentValues.put("EMAIL1", email);
        }
        else{
            contentValues.put("EMAIL2", email);
        }
        contentValues.put("ISTENANT", log);
        try {
            sqLiteDatabase.insert("LOGIN", null, contentValues);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Cursor findTenant(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TENANT WHERE EMAIL LIKE ?", new String[]{email});
    }
    public Cursor findAgency(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM AGENCY WHERE EMAIL LIKE ?", new String[]{email});
    }
    public Cursor findUSER(String email, int log) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        if(log == 0){
            return sqLiteDatabase.rawQuery("SELECT * FROM AGENCY WHERE EMAIL LIKE ?", new String[]{email});
        }else{
            return sqLiteDatabase.rawQuery("SELECT * FROM TENANT WHERE EMAIL LIKE ?", new String[]{email});
        }
    }

    public boolean Delete(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery( "DELETE  FROM POST WHERE (timeStamp /1000) <= " +
                        "strftime('%s', datetime('now', '-90 day'))", null);
        cursor.close();
        return false;
    }

    public boolean isReserved(String City,String Name) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RESERVE WHERE CITY LIKE ? AND AgencyName LIKE ?",
                new String[]{City, Name});
        if (cursor.getCount() !=0){
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isReserved_House(String City, String Address) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RESERVE WHERE CITY LIKE ? AND ADDRESS LIKE ?",
                new String[]{City, Address});
        if (cursor.getCount() !=0){
            return true;
        }
        cursor.close();
        return false;
    }
    public boolean isReserved_Agency(String name) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RESERVE WHERE AgencyName LIKE ?", new String[]{name});
        System.out.println("Form DB "+ name);
        if(cursor.getCount() !=0){
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isTenant(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOGIN WHERE EMAIL2 LIKE ?", new String[]{email});
       if (cursor.getCount() != 0) {
            int indexIsAdmin = cursor.getColumnIndex("ISTENANT");
            cursor.moveToFirst();
            return cursor.getString(indexIsAdmin).compareTo("1") == 0;
        }
        return false;
    }



    public Tenant getTenant(String email) {
        Cursor cursorUser = findTenant(email);
        if (cursorUser.getCount() != 0) {
            cursorUser.moveToFirst();
            Tenant tenant = new Tenant();
            tenant.setFName(cursorUser.getString(cursorUser.getColumnIndexOrThrow("FIRSTNAME")));
            tenant.setLName(cursorUser.getString(cursorUser.getColumnIndexOrThrow("LASTNAME")));
            tenant.setGender(cursorUser.getString(cursorUser.getColumnIndexOrThrow("GENDER")));
            tenant.setPassword(cursorUser.getString(cursorUser.getColumnIndexOrThrow("PASSWORD")));
            tenant.setNationality(cursorUser.getString(cursorUser.getColumnIndexOrThrow("NATIONALITY")));
            tenant.setGrossMSalary(Double.parseDouble(cursorUser.getString(cursorUser.getColumnIndexOrThrow("GROSSSALARY"))));
            tenant.setOccupation(cursorUser.getString(cursorUser.getColumnIndexOrThrow("OCCUPATION")));
            tenant.setFamilySize(Integer.parseInt(cursorUser.getString(cursorUser.getColumnIndexOrThrow("FAMILYSIZE"))));
            tenant.setCurrentRCountry(cursorUser.getString(cursorUser.getColumnIndexOrThrow("CURRENTRCOUNTRY")));
            tenant.setCity(cursorUser.getString(cursorUser.getColumnIndexOrThrow("CITY")));
            tenant.setPhone(Integer.parseInt(cursorUser.getString(cursorUser.getColumnIndexOrThrow("PHONE"))));
            cursorUser.close();
            return tenant;
        }

        return null;
    }
    public List<Post_Info> gethouse (){
        String quaryString="SELECT * FROM POST";
        List<Post_Info> list=new ArrayList<Post_Info>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quaryString,null);
        if(cursor.getCount() != 0){
            if (cursor.moveToFirst()) {
                do {
                    Post_Info house = new Post_Info();
                    house.setCity(cursor.getString(cursor.getColumnIndexOrThrow("City")));
                    house.setDate(cursor.getString(cursor.getColumnIndexOrThrow("Date")));
                    house.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("Description")));
                    house.setPostal(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("postal"))));
                    house.setNum_bedroom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("BedRooms"))));
                    house.setRental_price(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Price"))));
                    house.setSurfaceArea(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Area"))));
                    house.setFurnished(cursor.getString(cursor.getColumnIndexOrThrow("Furnished")));
                    house.setUnfurnished(cursor.getString(cursor.getColumnIndexOrThrow("UFurnished")));
                    house.setBalcony(cursor.getString(cursor.getColumnIndexOrThrow("BALCONY")));
                    house.setGarden(cursor.getString(cursor.getColumnIndexOrThrow("GARDEN")));
                    house.setYear(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Year"))));
                    house.setEmailAgency(cursor.getString(cursor.getColumnIndexOrThrow("AgencyEmail")));
                    house.setPhoto(cursor.getBlob(cursor.getColumnIndexOrThrow("IMAGE")));
                    list.add(house);
                }
                while (cursor.moveToNext());
            }}
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList <Reserve> getHistory(){
        String quaryString="SELECT * FROM RESERVE";
        List<Reserve> list=new ArrayList<Reserve>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quaryString,null);
        if(cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Reserve reserve = new Reserve();
                    reserve.setCity(cursor.getString(cursor.getColumnIndexOrThrow("CITY")));
                    reserve.setPostal(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("POSTAL"))));
                    reserve.setDate(cursor.getString(cursor.getColumnIndexOrThrow("DATE")));
                    reserve.setAgencyName(cursor.getString(cursor.getColumnIndexOrThrow("AgencyName")));
                    reserve.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow("First_Name")));
                    reserve.setLastName(cursor.getString(cursor.getColumnIndexOrThrow("Last_Name")));
                    reserve.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS")));
                    reserve.setEmailAgency(cursor.getString(cursor.getColumnIndexOrThrow("EMAILAGENCY")));
                    reserve.setEmailTenant(cursor.getString(cursor.getColumnIndexOrThrow("EMAILTENANT")));
                    list.add(reserve);
                }
                while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return (ArrayList<Reserve>) list;
    }
    public ArrayList<Search> getSearch(){
        String quaryString="SELECT * FROM SEARCH";
        List<Search> list=new ArrayList<Search>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quaryString,null);
        if(cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Search search = new Search();
                    search.setCity(cursor.getString(cursor.getColumnIndexOrThrow("CITY")));
                    search.setMin_area(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("MIN_AREA"))));
                    search.setMax_area(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("MAX_AREA"))));
                    search.setMin_bedrooms(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("MIN_BED"))));
                    search.setMax_bedrooms(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("MAX_BED"))));
                    search.setMin_rentalPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("MIN_PRICE"))));
                    search.setMax_rentalPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("MAX_PRICE"))));
                    search.setFurnished(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("FURNISHED"))));
                    search.setUnfurnished(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("UNFURNISHED"))));
                    search.setBalcony(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("BALCONY"))));
                    search.setNo_balcony(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("NOBALCONY"))));
                    search.setGarden(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("GARDEN"))));
                    search.setNo_garden(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndexOrThrow("NOGARDEN"))));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return (ArrayList<Search>) list;
    }

    public ArrayList<Post_Info> Search( String city,int min_area, int max_area, int min_bed, int max_bed,String frushied,String gar,
            String bal,int min_price, int max_price){
        SQLiteDatabase db = getReadableDatabase();
        List<Post_Info> list=new ArrayList<Post_Info>();
        String query ="SELECT * FROM POST WHERE City LIKE \"" + city +"\"  OR Area BETWEEN "+ min_area  +
                " AND "+ max_area +" OR BedRooms BETWEEN "+ min_bed +" AND "+ max_bed +
                " AND Furnished LIKE \""+ frushied + "\"  AND GARDEN LIKE  \""+ gar +" \" AND BALCONY LIKE \"" + bal + "\" AND Price BETWEEN "+ min_price +" AND "+ max_price ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    Post_Info house = new Post_Info();
                    house.setCity(cursor.getString(cursor.getColumnIndexOrThrow("City")));
                    house.setDate(cursor.getString(cursor.getColumnIndexOrThrow("Date")));
                    house.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("Description")));
                    house.setPostal(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("postal"))));
                    house.setNum_bedroom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("BedRooms"))));
                    house.setRental_price(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Price"))));
                    house.setSurfaceArea(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Area"))));
                    house.setFurnished(cursor.getString(cursor.getColumnIndexOrThrow("Furnished")));
                    house.setUnfurnished(cursor.getString(cursor.getColumnIndexOrThrow("UFurnished")));
                    house.setBalcony(cursor.getString(cursor.getColumnIndexOrThrow("BALCONY")));
                    house.setGarden(cursor.getString(cursor.getColumnIndexOrThrow("GARDEN")));
                    house.setYear(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Year"))));
                    house.setEmailAgency(cursor.getString(cursor.getColumnIndexOrThrow("AgencyEmail")));
                    house.setPhoto(cursor.getBlob(cursor.getColumnIndexOrThrow("IMAGE")));
                    list.add(house);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return (ArrayList<Post_Info>) list;
    }

    public RentingAgency getAgency(String email) {
        Cursor cursorUser = findAgency(email);
        if (cursorUser.getCount() != 0) {
            cursorUser.moveToFirst();
            RentingAgency agency = new RentingAgency();
            agency.setAName(cursorUser.getString(cursorUser.getColumnIndexOrThrow("NAME")));
            agency.setPassword(cursorUser.getString(cursorUser.getColumnIndexOrThrow("PASSWORD")));
            agency.setCountry(cursorUser.getString(cursorUser.getColumnIndexOrThrow("COUNTRY")));
            agency.setCity(cursorUser.getString(cursorUser.getColumnIndexOrThrow("CITY")));
            agency.setmPhone(Integer.parseInt(cursorUser.getString(cursorUser.getColumnIndexOrThrow("PHONE"))));
            cursorUser.close();
            return agency;
        }
        return null;
    }



    public boolean logInAuthentication(String email, String password, int log) {
        Cursor cursorUser = findUSER(email,log);
        System.out.println(cursorUser.getCount());
        if (cursorUser.getCount() != 0) {
            cursorUser.moveToFirst();
            return HashPassword.hashPassword(password).compareTo(cursorUser.getString(cursorUser.getColumnIndexOrThrow("PASSWORD")))== 0;
        }
        return false;
    }
//---------------------------------------------------
public void updateApprov(int Hid , int appro) {
        System.out.println(Hid);
        System.out.println(appro);
        String quaryString = " update Reserve set Approve = " + appro + " where ID = "+Hid ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quaryString, null);
        System.out.println(cursor.getCount());
}
//---------------------------------------------------
}
