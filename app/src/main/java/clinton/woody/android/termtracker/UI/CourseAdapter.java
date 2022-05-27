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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView1;
        private final TextView courseItemView2;
        private CourseViewHolder(View itemView){
            super(itemView);
            courseItemView1=itemView.findViewById(R.id.textViewCourse1);
            courseItemView2=itemView.findViewById(R.id.textViewCourse2);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Course current=mCourse.get(position);
                    Course.selectedCourse=current.getCourseID();
                    Intent intent = new Intent(context, AssessmentActivity.class);//use to be TermList.class
                    intent.putExtra("id", current.getCourseID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra( "start", current.getStart());
                    intent.putExtra( "end", current.getEnd());
                    intent.putExtra( "status", current.getStatus());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Course> mCourse;
    private final Context context;
    private final LayoutInflater mInflater;
    public CourseAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_item,parent, false);
        return new CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(mCourse!=null){
            Course current=mCourse.get(position);
            String title=current.getTitle();
            holder.courseItemView1.setText(title);
            String status=current.getStatus();
            holder.courseItemView2.setText(status);
        }
        else{
            holder.courseItemView1.setText("No Course Title");
            holder.courseItemView2.setText("No Term Status");
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
