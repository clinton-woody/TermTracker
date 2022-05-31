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

import clinton.woody.android.termtracker.Entity.Assessment;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {


    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView1;
        private final TextView assessmentItemView2;
        private AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView1=itemView.findViewById(R.id.textViewAssessment1);
            assessmentItemView2=itemView.findViewById(R.id.textViewAssessment2);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessment.get(position);
                    Assessment.selectedAssessment = current.getAssessmentID();
                    if (DetailedAssessmentActivity.active == false) {

                    }
                }
            });
        }
    }
    private List<Assessment> mAssessment;
    private final Context context;
    private final LayoutInflater mInflater;
    public AssessmentAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assessment_item,parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessment!=null){
            Assessment current=mAssessment.get(position);
            String title=current.getTitle();
            holder.assessmentItemView1.setText(title);
            String end=current.getEnd();
            holder.assessmentItemView2.setText(end);
        }
        else{
            holder.assessmentItemView1.setText("No Assessment Title");
            holder.assessmentItemView2.setText("No Assessment Status");
        }

    }
    public void setAssessment(List<Assessment> assessment) {
        mAssessment=assessment;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAssessment!=null){
            return mAssessment.size();
        }
        else
            return 0;

    }
}