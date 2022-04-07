package maen.edu.rental_house;

public class RentingAgency {
    private String Email;
    private String AName;
    private String Password;
    private String CPassword;
    private String Country;
    private String City;
    private int mPhone;


    public RentingAgency() {
    }

    public RentingAgency(String email, String AName, String password, String CPassword, String country, String city, int mPhone) {
        Email = email;
        this.AName = AName;
        Password = password;
        this.CPassword = CPassword;
        Country = country;
        City = city;
        this.mPhone = mPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
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

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getmPhone() {
        return mPhone;
    }

    public void setmPhone(int mPhone) {
        this.mPhone = mPhone;
    }

    @Override
    public String toString() {
        return "RentingAgency{" +
                "Email='" + Email + '\'' +
                ", AName='" + AName + '\'' +
                ", Password='" + Password + '\'' +
                ", CPassword='" + CPassword + '\'' +
                ", Country='" + Country + '\'' +
                ", City='" + City + '\'' +
                ", mPhone=" + mPhone +
                '}';
    }
}
