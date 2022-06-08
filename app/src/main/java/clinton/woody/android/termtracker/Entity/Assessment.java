package clinton.woody.android.termtracker.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    private int courseID;
    private String title;
    private String start;
    private String end;
    private String type;

    public static int selectedAssessment = -1;
    public static int greatestAssessment = 0;
    public Assessment(int assessmentID, int courseID, String title, String start, String end, String type) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.type = type;
    }



    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
