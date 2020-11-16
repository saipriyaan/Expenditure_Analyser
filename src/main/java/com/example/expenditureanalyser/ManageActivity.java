package com.example.expenditureanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManageActivity extends AppCompatActivity {
    TextView amount,cause;
    Button confirmbtn;
   /* FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
       /* database=FirebaseDatabase.getInstance();
        myRef=database.getReference("users");*/
        //mAuth = FirebaseAuth.getInstance();

        amount=findViewById(R.id.amount);
        cause=findViewById(R.id.cause);
        confirmbtn=findViewById(R.id.confirmbtn);
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String causeInput=cause.getText().toString();
                String amountInput=amount.getText().toString();
                if(causeInput.isEmpty()){
                    Toast.makeText(ManageActivity.this,"Enter the cause",Toast.LENGTH_LONG).show();
                }
                else if (amountInput.isEmpty())
                {
                    Toast.makeText(ManageActivity.this,"Enter the amount spent",Toast.LENGTH_LONG).show();
                }
                /*else
                    {
                        getUp(causeInput,amountInput);
                    }*/
            }
        });
    }
    /*void getUp(final String causeInput,String amountInput)
    {
        ProfileModel profileModel= new ProfileModel(amountInput,causeInput);
        myRef.child(mAuth.getUid()).setValue(profileModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ManageActivity.this,"Updated Successfully!",Toast.LENGTH_LONG).show();
            }
        });
    }*/
}