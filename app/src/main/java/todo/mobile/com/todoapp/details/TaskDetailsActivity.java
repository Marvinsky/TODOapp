package todo.mobile.com.todoapp.details;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.model.Task;

public class TaskDetailsActivity extends AppCompatActivity {

    ImageView iviMainImage;
    TextView tvTitle, tvCategory, tvColor, tvDate, tvDescription;
    Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        showToolbar(getResources().getString(R.string.toolbar_title_detail), true);
        task = (Task) getIntent().getExtras().getSerializable("TASK_DETAIL");
        ui();
    }

    private void ui() {
        iviMainImage = (ImageView)findViewById(R.id.iviTaskImage);
        tvTitle = (TextView)findViewById(R.id.tviTaskTitle);
        tvCategory = (TextView)findViewById(R.id.tviCategory);
        tvColor = (TextView)findViewById(R.id.tviColor);
        tvDate = (TextView)findViewById(R.id.tviDate);
        tvDescription = (TextView)findViewById(R.id.tviDescription);

        Picasso.with(getApplicationContext()).load(task.getImageUrl()).into(iviMainImage);
        tvTitle.setText(task.getTitle());
        tvCategory.setText(task.getCategory());
        tvColor.setText(task.getColor());
        tvDate.setText(task.getCreatedDate());
        tvDescription.setText(task.getContent());
    }


    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
