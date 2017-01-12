package todo.mobile.com.todoapp.authentication.ui;

/**
 * Created by mabisrror on 1/12/17.
 */

public interface AuthenticationView {
    public boolean isOnline();
    public void navigateToHomeScreen();
    public void authenticationFbError(String error);
}
