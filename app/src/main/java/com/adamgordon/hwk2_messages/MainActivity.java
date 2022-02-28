package com.adamgordon.hwk2_messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser theUser;
    private EditText email;
    private EditText pwEditText;
    private Button loginButton;
    private Intent intent;
    private String userName;
    private String pw;
    protected void login(String un,String pw){
        mAuth.signInWithEmailAndPassword(un,pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    theUser = mAuth.getCurrentUser();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"login_failed",Toast.LENGTH_SHORT).show();
                    pwEditText.clearComposingText();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, MessageActivity.class);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailEdittext);
        pwEditText = findViewById(R.id.pwEditText);
        loginButton = findViewById(R.id.loginButton);
        userName= String.valueOf(email.getText());
        pw = String.valueOf(pwEditText.getText());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = String.valueOf(email.getText());
                pw = String.valueOf(pwEditText.getText());
                login(userName,pw);
            }
        });
    }
}
