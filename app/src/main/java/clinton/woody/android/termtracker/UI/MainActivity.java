package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Instructor;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.Entity.User;
import clinton.woody.android.termtracker.R;

public class MainActivity extends AppCompatActivity {
    public static int alertNumber;
    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toScreen(View view) {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Repository repo=new Repository(getApplication());
//        Term term1=new Term(1, 1, "testTerm1", "01June2021", "01June2021");//not needed
//        Term term2=new Term(2, 1, "testTerm2", "01June2021", "01June2021");//not needed
//        Course course1=new Course( 1, 1, "testCourse", "01June2021", "1June2021", 1, "Dropped", "This is a note");//not needed
//        Assessment assessment1=new Assessment( 1, 1, "testAssessment", "01June2021", "01June2021", "Objective");//not needed
        User user1=new User(1, "usrTest", "usrTest", false, "Never Logged In", true);//needed
        User user2=new User(2, "admTest", "admTest", true, "Never Logged In", true);//needed
        Instructor instructor1=new Instructor(1, "instructor1", "555-123-4567", "instructor1@school.edu" );//needed
        Instructor instructor2=new Instructor(2, "instructor2", "555-123-8901", "instructor2@school.edu" );//needed
//        repo.insert(term1);//not needed
//        repo.insert(term2);//not needed
//        repo.insert(course1);//not needed
//        repo.insert(assessment1);//not needed
        repo.insert(user1);//needed
        repo.insert(user2);//needed
        repo.insert(instructor1);//needed
        repo.insert(instructor2);//needed
    }
    public void quit(View view) {
        MainActivity.this.finish();
        System.exit(0);
    }
}
//Scope: Mobile App Dev with some changes for Capstone
//Parent: None
//Child: TermActivity, LoginActivity

/*
Maybe change this to create and load the DB on creation instead of onClick to TermActivity.
 */