package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class DetailedAssessmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository;
    int courseID;
    public static Boolean active = false;
    public static String type;
    public static String title;
    public static String start;
    public static String end;
    public static int typeIndex;

    public static EditText editTitle;
    public static EditText editStart;
    public static EditText editEnd;
    public static Spinner spinnerType;
    public static TextView selectedTitle;
    public static TextView selectedStart;
    public static TextView selectedEnd;
    public static TextView selectedType;

    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    String dateFormatter;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseID=Course.selectedCourse;
        active = true;
        setContentView(R.layout.activity_detailed_assessment);
        editTitle=findViewById(R.id.assessmentTitle);

        // Date Below This Line

        dateFormatter = "MM/dd/yy";
        simpleDateFormat = new SimpleDateFormat(dateFormatter, Locale.US);

        editStart=findViewById(R.id.assessmentStart);
        editStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Date date;
                String info=editStart.getText().toString();
                if(info.equals(""))info="01/01/22";
                try {
                    myCalendarStart.setTime(simpleDateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog( DetailedAssessmentActivity.this, startDate,
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        startDate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR,year);
                myCalendarStart.set(Calendar.MONTH,monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabelStart();
            }
        };

        editEnd=findViewById(R.id.assessmentEnd);
        editEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Date date;
                String info=editEnd.getText().toString();
                if(info.equals(""))info="01/01/22";
                try {
                    myCalendarStart.setTime(simpleDateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog( DetailedAssessmentActivity.this, endDate,
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        endDate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR,year);
                myCalendarStart.set(Calendar.MONTH,monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabelEnd();
            }
        };



        // Date Above This Line

        spinnerType=findViewById(R.id.assessmentType);
        selectedTitle=findViewById(R.id.selectedATitle);
        selectedStart=findViewById(R.id.selectedAStart);
        selectedEnd=findViewById(R.id.selectedAEnd);
        selectedType=findViewById(R.id.selectedAType);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());
        List<Assessment> filteredAssessments=new ArrayList<>();
        for (Assessment a:repository.getAllAssessments()){
            if(a.getCourseID()==courseID)filteredAssessments.add(a);
        }
        RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
        final DetailedAssessmentAdapter adapter=new DetailedAssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(filteredAssessments);
        spinnerType=findViewById(R.id.assessmentType);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.type_list, android.R.layout.simple_spinner_item );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter3);
        spinnerType.setOnItemSelectedListener(this);
    }

    private void updateLabelStart(){
        editStart.setText(simpleDateFormat.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd(){
        editEnd.setText(simpleDateFormat.format(myCalendarStart.getTime()));
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
                courseID=Course.selectedCourse;
                if (Assessment.selectedAssessment == 0){
                    if (spinnerType.getSelectedItemPosition() == 1){
                        Assessment.selectedType = "Performance";
                    }else if (spinnerType.getSelectedItemPosition() == 2){
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
                    selectedStart.setText(start);
                    editStart.setText(start);
                    selectedEnd.setText(end);
                    editEnd.setText(end);
                    spinnerType.setSelection(0);
                    selectedType.setText(type);
                    repository=new Repository(getApplication());
                    List<Assessment> filteredAssessments=new ArrayList<>();
                    for (Assessment a:repository.getAllAssessments()){
                        if(a.getCourseID()==courseID)filteredAssessments.add(a);
                    }
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
                    final DetailedAssessmentAdapter adapter=new DetailedAssessmentAdapter(this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setAssessments(filteredAssessments);
                    Assessment.selectedAssessment = 0;
                }else{
                    if (spinnerType.getSelectedItemPosition() == 1){
                        Assessment.selectedType = "Performance";
                    }else if (spinnerType.getSelectedItemPosition() == 2){
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
                    selectedStart.setText(start);
                    editStart.setText(start);
                    selectedEnd.setText(end);
                    editEnd.setText(end);
                    spinnerType.setSelection(0);
                    selectedType.setText(type);

                    repository=new Repository(getApplication());
                    List<Assessment> filteredAssessments=new ArrayList<>();
                    for (Assessment a:repository.getAllAssessments()){
                        if(a.getCourseID()==courseID)filteredAssessments.add(a);
                    }
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
                    final DetailedAssessmentAdapter adapter=new DetailedAssessmentAdapter(this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setAssessments(filteredAssessments);
                    Assessment.selectedAssessment = 0;
                }
                return true;
            case R.id.deleteAssessment:
                courseID=Course.selectedCourse;
                Assessment assessment=new Assessment(Assessment.selectedAssessment, Course.selectedCourse, Assessment.selectedTitle, Assessment.selectedStart, Assessment.selectedEnd, Assessment.selectedType);
                repository.delete(assessment);
                repository=new Repository(getApplication());
                List<Assessment> filteredAssessments=new ArrayList<>();
                for (Assessment a:repository.getAllAssessments()){
                    if(a.getCourseID()==courseID)filteredAssessments.add(a);
                }
                RecyclerView recyclerView=findViewById(R.id.recyclerview_assessment);
                final DetailedAssessmentAdapter adapter=new DetailedAssessmentAdapter(this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter.setAssessments(filteredAssessments);
                Assessment.selectedAssessment = 0;
                return true;
            case R.id.clearAssessment:
                title = null;
                startDate = null;
                endDate = null;
                type = null;
                selectedTitle.setText(title);
                editTitle.setText(title);
                selectedStart.setText(start);
                editStart.setText(start);
                selectedEnd.setText(end);
                editEnd.setText(end);
                spinnerType.setSelection(0);
                selectedType.setText(type);
                Assessment.selectedAssessment = 0;
                return true;

            case R.id.notifyAssessmentStart:
                String startFromScreen=editStart.getText().toString();
                Date d1=null;
                try{
                    d1=simpleDateFormat.parse(startFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent1=new Intent(DetailedAssessmentActivity.this,MyReceiver1.class);
                return true;

            case R.id.notifyAssessmentEnd:
                String dateFromScreen=editEnd.getText().toString();
                Date d2=null;
                try{
                    d2=simpleDateFormat.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent2=new Intent(DetailedAssessmentActivity.this,MyReceiver1.class);
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