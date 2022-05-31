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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.R;

public class DetailedAssessmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository;
    public static Boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = true;
        setContentView(R.layout.activity_detailed_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());
        List<Assessment> allAssessments= repository.getAllAssessments();
        RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
        final AssessmentAdapter assessmentAdapter=new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);

        Spinner spinner3 = findViewById(R.id.assessmentType);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.type_list, android.R.layout.simple_spinner_item );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_assessment, menu);
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
//Parent: AssessmentActivity
//Child: None