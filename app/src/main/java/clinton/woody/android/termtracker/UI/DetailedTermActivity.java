package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;

public class DetailedTermActivity extends AppCompatActivity {
    private Repository repository;
    public static Boolean active = false;
    int termID;
    int userID;
    String title;
    String start;
    String end;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = true;
        setContentView(R.layout.activity_detailed_term);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());
        List<Term> allTerms= repository.getAllTerms();
        RecyclerView recyclerView=findViewById(R.id.recyclerview_term);
        final TermAdapter termAdapter=new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_term, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                active = false;
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
//Scope: Mobile App Dev
//Parent: TermActivity
//Child: None