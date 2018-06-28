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

        //Passed from the previous page -> Users_ProjectList
        //final String project_title = getIntent().getStringExtra("project_title");
        final String project_title = getIntent().getStringExtra("Title");
        final String project_owner_id = getIntent().getStringExtra("Owner");

        mTextViewTitle = findViewById(R.id.textViewTitle);
        mTextViewAbout = findViewById(R.id.textViewAbout);
        mTextViewPay = findViewById(R.id.textViewPay);
        mTextViewDuration = findViewById(R.id.textViewDuration);
        mBtnCollaborate = findViewById(R.id.buttonCollaborate);
        mBtnViewOwnerProfile = findViewById(R.id.buttonViewProfile);


        //Retrieve firebase database for the project from the project owner's profile
        mDatabase = FirebaseDatabase.getInstance().getReference();


        //Current user data -> Reference to Firebase database root
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String user_id = mCurrentUser.getUid();


       // Below code is to retrive the database information and display project's details
        mDatabase.child("Users")
                .child(project_owner_id)
                .child("Projects")
                .child(project_title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTextViewTitle.setText(dataSnapshot.child("Title").getValue().toString());
                mTextViewAbout.setText(dataSnapshot.child("About").getValue().toString());
                mTextViewPay.setText(dataSnapshot.child("Pay").getValue().toString());
                mTextViewDuration.setText(dataSnapshot.child("Duration").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //when user clicks collaborate, bring user to collaborate form
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

        //when user view project owner's profile, bring user to the profile
        mBtnViewOwnerProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ProjectDetails.this, ViewProfile.class);
                intent.putExtra("user_id", project_owner_id);
                startActivity(intent);
                // End the activity
                finish();
            }

        });


    }
}
