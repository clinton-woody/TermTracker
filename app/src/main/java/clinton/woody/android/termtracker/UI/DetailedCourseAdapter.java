package clinton.woody.android.termtracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class DetailedCourseAdapter extends RecyclerView.Adapter<DetailedCourseAdapter.DetailedCourseViewHolder> {


    class DetailedCourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView1;
        private final TextView courseItemView2;
        private DetailedCourseViewHolder(View itemView){

            super(itemView);
            courseItemView1=itemView.findViewById(R.id.textViewCourse1);
            courseItemView2=itemView.findViewById(R.id.textViewCourse2);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Course current=mCourse.get(position);
                    Course.selectedCourse=current.getCourseID();
                    Course.selectedTitle=current.getTitle();
                    Course.selectedInstructor=current.getInstructorID();
                    Course.selectedNote=current.getNote();
                    Course.selectedStart=current.getStart();
                    Course.selectedEnd=current.getEnd();
                    Course.selectedStatus= current.getStatus();
                    DetailedCourseActivity.title=current.getTitle();
                    DetailedCourseActivity.editTitle.setText(DetailedCourseActivity.title);
                    DetailedCourseActivity.selectedTitle.setText(DetailedCourseActivity.title);
                    DetailedCourseActivity.startDate=current.getStart();
                    DetailedCourseActivity.editStart.setText(DetailedCourseActivity.startDate);
                    DetailedCourseActivity.selectedStart.setText(DetailedCourseActivity.startDate);
                    DetailedCourseActivity.endDate=current.getEnd();
                    DetailedCourseActivity.editEnd.setText(DetailedCourseActivity.endDate);
                    DetailedCourseActivity.selectedEnd.setText(DetailedCourseActivity.endDate);
                    DetailedCourseActivity.status=current.getStatus();
                    if (DetailedCourseActivity.status.equals("Plan to Take")){
                        DetailedCourseActivity.spinnerStatus.setSelection(1);
                    }else if (DetailedCourseActivity.status.equals("In Progress")){
                        DetailedCourseActivity.spinnerStatus.setSelection(2);
                    }else if (DetailedCourseActivity.status.equals("Completed")){
                        DetailedCourseActivity.spinnerStatus.setSelection(3);
                    }else if (DetailedCourseActivity.status.equals("Dropped")){
                        DetailedCourseActivity.spinnerStatus.setSelection(4);
                    }
                    DetailedCourseActivity.selectedStatus.setText(DetailedCourseActivity.status);
                    DetailedCourseActivity.instructorId=current.getInstructorID();//need method
                    if (DetailedCourseActivity.instructorId==1){
                        DetailedCourseActivity.name="Instructor1";
                        DetailedCourseActivity.phone="555-123-4567";
                        DetailedCourseActivity.email="instructor1@school.edu";
                        DetailedCourseActivity.spinnerInstructor.setSelection(1);
                    }else{
                        DetailedCourseActivity.name="Instructor2";
                        DetailedCourseActivity.phone="555-123-8901";
                        DetailedCourseActivity.email="instructor2@school.edu";
                        DetailedCourseActivity.spinnerInstructor.setSelection(2);
                    }
                    DetailedCourseActivity.selectedName.setText(DetailedCourseActivity.name);
                    DetailedCourseActivity.selectedPhone.setText(DetailedCourseActivity.phone);
                    DetailedCourseActivity.selectedEmail.setText(DetailedCourseActivity.email);
                    DetailedCourseActivity.optionalNote=current.getNote();
                    DetailedCourseActivity.editNote.setText(DetailedCourseActivity.optionalNote);
                    DetailedCourseActivity.selectedNote.setText(DetailedCourseActivity.optionalNote);
                }
            });
        }
    }
    private List<Course> mCourse;
    private final Context context;
    private final LayoutInflater mInflater;
    public DetailedCourseAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public DetailedCourseAdapter.DetailedCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_item,parent, false);
        return new DetailedCourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedCourseAdapter.DetailedCourseViewHolder holder, int position) {
        if(mCourse!=null){

            Course current=mCourse.get(position);
            String title=current.getTitle();
            holder.courseItemView1.setText(title);
            String end=current.getEnd();
            holder.courseItemView2.setText(end);
        }
        else{
            holder.courseItemView1.setText("No Course Title");
            holder.courseItemView2.setText("No Course End Date");
        }

    }
    public void setCourses(List<Course> course) {
        mCourse=course;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourse.size();
    }
}
