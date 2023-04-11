package cn.org.angry.entity;

/**
 * 用户
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String realname;
    private String gender;
    private String icon;
    private String phone;
    private String email;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String realname, String gender, String phone, String email) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public User(int id, String username, String password, String realname, String gender, String icon, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.gender = gender;
        this.icon = icon;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", gender='" + gender + '\'' +
                ", icon='" + icon + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
