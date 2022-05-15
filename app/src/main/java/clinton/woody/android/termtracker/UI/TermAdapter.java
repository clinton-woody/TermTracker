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

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termItemView;
        private TermViewHolder(View itemView){
            super(itemView);
            termItemView=itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Term current=mTerms.get(position);
                    Intent intent = new Intent(context, CourseActivity.class);//use to be TermList.class
                    intent.putExtra("id", current.getTermID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra( "start", current.getStart());
                    intent.putExtra( "end", current.getEnd());
                    intent.putExtra( "status", current.getStatus());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public TermAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_item,parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null){
            Term current=mTerms.get(position);
            String title=current.getTitle();
            holder.termItemView.setText(title);
        }
        else{
            holder.termItemView.setText("No Term Title");
        }

    }
    public void setTerms(List<Term> terms) {
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms!=null){
            return mTerms.size();
        }
        else
            return 0;

    }
}
