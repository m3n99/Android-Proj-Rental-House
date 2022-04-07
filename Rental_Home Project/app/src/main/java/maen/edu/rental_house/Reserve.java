package maen.edu.rental_house;

import java.io.Serializable;
import java.util.ArrayList;

public class Reserve implements Serializable {
    public static ArrayList<Reserve> reserve = new ArrayList<Reserve>();
    Integer ID;
    String city;
    String date;
    int postal;
    String agencyName;
    String firstName;
    String lastName;
    String address;
    String emailAgency;
    String emailTenant;
    int approve;
    boolean Reserved;

    public Reserve() {
    }

    public Reserve(Integer ID, String city, String date, int postal, String agencyName,
                   String firstName, String lastName, String address, String emailAgency, String emailTenant, boolean reserved, int approve) {
        this.ID = ID;
        this.city = city;
        this.date = date;
        this.postal = postal;
        this.agencyName = agencyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAgency = emailAgency;
        this.emailTenant = emailTenant;
        Reserved = reserved;
        this.approve = approve;
    }

    public Reserve(String city, String date, int postal, String agencyName,
                   String firstName, String lastName, String emailAgency, String emailTenant,int approve) {
        this.city = city;
        this.date = date;
        this.postal = postal;
        this.agencyName = agencyName;
        this.firstName = firstName;
        this.lastName = lastName;
       // this.address = address;
        this.emailAgency = emailAgency;
        this.emailTenant = emailTenant;
        this.approve = approve;

    }

    public static ArrayList<Reserve> getReserve() {
        return reserve;
    }

    public static void setReserve(ArrayList<Reserve> reserve) {
        Reserve.reserve = reserve;
    }

    public boolean isReserved() {
        return Reserved;
    }

    public void setReserved(boolean reserved) {
        Reserved = reserved;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAgency() {
        return emailAgency;
    }

    public void setEmailAgency(String emailAgency) {
        this.emailAgency = emailAgency;
    }

    public String getEmailTenant() {
        return emailTenant;
    }

    public void setEmailTenant(String emailTenant) {
        this.emailTenant = emailTenant;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "ID=" + ID +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", postal=" + postal +
                ", agencyName='" + agencyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", emailAgency='" + emailAgency + '\'' +
                ", emailTenant='" + emailTenant + '\'' +
                ", approve=" + approve +
                ", Reserved=" + Reserved +
                '}';
    }
}
