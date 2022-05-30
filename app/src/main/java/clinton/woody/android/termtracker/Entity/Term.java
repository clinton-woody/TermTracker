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


    public static int selectedTerm = -1;//This worked
    public static int greatestTerm = 0;

    public Term(int termID, int userID, String title, String start, String end) {
        this.termID = termID;
        this.userID = userID;
        this.title = title;
        this.start = start;
        this.end = end;

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

}

// Going to try to simulate @ForeignKey() through coded constraints and flags.
// May not be able to query without @ForeignKey(), need to test.
