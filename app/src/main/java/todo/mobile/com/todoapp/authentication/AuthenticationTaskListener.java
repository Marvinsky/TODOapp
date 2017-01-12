package todo.mobile.com.todoapp.authentication;

/**
 * Created by mabisrror on 1/12/17.
 */

public interface AuthenticationTaskListener {

    public void authFbSuccess();
    public void authFbError(String error);

}
