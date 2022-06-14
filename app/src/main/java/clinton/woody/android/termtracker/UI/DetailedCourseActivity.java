package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlarmManager;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
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

public class DetailedCourseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository;
    int termID;
    public static String title;
    public static String start;
    public static String end;
    public static String optionalNote;
    public static String status;
    public static String name;
    public static String phone;
    public static String email;
    public static int instructorId;
    public static EditText editTitle;
    public static EditText editStart;
    public static EditText editEnd;
    public static EditText editNote;
    public static Spinner spinnerInstructor;
    public static Spinner spinnerStatus;
    public static TextView selectedTitle;
    public static TextView selectedStart;
    public static TextView selectedEnd;
    public static TextView selectedStatus;
    public static TextView selectedName;
    public static TextView selectedPhone;
    public static TextView selectedEmail;
    public static TextView selectedNote;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    String dateFormatter;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Course.selectedCourse = 0;
        termID = Term.selectedTerm;
        setContentView(R.layout.activity_detailed_course);
        editTitle=findViewById(R.id.courseTitle);

        // Date Below This Line

        dateFormatter = "MM/dd/yy";   //*********************
        simpleDateFormat = new SimpleDateFormat(dateFormatter, Locale.US);   //*********************

        editStart=findViewById(R.id.courseStart);   //*********************
        editStart.setOnClickListener(new View.OnClickListener(){   //*********************

            @Override
            public void onClick(View view) {   //*********************
                Date date;   //*********************
                String info=editStart.getText().toString();   //*********************
                if(info.equals(""))info="01/01/22";   //*********************
                try {   //*********************
                    myCalendarStart.setTime(simpleDateFormat.parse(info));   //*********************
                } catch (ParseException e) {   //*********************
                    e.printStackTrace();   //*********************
                }
                new DatePickerDialog( DetailedCourseActivity.this, startDate,   //*********************
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),   //*********************
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();   //*********************
            }
        });
        startDate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {   //*********************
                myCalendarStart.set(Calendar.YEAR,year);   //*********************
                myCalendarStart.set(Calendar.MONTH,monthOfYear);   //*********************
                myCalendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);   //*********************
                updateLabelStart();   //*********************
            }
        };

        editEnd=findViewById(R.id.courseEnd);   //*********************
        editEnd.setOnClickListener(new View.OnClickListener(){   //*********************

            @Override
            public void onClick(View view) {   //*********************
                Date date;   //*********************
                String info=editEnd.getText().toString();   //*********************
                if(info.equals(""))info="01/01/22";   //*********************
                try {   //*********************
                    myCalendarStart.setTime(simpleDateFormat.parse(info));   //*********************
                } catch (ParseException e) {   //*********************
                    e.printStackTrace();   //*********************
                }
                new DatePickerDialog( DetailedCourseActivity.this, endDate,   //*********************
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),   //*********************
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();   //*********************
            }
        });
        endDate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {   //*********************
                myCalendarStart.set(Calendar.YEAR,year);   //*********************
                myCalendarStart.set(Calendar.MONTH,monthOfYear);   //*********************
                myCalendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);   //*********************
                updateLabelEnd();   //*********************
            }
        };

        // Date Above This Line

        editNote=findViewById(R.id.courseNote);
        spinnerInstructor=findViewById(R.id.courseInstructor);
        spinnerStatus=findViewById(R.id.courseStatus);
        selectedTitle=findViewById(R.id.selectedCTitle);
        selectedStart=findViewById(R.id.selectedCStart);   //*********************
        selectedEnd=findViewById(R.id.selectedCEnd);   //*********************
        selectedStatus=findViewById(R.id.selectedCStatus);
        selectedName=findViewById(R.id.selectedCInstructorName);
        selectedPhone=findViewById(R.id.selectedCInstructorPhone);
        selectedEmail=findViewById(R.id.selectedCInstructorEmail);
        selectedNote=findViewById(R.id.selectedCOptionalNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed

        repository=new Repository(getApplication());
        List<Course> filteredCourses=new ArrayList<>();
        for (Course c:repository.getAllCourses()){
            if(c.getTermID()==termID)filteredCourses.add(c);
        }
        RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedCourse);
        final DetailedCourseAdapter detailedCourseAdapter=new DetailedCourseAdapter(this);
        recyclerView.setAdapter(detailedCourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedCourseAdapter.setCourses(filteredCourses);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.instructor_list, android.R.layout.simple_spinner_item );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstructor.setAdapter(adapter1);
        spinnerInstructor.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.status_list, android.R.layout.simple_spinner_item );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter2);
        spinnerStatus.setOnItemSelectedListener(this);
    }

    private void updateLabelStart(){   //*********************
        editStart.setText(simpleDateFormat.format(myCalendarStart.getTime()));   //*********************
    }   //*********************

    private void updateLabelEnd(){   //*********************
        editEnd.setText(simpleDateFormat.format(myCalendarStart.getTime()));   //*********************
    }   //*********************

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(DetailedCourseActivity.this,CourseActivity.class);
                startActivity(intent);
                this.finish();
                return true;

            case R.id.updateCourse:
                if (Course.selectedCourse == 0){
                    if (spinnerStatus.getSelectedItemPosition() == 1){
                        Course.selectedStatus="Plan to Take";
                    }else if (spinnerStatus.getSelectedItemPosition() == 2){
                        Course.selectedStatus="In Progress";
                    }else if (spinnerStatus.getSelectedItemPosition() == 3){
                        Course.selectedStatus="Completed";
                    }else if (spinnerStatus.getSelectedItemPosition() == 4){
                        Course.selectedStatus="Dropped";
                    }else{
                        Course.selectedStatus=null;
                    }
                    if (spinnerInstructor.getSelectedItemPosition() == 1){
                        Course.selectedInstructor=1;
                    }else if (spinnerInstructor.getSelectedItemPosition() == 2){
                        Course.selectedInstructor=2;
                    }else{
                        Course.selectedInstructor=0;
                    }
                    Course.selectedEnd=editEnd.getText().toString();   //*********************
                    Course.selectedStart =editStart.getText().toString();   //*********************
                    Course.selectedTitle=editTitle.getText().toString();
                    Course.selectedNote=editNote.getText().toString();
                    Course current=new Course(Course.selectedCourse, Term.selectedTerm, Course.selectedTitle, Course.selectedStart, Course.selectedEnd, Course.selectedInstructor, Course.selectedStatus, Course.selectedNote);
                    repository.insert(current);
                    DetailedCourseActivity.title = null;
                    DetailedCourseActivity.start = "";   //*********************
                    DetailedCourseActivity.end = "";   //*********************
                    DetailedCourseActivity.optionalNote = null;
                    DetailedCourseActivity.status = null;
                    DetailedCourseActivity.name = null;
                    DetailedCourseActivity.phone = null;
                    DetailedCourseActivity.email = null;
                    DetailedCourseActivity.optionalNote = null;
                    selectedTitle.setText(title);
                    editTitle.setText(title);
                    selectedStart.setText(start);   //*********************
                    editStart.setText(start);   //*********************
                    selectedEnd.setText(end);   //*********************
                    editEnd.setText(end);   //*********************
                    editNote.setText(optionalNote);
                    spinnerStatus.setSelection(0);
                    selectedStatus.setText(status);
                    spinnerInstructor.setSelection(0);
                    selectedName.setText(name);
                    selectedPhone.setText(phone);
                    selectedEmail.setText(email);
                    selectedNote.setText(optionalNote);
                    repository=new Repository(getApplication());
                    List<Course> filteredCourses=new ArrayList<>();
                    for (Course c:repository.getAllCourses()){
                        if(c.getTermID()==termID)filteredCourses.add(c);
                    }
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedCourse);
                    final DetailedCourseAdapter detailedCourseAdapter=new DetailedCourseAdapter(this);
                    recyclerView.setAdapter(detailedCourseAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    detailedCourseAdapter.setCourses(filteredCourses);
                    Course.selectedCourse = 0;
                    return true;
                }else{
                    if (spinnerStatus.getSelectedItemPosition() == 1){
                        Course.selectedStatus="Plan to Take";
                    }else if (spinnerStatus.getSelectedItemPosition() == 2){
                        Course.selectedStatus="In Progress";
                    }else if (spinnerStatus.getSelectedItemPosition() == 3){
                        Course.selectedStatus="Completed";
                    }else if (spinnerStatus.getSelectedItemPosition() == 4){
                        Course.selectedStatus="Dropped";
                    }else{
                        Course.selectedStatus=null;
                    }
                    if (spinnerInstructor.getSelectedItemPosition() == 1){
                        Course.selectedInstructor=1;
                    }else if (spinnerInstructor.getSelectedItemPosition() == 2){
                        Course.selectedInstructor=2;
                    }else{
                        Course.selectedInstructor=0;
                    }
                    Course.selectedEnd=editEnd.getText().toString();   //*********************
                    Course.selectedStart =editStart.getText().toString();   //*********************
                    Course.selectedTitle=editTitle.getText().toString();
                    Course.selectedNote=editNote.getText().toString();
                    Course current=new Course(Course.selectedCourse, Term.selectedTerm, Course.selectedTitle, Course.selectedStart, Course.selectedEnd, Course.selectedInstructor, Course.selectedStatus, Course.selectedNote);
                    repository.update(current);
                    title = null;
                    start = "";   //*********************
                    end = "";   //*********************
                    optionalNote = null;
                    status = null;
                    name = null;
                    phone = null;
                    email = null;
                    optionalNote = null;
                    selectedTitle.setText(title);
                    editTitle.setText(title);
                    selectedStart.setText(start);   //*********************
                    editStart.setText(start);   //*********************
                    selectedEnd.setText(end);   //*********************
                    editEnd.setText(end);   //*********************
                    editNote.setText(optionalNote);
                    spinnerStatus.setSelection(0);
                    selectedStatus.setText(status);
                    spinnerInstructor.setSelection(0);
                    selectedName.setText(name);
                    selectedPhone.setText(phone);
                    selectedEmail.setText(email);
                    selectedNote.setText(optionalNote);
                    repository=new Repository(getApplication());
                    List<Course> filteredCourses=new ArrayList<>();
                    for (Course c:repository.getAllCourses()){
                        if(c.getTermID()==termID)filteredCourses.add(c);
                    }
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedCourse);
                    final DetailedCourseAdapter detailedCourseAdapter=new DetailedCourseAdapter(this);
                    recyclerView.setAdapter(detailedCourseAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    detailedCourseAdapter.setCourses(filteredCourses);
                    Course.selectedCourse = 0;
                    return true;
                }

            case R.id.deleteCourse:
                int courseID = Course.selectedCourse;
                List<Assessment> filteredAssessments=new ArrayList<>();
                for (Assessment a:repository.getAllAssessments()){
                    if(a.getCourseID()==courseID)filteredAssessments.add(a);
                }
                if (filteredAssessments.size()==0){
                    Course course = new Course(Course.selectedCourse, Term.selectedTerm, Course.selectedTitle, Course.selectedStart, Course.selectedEnd, Course.selectedInstructor, Course.selectedStatus, Course.selectedNote);
                    repository.delete(course);
                }
                repository=new Repository(getApplication());
                List<Course> filteredCourses=new ArrayList<>();
                for (Course c:repository.getAllCourses()){
                    if(c.getTermID()==termID)filteredCourses.add(c);
                }
                RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedCourse);
                final DetailedCourseAdapter detailedCourseAdapter=new DetailedCourseAdapter(this);
                recyclerView.setAdapter(detailedCourseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                detailedCourseAdapter.setCourses(filteredCourses);
                return true;

            case R.id.clearCourse:
                Course.selectedCourse = 0;
                title = null;
                start = "";   //*********************
                end = "";   //*********************
                optionalNote = null;
                status = null;
                name = null;
                phone = null;
                email = null;
                optionalNote = null;
                selectedTitle.setText(title);
                editTitle.setText(title);
                selectedStart.setText(start);   //*********************
                editStart.setText(start);   //*********************
                selectedEnd.setText(end);   //*********************
                editEnd.setText(end);   //*********************
                editNote.setText(optionalNote);
                spinnerStatus.setSelection(0);
                selectedStatus.setText(status);
                spinnerInstructor.setSelection(0);
                selectedName.setText(name);
                selectedPhone.setText(phone);
                selectedEmail.setText(email);
                selectedNote.setText(optionalNote);
                return true;

            case R.id.shareNote:
                Intent sendIntent= new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,Course.selectedNote);
                sendIntent.putExtra(Intent.EXTRA_TITLE, Course.selectedTitle + " Note");
                sendIntent.setType("text/plain");
                Intent shareIntent=Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notifyCourseStart:   //*********************
                String startFromScreen=editStart.getText().toString();   //*********************
                Date d1=null;   //*********************
                try{   //*********************
                    d1=simpleDateFormat.parse(startFromScreen);   //*********************
                } catch (ParseException e) {   //*********************
                    e.printStackTrace();   //*********************
                }   //*********************
                Long trigger1=d1.getTime();   //*********************
                Intent intent1=new Intent(DetailedCourseActivity.this,MyReceiver1.class);   //*********************
                intent1.putExtra("key", "Course "+ Course.selectedTitle + " Starts Today");   //*********************
                PendingIntent sender1=PendingIntent.getBroadcast(DetailedCourseActivity.this, ++MainActivity.alertNumber,intent1,0);   //*********************
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);   //*********************
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger1, sender1);   //*********************
                return true;   //*********************

            case R.id.notifyCourseEnd:   //*********************
                String dateFromScreen=editEnd.getText().toString();   //*********************
                Date d2=null;   //*********************

                try{   //*********************
                    d2=simpleDateFormat.parse(dateFromScreen);   //*********************
                } catch (ParseException e) {   //*********************
                    e.printStackTrace();   //*********************
                }   //*********************
                Long trigger2=d2.getTime();   //*********************
                Intent intent2=new Intent(DetailedCourseActivity.this,MyReceiver1.class);   //*********************
                intent2.putExtra("key", "Course "+ Course.selectedTitle + " Ends Today");   //*********************
                PendingIntent sender2=PendingIntent.getBroadcast(DetailedCourseActivity.this, ++MainActivity.alertNumber,intent2,0);   //*********************
                alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);   //*********************
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);   //*********************
                return true;   //*********************

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