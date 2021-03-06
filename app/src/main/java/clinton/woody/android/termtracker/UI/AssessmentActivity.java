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
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class AssessmentActivity extends AppCompatActivity {
    Repository repository;
    int courseID;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        courseID = Course.selectedCourse;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed

        Repository repository=new Repository(getApplication());
        List<Assessment> filteredAssessments=new ArrayList<>();
        for (Assessment a:repository.getAllAssessments()){
            if(a.getCourseID()==courseID)filteredAssessments.add(a);
        }
        RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
        final AssessmentAdapter adapter=new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(filteredAssessments);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.detailedAssessment:
                Intent intent=new Intent(AssessmentActivity.this,DetailedAssessmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void toDetailedAssessment(View view) {
        Intent intent=new Intent(AssessmentActivity.this,DetailedAssessmentActivity.class);
        startActivity(intent);
    }
}
//Scope: Mobile App Dev
//Parent: CourseActivity
//Child: DetailedAssessmentActivity