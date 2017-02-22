package todo.mobile.com.todoapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.home.TaskContainerActivity;
import todo.mobile.com.todoapp.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private final static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        etUsername = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btSignIn = (Button) findViewById(R.id.btSignIn);

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    login();
                    return true;
                }
                return false;
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToSignInActivity();
            }
        });
    }

    public void login(){
        if (isFormValidated()){
            startActivity(new Intent(LoginActivity.this, TaskContainerActivity.class));
        }
    }

    public void goToSignInActivity(){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public boolean isFormValidated(){
        if (!isValidEmail()){
            Toast.makeText(this, "Invalid format email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidPassword()){
            Toast.makeText(this, "Invalid format password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidPassword() {
        return !TextUtils.isEmpty(etPassword.getText())
                && etPassword.getText().toString().matches(PASSWORD_REGEX);
    }

    public final boolean isValidEmail() {
        return !TextUtils.isEmpty(etUsername.getText())
                && android.util.Patterns.EMAIL_ADDRESS.matcher(etUsername.getText()).matches();
    }
}
