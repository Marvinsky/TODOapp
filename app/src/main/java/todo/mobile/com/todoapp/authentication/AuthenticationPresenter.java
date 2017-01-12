package todo.mobile.com.todoapp.authentication;

import com.facebook.AccessToken;

/**
 * Created by mabisrror on 1/12/17.
 */

public interface AuthenticationPresenter {
    public void onDestroy();
    public void validateAuthenticationFb(AccessToken accessToken);
    public void onStopAuthentication();
    public void onStartAuthentication();
    public void onAuthStateListener();
}
