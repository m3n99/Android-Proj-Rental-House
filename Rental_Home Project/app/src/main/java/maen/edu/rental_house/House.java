package maen.edu.rental_house;

import java.io.Serializable;
import java.util.ArrayList;

public class House implements Serializable {

    public static ArrayList<House> house = new ArrayList<House>();
    private String city;
    private String Address;
    private Double space;
    private int rooms;
    private String balcony;
    private String furnished;
    private String pool;
    private String garden;
    int ConstructingYear;
    private Double price;
    private String Type;
    private boolean Reserved;

    public House() {
    }

    public House(String city, String address, Double space, int rooms, String balcony,
                 String furnished, String pool, String garden, int constructingYear, Double price, String type, boolean Reserved) {
        this.city = city;
        Address = address;
        this.space = space;
        this.rooms = rooms;
        this.balcony = balcony;
        this.furnished = furnished;
        this.pool = pool;
        this.garden = garden;
        ConstructingYear = constructingYear;
        this.price = price;
        this.Reserved = Reserved;
        Type = type;
    }

    public static ArrayList<House> getHouse() {
        return house;
    }

    public static void setHouse(ArrayList<House> house) {
        House.house = house;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Double getSpace() {
        return space;
    }

    public void setSpace(Double space) {
        this.space = space;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public int getConstructingYear() {
        return ConstructingYear;
    }

    public void setConstructingYear(int constructingYear) {
        ConstructingYear = constructingYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean isReserved() {
        return Reserved;
    }

    public void setReserved(boolean reserved) {
        Reserved = reserved;
    }

    @Override
    public String toString() {
        return "House{" +
                "city='" + city + '\'' +
                ", Address='" + Address + '\'' +
                ", space=" + space +
                ", rooms=" + rooms +
                ", balcony='" + balcony + '\'' +
                ", furnished='" + furnished + '\'' +
                ", pool='" + pool + '\'' +
                ", garden='" + garden + '\'' +
                ", ConstructingYear=" + ConstructingYear +
                ", price=" + price +
                ", Type='" + Type + '\'' +
                '}';
    }
}
