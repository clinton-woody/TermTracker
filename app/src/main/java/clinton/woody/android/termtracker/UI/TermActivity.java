package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import clinton.woody.android.termtracker.DAO.TermDAO;
import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Course;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.Entity.User;
import clinton.woody.android.termtracker.R;

public class TermActivity extends AppCompatActivity {
    private Repository repository;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        userID = User.activeUserID;//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());

        List<Term> filteredTerms=new ArrayList<>();
        for (Term t:repository.getAllTerms()){
            if(t.getUserID()==userID)filteredTerms.add(t);
        }

        RecyclerView recyclerView=findViewById(R.id.recyclerview_term);
        final TermAdapter termAdapter=new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(filteredTerms);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.detailedTerm:
                Intent intent=new Intent(TermActivity.this,DetailedTermActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void toCourse(View view) {
        Intent intent=new Intent(TermActivity.this,CourseActivity.class);
        startActivity(intent);
    }

    public void toDetailedTerm(View view) {
        Intent intent=new Intent(TermActivity.this,DetailedTermActivity.class);
        startActivity(intent);
    }
}
//Scope: Mobile App Dev
//Parent: MainActivity, LoginActivity
//Child: DetailedTermActivity, CourseActivity