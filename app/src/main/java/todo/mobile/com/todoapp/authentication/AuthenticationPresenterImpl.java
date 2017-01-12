package todo.mobile.com.todoapp.authentication;

import com.facebook.AccessToken;

import todo.mobile.com.todoapp.authentication.ui.AuthenticationView;

/**
 * Created by mabisrror on 1/12/17.
 */

public class AuthenticationPresenterImpl implements AuthenticationPresenter, AuthenticationTaskListener {

    AuthenticationView authenticationView;
    AuthenticationInterator authenticationInterator;



    public AuthenticationPresenterImpl(AuthenticationView authenticationView) {
        this.authenticationView = authenticationView;
        authenticationInterator = new AuthenticationInteratorImpl(this);
    }

    @Override
    public void onDestroy() {
        authenticationView = null;
    }

    @Override
    public void validateAuthenticationFb(AccessToken accessToken) {
        if (authenticationView != null) {
            //TODO more ui validation
        }
        authenticationInterator.doSignOnFb(accessToken);
    }

    @Override
    public void onStopAuthentication() {
        authenticationInterator.doOnStopAuth();
    }

    @Override
    public void onStartAuthentication() {
        authenticationInterator.doOnStartAuth();
    }

    @Override
    public void onAuthStateListener() {
        authenticationInterator.doAuthListener();
    }

    @Override
    public void authFbSuccess() {
        if (authenticationView != null) {
            authenticationView.navigateToHomeScreen();
        }
    }

    @Override
    public void authFbError(String error) {
        authenticationView.authenticationFbError(error);
    }
}
