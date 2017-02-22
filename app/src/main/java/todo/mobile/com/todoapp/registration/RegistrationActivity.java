package todo.mobile.com.todoapp.registration;

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
import todo.mobile.com.todoapp.login.LoginActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etPasswordRetyped;
    private Button btSignIn, btSignOn;
    private final static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    public void init(){
        etUsername = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordRetyped = (EditText) findViewById(R.id.etPasswordRetyped);
        btSignIn = (Button) findViewById(R.id.btSignIn);
        btSignOn = (Button) findViewById(R.id.btSignOn);

        etPasswordRetyped.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    register();
                    return true;
                }
                return false;
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                register();
            }
        });

        btSignOn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToSignOnActivity();
            }
        });
    }

    public void register(){
        if (isFormValidated()){
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        }
    }

    public void goToSignOnActivity(){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    public boolean isFormValidated(){
        if (!isValidUsername()){
            Toast.makeText(this, "Username not valid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidPassword()){
            Toast.makeText(this, "Password not valid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isValidUsername(){
        return !TextUtils.isEmpty(etUsername.getText()) && android.util.Patterns.EMAIL_ADDRESS.matcher(etUsername.getText()).matches();
    }

    public boolean isValidPassword(){
        return !TextUtils.isEmpty(etPassword.getText()) && !TextUtils.isEmpty(etPasswordRetyped.getText())
                && etPassword.getText().toString().matches(PASSWORD_REGEX)
                && etPasswordRetyped.getText().toString().matches(PASSWORD_REGEX)
                && isSamePassword();
    }

    public boolean isSamePassword(){
        if (etPassword.getText().toString().equals(etPasswordRetyped.getText().toString())){
            return true;
        }
        return false;
    }
}
