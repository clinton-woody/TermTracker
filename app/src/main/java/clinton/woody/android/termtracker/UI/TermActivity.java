package clinton.woody.android.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import clinton.woody.android.termtracker.R;

public class TermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
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

    public void toDetailedCourse(View view) {
        Intent intent=new Intent(TermActivity.this,DetailedTermActivity.class);
        startActivity(intent);
    }
}
//Scope: Mobile App Dev
//Parent: MainActivity, LoginActivity
//Child: DetailedTermActivity, CourseActivity