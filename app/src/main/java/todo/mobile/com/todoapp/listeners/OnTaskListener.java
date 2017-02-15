package todo.mobile.com.todoapp.listeners;

import android.app.Activity;
import android.content.Context;

import todo.mobile.com.todoapp.model.Task;

/**
 * Created by mabisrror on 2/4/17.
 */

public interface OnTaskListener {
    void navigateTaskDetail(Task task);
    void navigateBackToHome();
    void navigateNewTask();
    void navigateProfile();
    void navigateMap(Activity activity);
}
