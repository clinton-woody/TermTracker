package clinton.woody.android.termtracker.UI;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import clinton.woody.android.termtracker.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView;
        private CourseViewHolder(View itemView){
            super(itemView);
            courseItemView=itemView.findViewById(R.id.textView2);
        }
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
