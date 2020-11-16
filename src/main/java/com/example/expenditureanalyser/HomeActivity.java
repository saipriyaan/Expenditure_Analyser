package com.example.expenditureanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    TextView nameshow;
    Button manage,balance,logoutbtn;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("users");

        mAuth = FirebaseAuth.getInstance();
        manage=findViewById(R.id.manage);
        balance=findViewById(R.id.balance);
        logoutbtn=findViewById(R.id.logoutbtn);
        nameshow=findViewById(R.id.nameshow);
        String uid = mAuth.getUid();
        myRef.child(uid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameshow.setText(snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(HomeActivity.this,ManageActivity.class);
                startActivity(inte);

            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(HomeActivity.this,BalanceActivity.class);
                startActivity(inten);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent= new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}