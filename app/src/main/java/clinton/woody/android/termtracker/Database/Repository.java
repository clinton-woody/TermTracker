package clinton.woody.android.termtracker.Database;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class Repository {
    private AssessmentDAO mAssessmentDAO;
    private CourseDAO mCourseDAO;
    private InstructorDAO mInstructorDAO;
    private TermDAO mTermDAO;
    private UserDAO mUserDAO;
    private List<Assessment> mAllAssessments;
    private List<Course> mAllCourses;
    private List<Instructor> mAllInstructors;
    private List<Term> mAllTerms;
    private List<User> mAllUsers;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDatabaseBuilder db=TermDatabaseBuilder.getDatabase(application);
        mAssessmentDAO=db.assessmentDAO();
        mCourseDAO=db.courseDAO();
        mInstructorDAO=db.instructorDAO();
        mTermDAO=db.termDAO();
        mUserDAO=db.userDAO();
    }

}
