package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import clinton.woody.android.termtracker.Database.Repository;
import clinton.woody.android.termtracker.Entity.Term;
import clinton.woody.android.termtracker.R;
import clinton.woody.android.termtracker.DAO.TermDAO;


public class DetailedTermActivity extends AppCompatActivity {
    private Repository repository;
    private Term maxTerm;
    public static Boolean active = false;
    private int termID;
    int userID;
    public static int id;
    public static String title;
    public static String start;
    public static String end;
    public static String updateTitle;
    public static String updateStart;
    public static String updateEnd;
    public static EditText editTitle;
    public static EditText editStart;
    public static EditText editEnd;
    public static TextView selectedTitle;
    public static TextView selectedStart;
    public static TextView selectedEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = true;
        Term.selectedTerm = 0;
        setContentView(R.layout.activity_detailed_term);
        editTitle=findViewById(R.id.termTitle);
        editStart=findViewById(R.id.termStart);
        editEnd=findViewById(R.id.termEnd);
        selectedTitle=findViewById(R.id.selectedTitle);
        selectedStart=findViewById(R.id.selectedStart);
        selectedEnd=findViewById(R.id.selectedEnd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//added, may not be needed
        repository=new Repository(getApplication());
        List<Term> allTerms= repository.getAllTerms();
        RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedTerm);
        final DetailedTermAdapter detailedTermAdapter=new DetailedTermAdapter(this);
        recyclerView.setAdapter(detailedTermAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedTermAdapter.setTerms(allTerms);
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
            case R.id.updateTerm:

                if (Term.selectedTerm == 0){
                    Term.selectedTitle=editTitle.getText().toString();
                    Term.selectedStart=editTitle.getText().toString();
                    Term.selectedEnd=editTitle.getText().toString();
                    Term current=new Term(Term.selectedTerm, 1, Term.selectedTitle, Term.selectedStart, Term.selectedEnd);
                    repository.insert(current);
                    DetailedTermActivity.title = null;
                    DetailedTermActivity.start = null;
                    DetailedTermActivity.end = null;
                    selectedTitle.setText(title);
                    editTitle.setText(title);
                    selectedStart.setText(start);
                    editStart.setText(start);
                    selectedEnd.setText(end);
                    editEnd.setText(end);
                    repository=new Repository(getApplication());
                    List<Term> allTerms=repository.getAllTerms();
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedTerm);
                    final DetailedTermAdapter detailedTermAdapter=new DetailedTermAdapter(this);
                    recyclerView.setAdapter(detailedTermAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    detailedTermAdapter.setTerms(allTerms);
                }else{
                    Term.selectedTitle=editTitle.getText().toString();
                    Term.selectedStart=editTitle.getText().toString();
                    Term.selectedEnd=editTitle.getText().toString();
                    Term current=new Term(Term.selectedTerm, 1, Term.selectedTitle, Term.selectedStart, Term.selectedEnd);
                    repository.update(current);
                    DetailedTermActivity.title = null;
                    DetailedTermActivity.start = null;
                    DetailedTermActivity.end = null;
                    selectedTitle.setText(title);
                    editTitle.setText(title);
                    selectedStart.setText(start);
                    editStart.setText(start);
                    selectedEnd.setText(end);
                    editEnd.setText(end);
                    repository=new Repository(getApplication());
                    List<Term> allTerms=repository.getAllTerms();
                    RecyclerView recyclerView=findViewById(R.id.recyclerview_detailedTerm);
                    final DetailedTermAdapter detailedTermAdapter=new DetailedTermAdapter(this);
                    recyclerView.setAdapter(detailedTermAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    detailedTermAdapter.setTerms(allTerms);
                    Term.selectedTerm = 0;
                }

                return true;



            case R.id.deleteTerm:
                return true;

            case R.id.clearTerm:
                Term.selectedTerm = 0;
                title = null;
                start = null;
                end = null;
                selectedTitle.setText(title);
                editTitle.setText(title);
                selectedStart.setText(start);
                editStart.setText(start);
                selectedEnd.setText(end);
                editEnd.setText(end);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
//Scope: Mobile App Dev
//Parent: TermActivity
//Child: None