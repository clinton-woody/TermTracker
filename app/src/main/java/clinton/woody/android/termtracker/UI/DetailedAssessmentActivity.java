package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.R;

public class DetailedAssessmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository;
    public static Boolean active = false;
    public static String type;
    public static String title;
    public static String startDate;
    public static String endDate;
    public static int typeIndex;

    public static EditText editTitle;
    public static EditText editStart;
    public static EditText editEnd;
    public static Spinner spinnerType;
    public static TextView selectedTitle;
    public static TextView selectedStart;
    public static TextView selectedEnd;
    public static TextView selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = true;
        setContentView(R.layout.activity_detailed_assessment);
        editTitle=findViewById(R.id.assessmentTitle);
        editStart=findViewById(R.id.assessmentStart);
        editEnd=findViewById(R.id.assessmentEnd);
        spinnerType=findViewById(R.id.assessmentType);
        selectedTitle=findViewById(R.id.selectedATitle);
        selectedStart=findViewById(R.id.selectedAStart);
        selectedEnd=findViewById(R.id.selectedAEnd);
        selectedType=findViewById(R.id.selectedAType);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());
        List<Assessment> allAssessments= repository.getAllAssessments();
        RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
        final DetailedAssessmentAdapter detailedAssessmentAdapter=new DetailedAssessmentAdapter(this);
        recyclerView.setAdapter(detailedAssessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedAssessmentAdapter.setAssessments(allAssessments);
        spinnerType=findViewById(R.id.assessmentType);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.type_list, android.R.layout.simple_spinner_item );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter3);
        spinnerType.setOnItemSelectedListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_assessment, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                active = false;
                Intent intent=new Intent(DetailedAssessmentActivity.this,AssessmentActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            case R.id.updateAssessment:
                if (Assessment.selectedAssessment == 0){
                    if (spinnerType.getSelectedItemPosition() == 1){
                        Assessment.selectedType = "Performance";
                    }else if (spinnerType.getSelectedItemPosition() == 1){
                        Assessment.selectedType = "Objective";
                    }else{
                        Assessment.selectedType = null;
                    }
                    Assessment.selectedEnd = editEnd.getText().toString();
                    Assessment.selectedStart = editStart.getText().toString();
                    Assessment.selectedTitle = editTitle.getText().toString();
                    Assessment assessment=new Assessment(Assessment.selectedAssessment, Course.selectedCourse, Assessment.selectedTitle, Assessment.selectedStart, Assessment.selectedEnd, Assessment.selectedType);
                    repository.insert(assessment);
                    title = null;
                    startDate = null;
                    endDate = null;
                    type = null;
                    selectedTitle.setText(title);
                    editTitle.setText(title);
                    selectedStart.setText(startDate);
                    editStart.setText(startDate);
                    selectedEnd.setText(endDate);
                    editEnd.setText(endDate);
                    spinnerType.setSelection(0);
                    selectedType.setText(type);
                    repository=new Repository(getApplication());
                    List<Assessment> allAssessments=repository.getAllAssessments();
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
                    final DetailedAssessmentAdapter detailedAssessmentAdapter=new DetailedAssessmentAdapter(this);
                    recyclerView.setAdapter(detailedAssessmentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    detailedAssessmentAdapter.setAssessments(allAssessments);
                    Assessment.selectedAssessment = 0;
                }else{
                    if (spinnerType.getSelectedItemPosition() == 1){
                        Assessment.selectedType = "Performance";
                    }else if (spinnerType.getSelectedItemPosition() == 1){
                        Assessment.selectedType = "Objective";
                    }else{
                        Assessment.selectedType = null;
                    }
                    Assessment.selectedEnd = editEnd.getText().toString();
                    Assessment.selectedStart = editStart.getText().toString();
                    Assessment.selectedTitle = editTitle.getText().toString();
                    Assessment assessment=new Assessment(Assessment.selectedAssessment, Course.selectedCourse, Assessment.selectedTitle, Assessment.selectedStart, Assessment.selectedEnd, Assessment.selectedType);
                    repository.update(assessment);
                    title = null;
                    startDate = null;
                    endDate = null;
                    type = null;
                    selectedTitle.setText(title);
                    editTitle.setText(title);
                    selectedStart.setText(startDate);
                    editStart.setText(startDate);
                    selectedEnd.setText(endDate);
                    editEnd.setText(endDate);
                    spinnerType.setSelection(0);
                    selectedType.setText(type);
                    repository=new Repository(getApplication());
                    List<Assessment> allAssessments=repository.getAllAssessments();
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
                    final DetailedAssessmentAdapter detailedAssessmentAdapter=new DetailedAssessmentAdapter(this);
                    recyclerView.setAdapter(detailedAssessmentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    detailedAssessmentAdapter.setAssessments(allAssessments);
                    Assessment.selectedAssessment = 0;
                }
            case R.id.deleteAssessment:
            case R.id.clearAssessment:
                title = null;
                startDate = null;
                endDate = null;
                type = null;
                selectedTitle.setText(title);
                editTitle.setText(title);
                selectedStart.setText(startDate);
                editStart.setText(startDate);
                selectedEnd.setText(endDate);
                editEnd.setText(endDate);
                spinnerType.setSelection(0);
                selectedType.setText(type);
                Assessment.selectedAssessment = 0;
        }
        return super.onOptionsItemSelected(item);
    }

    /*


     */

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