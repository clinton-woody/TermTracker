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

public class DetailedAssessmentAdapter extends RecyclerView.Adapter<DetailedAssessmentAdapter.DetailedAssessmentViewHolder> {


    class DetailedAssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView1;
        private final TextView assessmentItemView2;
        private DetailedAssessmentViewHolder(View itemView){

            super(itemView);
            assessmentItemView1=itemView.findViewById(R.id.textViewAssessment1);
            assessmentItemView2=itemView.findViewById(R.id.textViewAssessment2);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Assessment.selectedAssessment = current.getAssessmentID();
                    DetailedAssessmentActivity.title=current.getTitle();
                    DetailedAssessmentActivity.editTitle.setText(DetailedAssessmentActivity.title);
                    DetailedAssessmentActivity.selectedTitle.setText(DetailedAssessmentActivity.title);
                    DetailedAssessmentActivity.startDate=current.getStart();
                    DetailedAssessmentActivity.editStart.setText(DetailedAssessmentActivity.startDate);
                    DetailedAssessmentActivity.selectedStart.setText(DetailedAssessmentActivity.startDate);
                    DetailedAssessmentActivity.endDate=current.getEnd();
                    DetailedAssessmentActivity.editEnd.setText(DetailedAssessmentActivity.endDate);
                    DetailedAssessmentActivity.selectedEnd.setText(DetailedAssessmentActivity.endDate);
                    DetailedAssessmentActivity.type=current.getType();
                    DetailedAssessmentActivity.selectedType.setText(DetailedAssessmentActivity.type);
                    if (DetailedAssessmentActivity.type.equals("Performance")){
                        DetailedAssessmentActivity.spinnerType.setSelection(1);
                    }else if(DetailedAssessmentActivity.type.equals("Objective")){
                        DetailedAssessmentActivity.spinnerType.setSelection(2);
                    }

                    /*
                        public static String title;
    public static String startDate;
    public static String endDate;
    public static int typeIndex;
                     */

                }
            });
        }
    }
    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;
    public DetailedAssessmentAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public DetailedAssessmentAdapter.DetailedAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assessment_item,parent, false);
        return new DetailedAssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedAssessmentAdapter.DetailedAssessmentViewHolder holder, int position) {
        if(mAssessments!=null){
            Assessment current=mAssessments.get(position);
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
    public void setAssessments(List<Assessment> assessments) {
        mAssessments=assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAssessments!=null){
            return mAssessments.size();
        }
        else
            return 0;

    }
}