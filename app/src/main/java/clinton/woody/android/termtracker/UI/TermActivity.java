package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import clinton.woody.android.termtracker.DAO.TermDAO;
import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class TermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        RecyclerView recyclerView=findViewById(R.id.recyclerview_term);
        Repository repo=new Repository(getApplication());
        List<Term> terms= repo.getAllTerms();
        final TermAdapter adapter=new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
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