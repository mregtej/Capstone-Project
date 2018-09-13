package com.udacity.mregtej.mymealplanner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity {

    @BindView(R.id.et_login_screen_email)
    EditText etLoginScreenEmail;
    @BindView(R.id.et_login_screen_password)
    EditText etLoginScreenPassword;
    @BindView(R.id.bt_login_screen_mail_login)
    Button btLoginScreenLogin;
    @BindView(R.id.bt_login_screen_forgot_pass)
    Button btLoginForgotPassword;
    @BindView(R.id.bt_login_screen_sign_up)
    Button btLoginScreenRegister;
    @BindView(R.id.bt_login_screen_google_login)
    SignInButton btLoginScreenGoogleLogin;
    @BindView(R.id.pb_login_screen_progress)
    ProgressBar pbLoginScreenProgress;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_login_screen_mail_login, R.id.bt_login_screen_forgot_pass, R.id.bt_login_screen_sign_up, R.id.bt_login_screen_google_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login_screen_mail_login:

                if(etLoginScreenEmail.getError() != null) {
                    etLoginScreenEmail.setError(null);
                }
                if(etLoginScreenPassword.getError() != null) {
                    etLoginScreenPassword.setError(null);
                }

                String email = etLoginScreenEmail.getText().toString();
                final String password = etLoginScreenPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etLoginScreenEmail.setError("Enter valid email address");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etLoginScreenPassword.setError("Enter password");
                    return;
                }

                pbLoginScreenProgress.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogInActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        pbLoginScreenProgress.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                etLoginScreenPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(LogInActivity.this, getString(R.string.auth_failed),
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
                break;
            case R.id.bt_login_screen_forgot_pass:
                // startActivity(new Intent(LogInActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.bt_login_screen_sign_up:
                // startActivity(new Intent(LogInActivity.this, SignupActivity.class));
                break;
            case R.id.bt_login_screen_google_login:
                break;
        }
    }

}
