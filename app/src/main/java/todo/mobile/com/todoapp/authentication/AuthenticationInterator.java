package todo.mobile.com.todoapp.authentication;

import com.facebook.AccessToken;

/**
 * Created by mabisrror on 1/12/17.
 */

public interface AuthenticationInterator {

    public void doSignOnFb(AccessToken accessToken);
    public void doOnStartAuth();
    public void doOnStopAuth();
    public void doAuthListener();
}
