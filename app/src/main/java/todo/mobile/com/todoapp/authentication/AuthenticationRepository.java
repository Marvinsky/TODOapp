package todo.mobile.com.todoapp.authentication;

import com.facebook.AccessToken;

/**
 * Created by mabisrror on 1/12/17.
 */

public interface AuthenticationRepository {

    public void signInFb(AccessToken accessToken);
    public void onStartAuthentication();
    public void onStopAuthentication();
    public void onAuthStateListener();

}
