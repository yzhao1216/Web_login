package entity;

/**
 * Created by EYlee on 7/27/15.
 */
public class User {
    private int id;
    private String username;
    private String pwd;
    private String name;
    private String gender;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id+","+username+","+pwd+","+name+","+gender;
    }
}
