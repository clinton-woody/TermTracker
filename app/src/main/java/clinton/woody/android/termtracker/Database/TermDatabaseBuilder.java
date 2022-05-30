package clinton.woody.android.termtracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import clinton.woody.android.termtracker.DAO.AssessmentDAO;
import clinton.woody.android.termtracker.DAO.CourseDAO;
import clinton.woody.android.termtracker.DAO.InstructorDAO;
import clinton.woody.android.termtracker.DAO.TermDAO;
import clinton.woody.android.termtracker.DAO.UserDAO;
import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Instructor;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.Entity.User;

@Database(entities={Assessment.class, Course.class, Instructor.class, Term.class, User.class}, version=3, exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract TermDAO termDAO();
    public abstract UserDAO userDAO();

    private static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null){
        synchronized (TermDatabaseBuilder.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermDatabaseBuilder.class, "myTermDatabase")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
    }
    return INSTANCE;
    }
}
