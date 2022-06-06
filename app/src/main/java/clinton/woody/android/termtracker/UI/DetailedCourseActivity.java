package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class DetailedCourseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository;
    public static Boolean active = false;
    public static String title;
    public static String startDate;
    public static String endDate;
    public static String optionalNote;
    public static int instructorIndex;
    public static int statusIndex;

    public static EditText editTitle;
    public static EditText editStart;
    public static EditText editEnd;
    public static EditText editNote;
    public static Spinner spinnerInstructor;
    public static Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = true;
        setContentView(R.layout.activity_detailed_course);
        editTitle=findViewById(R.id.courseTitle);
        editStart=findViewById(R.id.courseStart);
        editEnd=findViewById(R.id.courseEnd);
        editNote=findViewById(R.id.courseNote);
        spinnerInstructor=findViewById(R.id.courseInstructor);
        spinnerStatus=findViewById(R.id.courseStatus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());
        List<Course> allCourses= repository.getAllCourses();
        RecyclerView recyclerView=findViewById(R.id.recyclerview_course);
        final DetailedCourseAdapter detailedCourseAdapter=new DetailedCourseAdapter(this);
        recyclerView.setAdapter(detailedCourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedCourseAdapter.setCourses(allCourses);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.instructor_list, android.R.layout.simple_spinner_item );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstructor.setAdapter(adapter1);
        spinnerInstructor.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.status_list, android.R.layout.simple_spinner_item );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter2);
        spinnerStatus.setOnItemSelectedListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                active = false;
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
//Scope: Mobile App Dev
//Parent: CourseActivity
//Child: None