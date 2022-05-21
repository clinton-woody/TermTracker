package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        RecyclerView recyclerView=findViewById(R.id.recyclerview_course);
        Repository repo=new Repository(getApplication());
        List<Course> courses=repo.getAllCourses();
        final CourseAdapter adapter=new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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