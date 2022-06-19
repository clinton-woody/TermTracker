package clinton.woody.android.termtracker.Entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;


@Entity(tableName = "users",
        indices = {@Index(value = {"userName"},
        unique = true)})


public class User {
    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String userName;
    private String password;
    private Boolean admin;
    private String lastLogin;
    private Boolean enabled;

    public static int greatestUser = 0;

    public static int selectedUserID = 0;
    public static String selectedName = "";
    public static String selectedPassword = "ThisIsAnUnsetPassword!Q@W#E$R";//Only set this to prevent an unintended error that allows login without input of password.
    public static Boolean selectedAdmin = false;
    public static String selectedLastLogin = "";
    public static Boolean selectedEnabled = false;


    public User(int userID, String userName, String password, Boolean admin, String lastLogin, Boolean enabled) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.admin = admin;
        this.lastLogin = lastLogin;
        this.enabled = enabled;
    }


    public String timeStamper(){
        Date instant = Timestamp.from(Instant.now());
        lastLogin=instant.toString();
        return lastLogin;
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

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        Date instant = Timestamp.from(Instant.now());
        this.lastLogin = lastLogin;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

