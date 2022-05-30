package clinton.woody.android.termtracker.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String userName;
    private String password;
    private Boolean admin;

    public static int selectedUser = 1;
    public static int greatestUser = 0;

    public User(int userID, String userName, String password, Boolean admin) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}

