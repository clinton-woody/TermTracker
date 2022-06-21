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

import java.text.ParseException;
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
        User.activeName = selectedName;
        try{
            User user = repository.getUser(User.activeName);
            User.activeUserID = user.getUserID();
            User.activeName = user.getUserName();
            User.activePassword = user.getPassword();
            User.activeAdmin = user.getAdmin();
            User.activeEnabled = user.getEnabled();
            User.activeLastLogin = user.getLastLogin();
            if (User.activeEnabled == true) {

                if (User.activePassword.equals(checkerPassword)) {

                    if (User.activeAdmin == true) {
                        User.activeLastLogin= user.timeStamper();
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        User updateUser = new User(User.activeUserID,User.activeName, User.activePassword, User.activeAdmin, User.activeLastLogin, User.activeEnabled);
                        repository.update(updateUser);
                        startActivity(intent);
                    } else {
                        User.activeLastLogin= user.timeStamper();
                        Intent intent = new Intent(LoginActivity.this, TermActivity.class);
                        User updateUser = new User(User.activeUserID,User.activeName, User.activePassword, User.activeAdmin, User.activeLastLogin, User.activeEnabled);
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


            } else if (User.activeUserID > 0 && User.activeEnabled == false) {
                //Make a popup that says "Account is not active"
                Context context = getApplicationContext();
                CharSequence text = "Account is not active.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Unexpected Error Occured.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }catch (Exception e){
            Context context = getApplicationContext();
            CharSequence text = "User does not exist.";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}

