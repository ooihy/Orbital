package com.example.shanna.orbital2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcceptRequest extends AppCompatActivity {
/*
    private TextView mTextViewTitle;
    private TextView mTextViewChanges;
    private TextView mTextViewQuote;
    private TextView mTextViewDeadline;
    private TextView mTextViewWait;

    private Button mBtnReject;
    private Button mBtnAccept;

    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_request);

        mTextViewTitle = findViewById(R.id.textViewTitle);
        mTextViewChanges = findViewById(R.id.textViewChanges);
        mTextViewQuote = findViewById(R.id.textViewQuote);
        mTextViewDeadline = findViewById(R.id.textViewDeadline);
        mTextViewWait = findViewById(R.id.textViewWait);
        mBtnReject = findViewById(R.id.buttonReject);
        mBtnAccept = findViewById(R.id.buttonAccept);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final String owner_id = getIntent().getStringExtra("owner_id");

        mBtnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store collab agreements in owner profile
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("Partner").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("MaxChanges").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("BaseQuote").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("Deadline").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("WaitingTime").setValue("");

                Intent intent = new Intent(AcceptRequest.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

                Intent intent = new Intent(AcceptRequest.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    */
}
