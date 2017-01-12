package todo.mobile.com.todoapp.authentication;

import com.facebook.AccessToken;

/**
 * Created by mabisrror on 1/12/17.
 */

public class AuthenticationInteratorImpl implements AuthenticationInterator {

    AuthenticationRepository authenticationRepository;


    public AuthenticationInteratorImpl(AuthenticationTaskListener listener) {
        this.authenticationRepository = new AuthenticationRepositoryImpl(listener);
    }

    @Override
    public void doSignOnFb(AccessToken accessToken) {
        authenticationRepository.signInFb(accessToken);
    }

    @Override
    public void doOnStartAuth() {
        authenticationRepository.onStartAuthentication();
    }

    @Override
    public void doOnStopAuth() {
        authenticationRepository.onStopAuthentication();
    }

    @Override
    public void doAuthListener() {
        authenticationRepository.onAuthStateListener();
    }
}
