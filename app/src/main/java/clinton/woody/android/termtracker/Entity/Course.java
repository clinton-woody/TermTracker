package clinton.woody.android.termtracker.Entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private int termID;
    private String title;
    private String start; //Will need formatter
    private String end; //Will need formatter
    private int instructorID;
    private String status;

    @Nullable
    private String note;

    public static int selectedCourse = 0;
    public static String selectedTitle = null;
    public static String selectedStart = null;
    public static String selectedEnd = null;
    public static int selectedInstructor = 0;
    public static String selectedStatus = null;
    public static String selectedNote = null;

    public Course(int courseID, int termID, String title, String start, String end, int instructorID, String status, String note) {
        this.courseID = courseID;
        this.termID = termID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.instructorID = instructorID;
        this.status = status;
        this.note = note;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
