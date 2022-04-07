package maen.edu.rental_house.ui_Rental.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Search {
    String city;
    int min_area;
    int max_area;
    int min_bedrooms;
    int max_bedrooms;
    int min_rentalPrice;
    int max_rentalPrice;
    boolean furnished;
    boolean unfurnished;
    boolean balcony;
    boolean no_balcony;
    boolean garden;
    boolean no_garden;

    public Search() {
    }

    public Search(String city, int min_area, int max_area, int min_bedrooms, int max_bedrooms, int min_rentalPrice, int max_rentalPrice,
                  boolean furnished, boolean unfurnished, boolean balcony, boolean no_balcony, boolean garden, boolean no_garden) {
        this.city = city;
        this.min_area = min_area;
        this.max_area = max_area;
        this.min_bedrooms = min_bedrooms;
        this.max_bedrooms = max_bedrooms;
        this.min_rentalPrice = min_rentalPrice;
        this.max_rentalPrice = max_rentalPrice;
        this.furnished = furnished;
        this.unfurnished = unfurnished;
        this.balcony = balcony;
        this.no_balcony = no_balcony;
        this.garden = garden;
        this.no_garden = no_garden;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMin_area() {
        return min_area;
    }

    public void setMin_area(int min_area) {
        this.min_area = min_area;
    }

    public int getMax_area() {
        return max_area;
    }

    public void setMax_area(int max_area) {
        this.max_area = max_area;
    }

    public int getMin_bedrooms() {
        return min_bedrooms;
    }

    public void setMin_bedrooms(int min_bedrooms) {
        this.min_bedrooms = min_bedrooms;
    }

    public int getMax_bedrooms() {
        return max_bedrooms;
    }

    public void setMax_bedrooms(int max_bedrooms) {
        this.max_bedrooms = max_bedrooms;
    }

    public int getMin_rentalPrice() {
        return min_rentalPrice;
    }

    public void setMin_rentalPrice(int min_rentalPrice) {
        this.min_rentalPrice = min_rentalPrice;
    }

    public int getMax_rentalPrice() {
        return max_rentalPrice;
    }

    public void setMax_rentalPrice(int max_rentalPrice) {
        this.max_rentalPrice = max_rentalPrice;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public boolean isUnfurnished() {
        return unfurnished;
    }

    public void setUnfurnished(boolean unfurnished) {
        this.unfurnished = unfurnished;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isNo_balcony() {
        return no_balcony;
    }

    public void setNo_balcony(boolean no_balcony) {
        this.no_balcony = no_balcony;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public boolean isNo_garden() {
        return no_garden;
    }

    public void setNo_garden(boolean no_garden) {
        this.no_garden = no_garden;
    }

    @Override
    public String toString() {
        return "Search{" +
                "city='" + city + '\'' +
                ", min_area=" + min_area +
                ", max_area=" + max_area +
                ", min_bedrooms=" + min_bedrooms +
                ", max_bedrooms=" + max_bedrooms +
                ", min_rentalPrice=" + min_rentalPrice +
                ", max_rentalPrice=" + max_rentalPrice +
                ", furnished=" + furnished +
                ", unfurnished=" + unfurnished +
                ", balcony=" + balcony +
                ", no_balcony=" + no_balcony +
                ", garden=" + garden +
                ", no_garden=" + no_garden +
                '}';
    }
}
