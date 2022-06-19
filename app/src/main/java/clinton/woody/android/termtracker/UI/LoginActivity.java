package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Instructor;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.Entity.User;
import clinton.woody.android.termtracker.R;

public class LoginActivity extends MainActivity {
    public static EditText inputName;
    public static EditText inputPassword;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputName=findViewById(R.id.loginUser);
        inputPassword=findViewById(R.id.loginPassword);
    }

    @Override
    public void toScreen(View view) {
        repository = new Repository(getApplication());
        String checkerPassword = inputPassword.getText().toString(); //The app crashes here
        String selectedName = inputName.getText().toString(); //The app crashes here
        User.selectedName = selectedName;
        User user = repository.getUser(User.selectedName);
        User.selectedUserID = user.getUserID();
        User.selectedName = user.getUserName();
        User.selectedPassword = user.getPassword();
        User.selectedAdmin = user.getAdmin();
        User.selectedEnabled = user.getEnabled();
        User.selectedLastLogin = user.getLastLogin();
        if (User.selectedEnabled == true) {

            if (User.selectedPassword.equals(checkerPassword)) {

                if (User.selectedAdmin == true) {
                    User.selectedLastLogin= user.timeStamper();
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    User updateUser = new User(User.selectedUserID,User.selectedName, User.selectedPassword, User.selectedAdmin, User.selectedLastLogin, User.selectedEnabled);
                    repository.update(updateUser);
                    startActivity(intent);
                } else {
                    User.selectedLastLogin= user.timeStamper();
                    Intent intent = new Intent(LoginActivity.this, TermActivity.class);
                    User updateUser = new User(User.selectedUserID,User.selectedName, User.selectedPassword, User.selectedAdmin, User.selectedLastLogin, User.selectedEnabled);
                    repository.update(updateUser);
                    startActivity(intent);
                }


            } else {
                //Make a popup that says "Username/Password does not match records."
                Context context = getApplicationContext();
                CharSequence text = "Username/Password does not match records.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


        } else if (User.selectedUserID > 0 && User.selectedEnabled == false) {
            //Make a popup that says "Account is not active"
            Context context = getApplicationContext();
            CharSequence text = "Account is not active.";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            //Make a popup that says "User does not exist."
            Context context = getApplicationContext();
            CharSequence text = "User does not exist.";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

