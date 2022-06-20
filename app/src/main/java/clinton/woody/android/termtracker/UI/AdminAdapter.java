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
import clinton.woody.android.termtracker.Entity.User;
import clinton.woody.android.termtracker.R;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {
    public static Boolean normal = true;

    class AdminViewHolder extends RecyclerView.ViewHolder{
        private final TextView adminItemView1;
        private final TextView adminItemView2;

        private AdminViewHolder(View itemView){

            super(itemView);
            adminItemView1=itemView.findViewById(R.id.textViewAdmin1);
            adminItemView2=itemView.findViewById(R.id.textViewAdmin2);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final User current=mUsers.get(position);

                    User.targetUserID=current.getUserID();
                    User.targetName=current.getUserName();
                    User.targetPassword=current.getPassword();
                    User.targetAdmin=current.getAdmin();
                    User.targetLastLogin=current.getLastLogin();
                    User.targetEnabled=current.getEnabled();

                    AdminActivity.idU=current.getUserID();
                    AdminActivity.nameU=current.getUserName();
                    AdminActivity.passwordU=current.getPassword();
                    AdminActivity.adminU=current.getAdmin();
                    AdminActivity.lastLoginU=current.getLastLogin();
                    AdminActivity.enabledU=current.getEnabled();

                    if (User.targetEnabled == true) {
                        AdminActivity.spinnerEnable.setSelection(1);
                    }else{
                        AdminActivity.spinnerEnable.setSelection(0);
                    }
                    if (User.targetAdmin == true) {
                        AdminActivity.spinnerType.setSelection(1);
                    }else{
                        AdminActivity.spinnerType.setSelection(0);
                    }
                    AdminActivity.editUserName.setText(User.targetName);
                    AdminActivity.editPassword.setText("Password Hidden");

                }
            });
        }
    }

    private List<User> mUsers;
    private final Context context;
    private final LayoutInflater mInflater;
    public AdminAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AdminAdapter.AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.admin_item,parent, false);
        return new AdminViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.AdminViewHolder holder, int position) {

        if(normal==true){
            if(mUsers!=null){
                User current=mUsers.get(position);
                String userName=current.getUserName();
                holder.adminItemView1.setText(userName);
                Boolean enabled=current.getEnabled();
                if (enabled == true){
                    holder.adminItemView2.setText("Enabled");
                }else{
                    holder.adminItemView2.setText("Disabled");
                }
            }else{
                holder.adminItemView1.setText("No User");
                holder.adminItemView2.setText("No User");
            }
        }else if(normal==false){
            if(mUsers!=null){
                User current=mUsers.get(position);
                String userName=current.getUserName();
                holder.adminItemView1.setText(userName);
                String lastLogin=current.getLastLogin();
                holder.adminItemView2.setText(lastLogin);
             }else{
                holder.adminItemView1.setText("No User");
                holder.adminItemView2.setText("No User");
            }

        }

    }

    public void setUsers(List<User> users) {
        mUsers=users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}