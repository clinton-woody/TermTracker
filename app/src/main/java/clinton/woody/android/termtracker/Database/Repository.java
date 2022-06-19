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
    private User mUser;


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

    //Term
    public List<Term>getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms=mTermDAO.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.insert(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Course
    public List<Course>getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.insert(course);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.update(course);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(course);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Assessment
    public List<Assessment>getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessment);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessment);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //User
    public User getUser(String search){
        databaseExecutor.execute(()->{
            mUser=mUserDAO.getUser(search);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mUser;
    }

    public List<User>getAllUsers(){
        databaseExecutor.execute(()->{
            mAllUsers=mUserDAO.getAllUsers();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUsers;
    }

    public void insert(User user){
        databaseExecutor.execute(()->{
            mUserDAO.insert(user);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(User user){
        databaseExecutor.execute(()->{
            mUserDAO.update(user);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(User user){
        databaseExecutor.execute(()->{
            mUserDAO.delete(user);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Instructor
    public List<Instructor>getAllInstructors(){
        databaseExecutor.execute(()->{
            mAllInstructors=mInstructorDAO.getAllInstructors();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllInstructors;
    }

    public void insert(Instructor instructor){
        databaseExecutor.execute(()->{
            mInstructorDAO.insert(instructor);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Instructor instructor){
        databaseExecutor.execute(()->{
            mInstructorDAO.update(instructor);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Instructor instructor){
        databaseExecutor.execute(()->{
            mInstructorDAO.delete(instructor);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
