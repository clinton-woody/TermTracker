package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class CourseActivity extends AppCompatActivity {
    private Repository repository;
    int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_course);//
        termID = Term.selectedTerm;//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());

        List<Course> filteredCourses=new ArrayList<>();
        for (Course c:repository.getAllCourses()){
            if(c.getTermID()==termID)filteredCourses.add(c);
        }

        RecyclerView recyclerView=findViewById(R.id.recyclerview_course);
        final CourseAdapter courseAdapter=new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(filteredCourses);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent termIntent=new Intent(CourseActivity.this,TermActivity.class);
                termIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(termIntent);
                this.finish();
                return true;
            case R.id.detailedCourse:
                Intent detailedCourseIntent=new Intent(CourseActivity.this,DetailedCourseActivity.class);
                detailedCourseIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailedCourseIntent);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toAssessment(View view) {
        Intent intent=new Intent(CourseActivity.this,AssessmentActivity.class);
        startActivity(intent);
    }

    public void toDetailedCourse(View view) {
        Intent intent=new Intent(CourseActivity.this,DetailedCourseActivity.class);
        startActivity(intent);
    }
}
//Scope: Mobile App Dev
//Parent: TermActivity
//Child: DetailedCourseActivity, AssessmentActivity