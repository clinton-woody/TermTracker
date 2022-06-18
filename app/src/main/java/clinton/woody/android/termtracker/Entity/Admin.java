package clinton.woody.android.termtracker.Entity;

import android.view.View;

import androidx.room.PrimaryKey;

class Admin extends User{

    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String userName;
    private String password;
    private final Boolean admin = true;

    public Admin(int userID, String userName, String password, Boolean admin) {
        super(userID, userName, password, admin);
    }

    public void toScreen(View view) {

    }
}
