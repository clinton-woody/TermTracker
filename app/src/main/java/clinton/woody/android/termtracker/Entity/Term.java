package clinton.woody.android.termtracker.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private int userID;
    private String title;
    private String start; //Will need formatter
    private String end; //Will need formatter
    private String status;

    public Term(int termID, int userID, String title, String start, String end, String status) {
        this.termID = termID;
        this.userID = userID;
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termID=" + termID +
                ", userID=" + userID +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", status='" + status + '\'' +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

// Going to try to simulate @ForeignKey() through coded constraints and flags.
// May not be able to query without @ForeignKey(), need to test.
