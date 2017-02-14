package todo.mobile.com.todoapp.listeners;

import todo.mobile.com.todoapp.model.Task;

/**
 * Created by mabisrror on 2/4/17.
 */

public interface OnTaskListener {
    void navigateTaskDetail(Task task);
    void navigateBackToHome();
    void navigateNewTask();
    void navigateProfile();
}
