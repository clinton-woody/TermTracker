package clinton.woody.android.termtracker.Entity;

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

    public static int selectedCourse = -1;

    public Course(int courseID, int termID, String title, String start, String end, int instructorID, String status) {
        this.courseID = courseID;
        this.termID = termID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.instructorID = instructorID;
        this.status = status;
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
}
