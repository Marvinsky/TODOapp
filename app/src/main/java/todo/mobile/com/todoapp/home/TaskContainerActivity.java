package todo.mobile.com.todoapp.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.details.fragment.TaskDetailFragment;
import todo.mobile.com.todoapp.geolocalization.TaskMapActivity;
import todo.mobile.com.todoapp.home.fragment.HomeFragment;
import todo.mobile.com.todoapp.listeners.OnTaskListener;
import todo.mobile.com.todoapp.model.Task;
import todo.mobile.com.todoapp.newtask.fragment.TaskNewFragment;
import todo.mobile.com.todoapp.profile.fragment.ProfileFragment;

public class TaskContainerActivity extends AppCompatActivity implements OnTaskListener {

    private TaskDetailFragment detailFragment;
    private HomeFragment homeFragment;
    private TaskNewFragment newFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_container);
        app();
        home();
    }

    private void app() {
        homeFragment = new HomeFragment();
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
    public void navigateTaskDetail(Task task) {
        detailFragment = new TaskDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TASK_DETAIL", task);
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateBackToHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateNewTask() {
        newFragment = new TaskNewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, newFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateProfile() {
        profileFragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateMap(Activity activity) {
        Intent intent = new Intent(activity, TaskMapActivity.class);
        startActivity(intent);
    }
}
