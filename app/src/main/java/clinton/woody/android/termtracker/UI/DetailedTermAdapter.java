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

import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class DetailedTermAdapter extends RecyclerView.Adapter<DetailedTermAdapter.DetailedTermViewHolder> {


    class DetailedTermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termItemView1;
        private final TextView termItemView2;
        private DetailedTermViewHolder(View itemView){

            super(itemView);
            termItemView1=itemView.findViewById(R.id.textViewTerm1);
            termItemView2=itemView.findViewById(R.id.textViewTerm2);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Term current=mTerms.get(position);
                    Term.selectedTerm=current.getTermID();
//                    Intent intent = new Intent(context, CourseActivity.class);//use to be TermList.class
//                    intent.putExtra("id", current.getTermID());
//                    intent.putExtra("title", current.getTitle());
//                    intent.putExtra("start", current.getStart());
//                    intent.putExtra("end", current.getEnd());
//                    context.startActivity(intent);

                }
            });
        }
    }
    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public DetailedTermAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public DetailedTermAdapter.DetailedTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_item,parent, false);
        return new DetailedTermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedTermAdapter.DetailedTermViewHolder holder, int position) {
        if(mTerms!=null){
            Term current=mTerms.get(position);
            String title=current.getTitle();
            holder.termItemView1.setText(title);
            String end=current.getEnd();
            holder.termItemView2.setText(end);
        }
        else{
            holder.termItemView1.setText("No Term Title");
            holder.termItemView2.setText("No Term End Date");
        }

    }
    public void setTerms(List<Term> terms) {
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }

}