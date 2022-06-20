package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.Entity.User;
import clinton.woody.android.termtracker.R;

public class AdminActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository;
    public static Spinner spinnerEnable;
    public static Spinner spinnerType;
    public static Spinner spinnerSearch;
    public static EditText editUserName;
    public static EditText editPassword;
    public static SearchView search;

    public static int idU;
    public static String nameU;
    public static String passwordU;
    public static Boolean adminU;
    public static String lastLoginU;
    public static Boolean enabledU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User.targetUserID = 0;
        setContentView(R.layout.activity_admin);
        editUserName=findViewById(R.id.userName);
        editPassword=findViewById(R.id.userPassword);
        spinnerEnable=findViewById(R.id.enabledStatus);
        spinnerSearch=findViewById(R.id.searchSpinner);
        spinnerType=findViewById(R.id.userType);
        search=findViewById(R.id.search);

        repository=new Repository(getApplication());
        List<User> allUsers= repository.getAllUsers();

        RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
        final AdminAdapter adminAdapter=new AdminAdapter(this);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adminAdapter.setUsers(allUsers);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.enable_list, android.R.layout.simple_spinner_item );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnable.setAdapter(adapter1);
        spinnerEnable.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.search_list, android.R.layout.simple_spinner_item );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearch.setAdapter(adapter2);
        spinnerSearch.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.admin_list, android.R.layout.simple_spinner_item );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter3);
        spinnerType.setOnItemSelectedListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.adminUpdate:
                if(User.targetUserID==0){
                    if (spinnerEnable.getSelectedItemPosition()==1){
                        User.targetEnabled=true;
                    }else{
                        User.targetEnabled=false;
                    }

                    if (spinnerType.getSelectedItemPosition()==1){
                        User.targetAdmin=true;
                    }else{
                        User.targetAdmin=false;
                    }

                    User.targetName=editUserName.getText().toString();
                    User.targetPassword=editUserName.getText().toString();
                    User user=new User(User.targetUserID, User.targetName, User.targetPassword, User.targetAdmin, User.targetLastLogin, User.targetEnabled);
                    repository.insert(user);
                    User.targetName="";
                    User.targetPassword="";
                    User.targetAdmin=false;
                    User.targetLastLogin="Never Logged In";
                    User.targetEnabled=false;
                    spinnerEnable.setSelection(0);
                    spinnerType.setSelection(0);
                    editUserName.setText(User.targetName);
                    editPassword.setText(User.targetPassword);

                    repository=new Repository(getApplication());
                    List<User> allUsers=repository.getAllUsers();
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
                    final AdminAdapter adminAdapter=new AdminAdapter(this);
                    recyclerView.setAdapter(adminAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adminAdapter.setUsers(allUsers);
                    User.targetUserID=0;
                    return true;

                }else{
                    if (spinnerEnable.getSelectedItemPosition()==1){
                        User.targetEnabled=true;
                    }else{
                        User.targetEnabled=false;
                    }

                    if (spinnerType.getSelectedItemPosition()==1){
                        User.targetAdmin=true;
                    }else{
                        User.targetAdmin=false;
                    }

                    User.targetName=editUserName.getText().toString();
                    User.targetPassword=editUserName.getText().toString();
                    User user=new User(User.targetUserID, User.targetName, User.targetPassword, User.targetAdmin, User.targetLastLogin, User.targetEnabled);
                    repository.update(user);
                    User.targetName="";
                    User.targetPassword="";
                    User.targetAdmin=false;
                    User.targetLastLogin="Never Logged In";
                    User.targetEnabled=false;
                    spinnerEnable.setSelection(0);
                    spinnerType.setSelection(0);
                    editUserName.setText(User.targetName);
                    editPassword.setText(User.targetPassword);

                    repository=new Repository(getApplication());
                    List<User> allUsers=repository.getAllUsers();
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
                    final AdminAdapter adminAdapter=new AdminAdapter(this);
                    recyclerView.setAdapter(adminAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adminAdapter.setUsers(allUsers);
                    User.targetUserID=0;
                    return true;
                }

            case R.id.adminSearch:
                return true;

            case R.id.adminClear:

                User.targetUserID=0;
                User.targetName="";
                User.targetPassword="";
                User.targetAdmin=false;
                User.targetLastLogin="Never Logged In";
                User.targetEnabled=false;
                spinnerEnable.setSelection(0);
                spinnerType.setSelection(0);
                editUserName.setText(User.targetName);
                editPassword.setText(User.targetPassword);
                return true;

            case R.id.adminReport:
                return true;

            case R.id.adminLogout:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
//Scope: Capstone only
//Parent: LoginActivity (Capstone)
//Child: None