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
                    DetailedCourseActivity.title=current.getTitle();
                    DetailedCourseActivity.editTitle.setText(DetailedCourseActivity.title);
                    DetailedCourseActivity.startDate=current.getStart();
                    DetailedCourseActivity.editStart.setText(DetailedCourseActivity.startDate);
                    DetailedCourseActivity.endDate=current.getEnd();
                    DetailedCourseActivity.editEnd.setText(DetailedCourseActivity.endDate);
                    DetailedCourseActivity.optionalNote=current.getNote();
                    DetailedCourseActivity.editNote.setText(DetailedCourseActivity.optionalNote);
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
