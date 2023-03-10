package evoting.dto;

public class UserDetails {

    public UserDetails(String username, String userid, String password, long mobile, String address, String email, String city, String gender) {
        this.username = username;
        this.userid = userid;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.city = city;
        this.gender = gender;
    }
    
    private String username;
    private String userid;
    private String password;
    private long mobile;
    private String address;
    private String email;
    private String city;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "username=" + username + ", userid=" + userid + ", password=" + password + ", mobile=" + mobile + ", gender=" + gender + ", address=" + address + ", email=" + email + ", city=" + city + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
    public UserDetails(){
        
    }
 }
