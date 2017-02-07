package todo.mobile.com.todoapp.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

    private TaskDetailFragment detailFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_container);
        app();
        home();
    }

    private void app() {
        homeFragment = new HomeFragment();
        detailFragment = new TaskDetailFragment();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveItemTask();
    }

    private void editTask() {

    }

    private void deleteTask() {

    }

    private void shareTask() {

    }


    private void home() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        menu.clear();
        return true;
    }

    @Override
    public void selectedItemTask(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("TASK_DETAIL", task);
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void saveItemTask() {
        detailFragment.saveTask();
    }
}
