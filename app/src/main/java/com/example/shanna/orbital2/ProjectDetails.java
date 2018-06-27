package com.example.shanna.orbital2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class ProjectDetails extends AppCompatActivity {

    private TextView mTextViewTitle;
    private TextView mTextViewAbout;
    private TextView mTextViewPay;
    private TextView mTextViewDuration;
    private Button mBtnCollaborate;
    private Button mBtnViewOwnerProfile;

    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        mTextViewTitle = findViewById(R.id.textViewTitle);
        mTextViewAbout = findViewById(R.id.textViewAbout);
        mTextViewPay = findViewById(R.id.textViewPay);
        mTextViewDuration = findViewById(R.id.textViewDuration);
        mBtnCollaborate = findViewById(R.id.buttonCollaborate);
        mBtnViewOwnerProfile = findViewById(R.id.buttonViewProfile);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final String project_title = getIntent().getStringExtra("project_title");

        //User data -> Reference to Firebase database root
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        //Get user_id
        final String user_id = mCurrentUser.getUid();


        mBtnCollaborate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //first set the value inside the text boxes to contain information that was set previously
                mDatabase.child("Users").child(user_id).child("Projects").child(project_title).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mTextViewTitle.setText(dataSnapshot.child("Title").getValue().toString());
                        mTextViewAbout.setText(dataSnapshot.child("About").getValue().toString());
                        mTextViewPay.setText(dataSnapshot.child("Pay").getValue().toString());
                        mTextViewDuration.setText(dataSnapshot.child("Duration").getValue().toString());

                        final String owner_id = dataSnapshot.child("Owner").getValue().toString();
                        Intent intent = new Intent(ProjectDetails.this, Collaborate.class);

                        intent.putExtra("owner_id", owner_id);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        mBtnViewOwnerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //first set the value inside the text boxes to contain information that was set previously
                mDatabase.child("Users").child(user_id).child("Projects").child(project_title).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String owner_id = dataSnapshot.child("Owner").getValue().toString();
                        Intent intent = new Intent(ProjectDetails.this, ViewProfile.class);
                        intent.putExtra("owner_id", owner_id);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
