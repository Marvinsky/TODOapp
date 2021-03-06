package todo.mobile.com.todoapp.authentication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.authentication.AuthenticationPresenter;
import todo.mobile.com.todoapp.authentication.AuthenticationPresenterImpl;
import todo.mobile.com.todoapp.home.TaskContainerActivity;
import todo.mobile.com.todoapp.login.LoginActivity;
import todo.mobile.com.todoapp.utils.Utils;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationView {

    LoginButton btn_login_fb;
    CallbackManager callbackManager;
    AuthenticationPresenter presenter;
    Button btn_login_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());


        presenter = new AuthenticationPresenterImpl(this);
        setContentView(R.layout.activity_authentication);
        callbackManager = CallbackManager.Factory.create();
        setAuthStateListener();
        init();
    }

    public void setAuthStateListener() {
        presenter.onAuthStateListener();
    }

    private void init() {
        btn_login_fb = (LoginButton)findViewById(R.id.btn_login_fb);
        btn_login_email = (Button) findViewById(R.id.btn_login_email);
        btn_login_fb.setReadPermissions("email");
        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                signInWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        btn_login_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmailLogin();
            }
        });
    }

    public void signInWithFacebook(AccessToken accessToken) {
        presenter.validateAuthenticationFb(accessToken);
    }

    public void goToEmailLogin(){
        startActivity(new Intent(AuthenticationActivity.this, LoginActivity.class));
    }


    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStopAuthentication();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStartAuthentication();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean isOnline() {
        Utils utils = new Utils(this);
        return  utils.isOnline();
    }

    @Override
    public void navigateToHomeScreen() {
        Intent intent = new Intent(AuthenticationActivity.this, TaskContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void authenticationFbError(String error) {
        String errorMsg = String.format(getResources().getString(R.string.fb_authentication_failed), error);
        Toast.makeText(AuthenticationActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
