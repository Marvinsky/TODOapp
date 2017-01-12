package todo.mobile.com.todoapp.authentication;


import android.support.annotation.NonNull;
import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mabisrror on 1/12/17.
 */

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    AuthenticationTaskListener authenticationTaskListener;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public AuthenticationRepositoryImpl(AuthenticationTaskListener listener) {
        authenticationTaskListener = listener;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInFb(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = task.getResult().getUser();
                        if (task.isSuccessful()) {
                            authenticationTaskListener.authFbSuccess();
                        } else {
                            authenticationTaskListener.authFbError(task.getException().toString());
                        }

                    }
                });
    }

    @Override
    public void onStartAuthentication() {
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStopAuthentication() {
        if(authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onAuthStateListener() {
        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {

                }
            }
        };
    }
}
