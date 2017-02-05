package todo.mobile.com.todoapp.home;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.app.TodoApplication;
import todo.mobile.com.todoapp.details.fragment.TaskDetailFragment;
import todo.mobile.com.todoapp.details.fragment.TaskEditFragment;
import todo.mobile.com.todoapp.home.fragment.HomeFragment;
import todo.mobile.com.todoapp.listeners.OnTaskListener;
import todo.mobile.com.todoapp.model.Task;

public class TaskContainerActivity extends AppCompatActivity implements OnTaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_container);
        home();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_edit_task:
                editTask();
                return true;
            case R.id.action_share_link:
                shareTask();
                return true;
            case R.id.action_delete:
                deleteTask();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editTask() {

    }

    private void deleteTask() {

    }

    private void shareTask() {

    }

    private void detailTask() {
        TaskDetailFragment detail = new TaskDetailFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detail)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void home() {
        HomeFragment home = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, home)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public void selectedItemTask(Task task) {
        TaskDetailFragment detail = new TaskDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TASK_DETAIL", task);
        detail.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detail)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
}
