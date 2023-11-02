package com.anstudio.travelblog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends Activity {

    private TextInputLayout textUsernameLayout;
    private TextInputLayout textPasswordInput;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        //
        textUsernameLayout = findViewById(R.id.textUsernameLayout);
        textPasswordInput = findViewById(R.id.textPasswordLayout);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        // Connecte Buttons And Inputs With Listeners
        loginButton.setOnClickListener(v -> onLoginClicked());
        textUsernameLayout.getEditText().addTextChangedListener(createTextWatcher(textUsernameLayout));
        textPasswordInput.getEditText().addTextChangedListener(createTextWatcher(textPasswordInput));

    }

    private void onLoginClicked(){
        String username = textUsernameLayout.getEditText().getText().toString();
        String password = textPasswordInput.getEditText().getText().toString();

        if(username.isEmpty()){
            textUsernameLayout.setError("Username must not be empty");
        } else if(password.isEmpty()){
            textPasswordInput.setError("Password must not be empty");
        } else if(!username.equals("admin") || !password.equals("admin")){
            showErrorDialog();
        } else {
            performLogin();
        }
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage("Username or password is not correct. Please try again.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private TextWatcher createTextWatcher(TextInputLayout textPasswordInput){
        return new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                // not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after){
                textPasswordInput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s){
                // not needed
            }
        };
    }

    private void performLogin(){
        // Show The Progress Bar
        loginButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Disable The Inputs
        textUsernameLayout.setEnabled(false);
        textPasswordInput.setEnabled(false);

        // Display MainActivity
        Handler handler = new Handler();
        handler.postDelayed(()-> {
            startMainActivity();
            finish();
        }, 2000);
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
