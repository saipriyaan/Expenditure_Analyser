package com.example.expenditureanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView name,signupemail,signuppassword,signupcpassword;
    Button signupbtn;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("users");



        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        signupemail=findViewById(R.id.signupemail);
        signuppassword=findViewById(R.id.signuppassword);
        signupcpassword=findViewById(R.id.signupcpassword);
        signupbtn=findViewById(R.id.signupbtn);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameInput=name.getText().toString();
                String emailInput=signupemail.getText().toString();
                String passwordInput=signuppassword.getText().toString();
                String cpasswordInput=signupcpassword.getText().toString();
                if(nameInput.isEmpty())
                {
                    Toast.makeText(SignUp.this,"Please enter a username",Toast.LENGTH_LONG).show();
                }
                else if(passwordInput.isEmpty())
                {
                    Toast.makeText(SignUp.this,"Please enter a password",Toast.LENGTH_LONG).show();
                }
                else if(cpasswordInput.isEmpty())
                {
                    Toast.makeText(SignUp.this,"Please re-enter password",Toast.LENGTH_LONG).show();
                }

                else if(!cpasswordInput.equals(passwordInput))
                {
                    Toast.makeText(SignUp.this,"Password Does not match!",Toast.LENGTH_LONG).show();
                }
                else if (passwordInput.length()<7)
                {
                    Toast.makeText(SignUp.this,"Weak password! minimun 7 characters required",Toast.LENGTH_LONG).show();
                }
                else if(emailInput.isEmpty())
                {
                    Toast.makeText(SignUp.this,"Please enter a email",Toast.LENGTH_LONG).show();
                }
                else
                {
                    signIn(emailInput,passwordInput,nameInput);
                }



            }
        });
    }
    void signIn(String emailInput, String passwordInput, final String nameInput)
    {
        mAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                   myRef.child(mAuth.getUid()).child("name").setValue(nameInput).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(SignUp.this, "Your Account is successfully created!", Toast.LENGTH_LONG).show();
                       }
                   });


                }
                else
                {
                    Toast.makeText(SignUp.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                }
        });
    }
}