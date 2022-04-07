package maen.edu.rental_house;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Tenant {
    private String Email;
    private String FName;
    private String LName;
    private String Gender;
    private String Password;
    private String CPassword;
    private String Nationality;
    private Double GrossMSalary;
    private String Occupation;
    private  int FamilySize;
    private String CurrentRCountry;
    private String City;
    private int Phone;


    public Tenant() {
    }

    public Tenant(String email, String FName, String LName, String gender, String password, String CPassword, String nationality,
                  Double grossMSalary,String occupation, int familySize, String currentRCountry, String city, int phone) {
        Email = email;
        this.FName = FName;
        this.LName = LName;
        Gender = gender;
        Password = password;
        this.CPassword = CPassword;
        Nationality = nationality;
        GrossMSalary = grossMSalary;
        Occupation = occupation;
        FamilySize = familySize;
        CurrentRCountry = currentRCountry;
        City = city;
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCPassword() {
        return CPassword;
    }

    public void setCPassword(String CPassword) {
        this.CPassword = CPassword;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public Double getGrossMSalary() {
        return GrossMSalary;
    }

    public void setGrossMSalary(Double grossMSalary) {
        GrossMSalary = grossMSalary;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public int getFamilySize() {
        return FamilySize;
    }

    public void setFamilySize(int familySize) {
        FamilySize = familySize;
    }

    public String getCurrentRCountry() {
        return CurrentRCountry;
    }

    public void setCurrentRCountry(String currentRCountry) {
        CurrentRCountry = currentRCountry;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "Email='" + Email + '\'' +
                ", FName='" + FName + '\'' +
                ", LName='" + LName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Password='" + Password + '\'' +
                ", CPassword='" + CPassword + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", GrossMSalary=" + GrossMSalary +
                ", Occupation='" + Occupation + '\'' +
                ", FamilySize=" + FamilySize +
                ", CurrentRCountry='" + CurrentRCountry + '\'' +
                ", City='" + City + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
