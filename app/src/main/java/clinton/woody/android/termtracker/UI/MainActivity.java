package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Instructor;
import clinton.woody.android.termtracker.Entity.User;
import clinton.woody.android.termtracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //change
    }

    public void toTerm(View view) {
        Intent intent = new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);
        Repository repo=new Repository(getApplication());
        User user1=new User(1, "usrTest", "usrTest", false);
        User user2=new User(2, "admTest", "admTest", true);
        Instructor instructor1=new Instructor(1, "instructor1", "555-123-4567", "instructor1@school.edu" );
        Instructor instructor2=new Instructor(2, "instructor2", "555-123-8901", "instructor2@school.edu" );
        repo.insert(user1);
        repo.insert(user2);
        repo.insert(instructor1);
        repo.insert(instructor2);

    }
}
//Scope: Mobile App Dev with some changes for Capstone
//Parent: None
//Child: TermActivity, LoginActivity