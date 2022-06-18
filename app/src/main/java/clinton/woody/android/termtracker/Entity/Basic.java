package clinton.woody.android.termtracker.Entity;

import android.content.Intent;
import android.view.View;

import androidx.room.PrimaryKey;

import clinton.woody.android.termtracker.UI.MainActivity;
import clinton.woody.android.termtracker.UI.TermActivity;

public class Basic extends User{

    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String userName;
    private String password;
    private final Boolean admin = false;

    public Intent intent = new Intent(String.valueOf(TermActivity.class));

    public Basic(int userID, String userName, String password, Boolean admin) {
        super(userID, userName, password, admin);
    }

    public void toScreen(View view) {
        Intent intent = new Intent(String.valueOf(TermActivity.class));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
