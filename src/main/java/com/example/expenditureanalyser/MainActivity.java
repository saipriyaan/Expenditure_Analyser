package com.example.expenditureanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView email,password,signuptext;
    Button login;

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signuptext=findViewById(R.id.signuptext);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                String emailInput=email.getText().toString();
                String passwordInput=password.getText().toString();
                if(emailInput.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter your email ID",Toast.LENGTH_SHORT).show();
                }
                else if (passwordInput.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please enter your password",Toast.LENGTH_LONG).show();
                }
                else if(emailInput.isEmpty()&&passwordInput.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    loginn(emailInput,passwordInput);
                }

            }
        });
        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
            }
        });


    }
    void loginn(String emailInput,String passwordInput)
    {
        mAuth.signInWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {
                Intent in = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(in);
                finish();
            }
            else
            {
                Toast.makeText(MainActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
            }
        });
    }
}