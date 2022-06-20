package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

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
    public static SearchView searchView;

    public static int idU;
    public static String nameU;
    public static String passwordU;
    public static Boolean adminU;
    public static String lastLoginU;
    public static Boolean enabledU;
    public static Boolean searchBoolean=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdminAdapter.normal=true;
        User.targetUserID = 0;
        setContentView(R.layout.activity_admin);
        editUserName=findViewById(R.id.userName);
        editPassword=findViewById(R.id.userPassword);
        spinnerEnable=findViewById(R.id.enabledStatus);
        spinnerSearch=findViewById(R.id.searchSpinner);
        spinnerType=findViewById(R.id.userType);
        searchView=findViewById(R.id.search);

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
                AdminAdapter.normal=true;
                if(User.targetUserID==0){
                    /*
                    if (spinnerEnable.getSelectedItemPosition()==1){
                        User.targetEnabled=true;
                    }else{
                        User.targetEnabled=false;
                    }
                    */


                    if (spinnerType.getSelectedItemPosition()==1){
                        User.targetAdmin=true;
                    }else{
                        User.targetAdmin=false;
                    }

                    User.targetName=editUserName.getText().toString();
                    User.targetPassword=editPassword.getText().toString();
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
                    if (User.targetPassword.equals("")){
                        User.targetEnabled=false;
                        Context context = getApplicationContext();
                        CharSequence text = "Account disabled until password is assigned.";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }else{
                        if (spinnerEnable.getSelectedItemPosition()==1){
                            User.targetEnabled=true;
                        }else{
                            User.targetEnabled=false;
                        }
                    }

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
                    /*
                    if (spinnerEnable.getSelectedItemPosition()==1){
                        User.targetEnabled=true;
                    }else{
                        User.targetEnabled=false;
                    }
                    */

                    if (spinnerType.getSelectedItemPosition()==1){
                        User.targetAdmin=true;
                    }else{
                        User.targetAdmin=false;
                    }

                    User.targetName=editUserName.getText().toString();
                    User.targetPassword=editPassword.getText().toString();
                    if (User.targetPassword.equals("")){
                        User.targetEnabled=false;
                        Context context = getApplicationContext();
                        CharSequence text = "Account disabled until password is assigned.";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }else{
                        if (spinnerEnable.getSelectedItemPosition()==1){
                            User.targetEnabled=true;
                        }else{
                            User.targetEnabled=false;
                        }
                    }
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
                AdminAdapter.normal=true;
                if(spinnerSearch.getSelectedItemPosition()==0){
                    String searchString=searchView.getQuery().toString();
                    repository=new Repository(getApplication());
                    List<User> filteredUsers=repository.searchName("%"+searchString+"%");
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
                    final AdminAdapter adminAdapter=new AdminAdapter(this);
                    recyclerView.setAdapter(adminAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adminAdapter.setUsers(filteredUsers);
                    return true;
                }else{
                    repository=new Repository(getApplication());
                    List<User> filteredUsers;
                    if(searchView.getQuery().toString().equals("enabled")||searchView.getQuery().toString().equals("Enabled")){
                        filteredUsers=repository.searchEnabled(true);
                        RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
                        final AdminAdapter adminAdapter=new AdminAdapter(this);
                        recyclerView.setAdapter(adminAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        adminAdapter.setUsers(filteredUsers);
                        return true;
                    }else if(searchView.getQuery().toString().equals("disabled")||searchView.getQuery().toString().equals("Disabled")){
                        filteredUsers=repository.searchEnabled(false);
                        RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
                        final AdminAdapter adminAdapter=new AdminAdapter(this);
                        recyclerView.setAdapter(adminAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        adminAdapter.setUsers(filteredUsers);
                        return true;
                    }

                }

            case R.id.adminClear:
                AdminAdapter.normal=true;
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

                repository=new Repository(getApplication());
                List<User> allUsers=repository.getAllUsers();
                RecyclerView recyclerView=findViewById(R.id.recyclerview_admin);
                final AdminAdapter adminAdapter=new AdminAdapter(this);
                recyclerView.setAdapter(adminAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adminAdapter.setUsers(allUsers);
                User.targetUserID=0;
                return true;


            case R.id.adminReport:
                AdminAdapter.normal=false;
                repository=new Repository(getApplication());
                List<User> reportUsers;
                reportUsers=repository.getReport();
                recyclerView=findViewById(R.id.recyclerview_admin);
                final AdminAdapter adminAdapter2=new AdminAdapter(this);
                recyclerView.setAdapter(adminAdapter2);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adminAdapter2.setUsers(reportUsers);
                return true;

            case R.id.adminLogout:
                User.activeUserID = 0;
                User.activeName = "Something Went Wrong";
                User.activePassword = "ThisIsAnUnsetPassword!Q@W#E$R";//Only set this to prevent an unintended error that allows login without input of password.
                User.activeAdmin = false;
                User.activeLastLogin = "Something Went Wrong";
                User.activeEnabled = false;
                Intent intent=new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(intent);
                this.finish();
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