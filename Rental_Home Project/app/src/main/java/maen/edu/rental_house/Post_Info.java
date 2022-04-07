package maen.edu.rental_house;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class Post_Info implements Serializable {
    public static ArrayList<Post_Info> post_inf = new ArrayList<Post_Info>();
    Integer ID;
    String City;
    String description;
    Double rental_price;
    Double SurfaceArea;
    String date;
    int postal;
    int Year;
    int num_bedroom;
    String furnished;
    String unfurnished;
    String balcony;
    String garden;
    byte [] photo;
    boolean Reserved;
    String emailAgency;

    public Post_Info() {
    }

    public Post_Info(Integer ID, String city, String description, Double rental_price, Double surfaceArea, String date, int postal, int year, int num_bedroom, String furnished,
                     String unfurnished, String balcony, String garden, byte[] photo, boolean reserved, String emailAgency) {
        this.ID = ID;
        City = city;
        this.description = description;
        this.rental_price = rental_price;
        SurfaceArea = surfaceArea;
        this.date = date;
        this.postal = postal;
        Year = year;
        this.num_bedroom = num_bedroom;
        this.furnished = furnished;
        this.unfurnished = unfurnished;
        this.balcony = balcony;
        this.garden = garden;
        this.photo = photo;
        Reserved = reserved;
        this.emailAgency = emailAgency;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public static ArrayList<Post_Info> getPost_inf() {
        return post_inf;
    }

    public static void setPost_inf(ArrayList<Post_Info> post_inf) {
        Post_Info.post_inf = post_inf;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRental_price() {
        return rental_price;
    }

    public void setRental_price(Double rental_price) {
        this.rental_price = rental_price;
    }

    public Double getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        SurfaceArea = surfaceArea;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getNum_bedroom() {
        return num_bedroom;
    }

    public void setNum_bedroom(int num_bedroom) {
        this.num_bedroom = num_bedroom;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getUnfurnished() {
        return unfurnished;
    }

    public void setUnfurnished(String unfurnished) {
        this.unfurnished = unfurnished;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isReserved() {
        return Reserved;
    }

    public void setReserved(boolean reserved) {
        Reserved = reserved;
    }

    public String getEmailAgency() {
        return emailAgency;
    }

    public void setEmailAgency(String emailAgency) {
        this.emailAgency = emailAgency;
    }

    @Override
    public String toString() {
        return "Post_Info{" +
                "ID=" + ID +
                ", City='" + City + '\'' +
                ", description='" + description + '\'' +
                ", rental_price=" + rental_price +
                ", SurfaceArea=" + SurfaceArea +
                ", date='" + date + '\'' +
                ", postal=" + postal +
                ", Year=" + Year +
                ", num_bedroom=" + num_bedroom +
                ", furnished='" + furnished + '\'' +
                ", unfurnished='" + unfurnished + '\'' +
                ", balcony='" + balcony + '\'' +
                ", garden='" + garden + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", Reserved=" + Reserved +
                ", emailAgency='" + emailAgency + '\'' +
                '}';
    }
}
