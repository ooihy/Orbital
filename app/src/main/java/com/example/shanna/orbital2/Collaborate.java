package com.example.shanna.orbital2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.File;
import java.util.HashMap;

public class Collaborate extends AppCompatActivity {

    private EditText mEditTextChanges;
    private EditText mEditTextQuote;
    private EditText mEditTextDeadline;
    private EditText mEditTextWait;
    private Button mBtnCollaborate;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private DatabaseReference mNotificationDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborate);

        mEditTextChanges = findViewById(R.id.editText6);
        mEditTextQuote = findViewById(R.id.editTextAbout);
        mEditTextDeadline = findViewById(R.id.editTextDuration);
        mEditTextWait = findViewById(R.id.editTextPay);
        mBtnCollaborate = findViewById(R.id.button2);

        final String owner_id = getIntent().getStringExtra("Owner");
        final String title = getIntent().getStringExtra("Title");

        auth = FirebaseAuth.getInstance();

        //notifications
        mNotificationDatabase = FirebaseDatabase.getInstance().getReference().child("Notifications");

        // click to complete collaboration
        mBtnCollaborate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String numChanges = mEditTextChanges.getText().toString().trim(); //get from textbox
                final String baseQuotation = mEditTextQuote.getText().toString().trim(); //get from textbox
                final String deadline = mEditTextDeadline.getText().toString().trim(); //get from textbox
                final String bufferWait = mEditTextWait.getText().toString().trim(); //get from textbox

                // check if changes is empty
                if (TextUtils.isEmpty(numChanges)) {
                    Toast.makeText(Collaborate.this, "Enter max number of changes", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if quotation is empty
                if (TextUtils.isEmpty(baseQuotation)) {
                    Toast.makeText(Collaborate.this, "Enter base quotation", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if phone number is empty
                if (TextUtils.isEmpty(deadline)) {
                    Toast.makeText(Collaborate.this, "Enter collaboration deadline", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if password is empty
                if (TextUtils.isEmpty(bufferWait)) {
                    Toast.makeText(Collaborate.this, "Enter buffer time for response", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Update the project's owner file
                //Under the project thread for that particular project, there is another sub-branch
                //that states collabs -> Which in turn contain many other sub branches, to store all potential
                //partner's collaborate form details -> So maybe the information can be displayed in the collabs
                //fragment for notifications.
                // store collab agreements details in owner profile

                mDatabase =  FirebaseDatabase.getInstance().getReference().child("Users").child(owner_id)
                        .child("Projects")
                        .child(title)
                        .child("CollaborationForms")
                        .child(auth.getCurrentUser().getUid());

                final HashMap<String, Object> collabMap = new HashMap<>();

                collabMap.put("Partner", auth.getCurrentUser().getUid());
                collabMap.put("MaxChanges",numChanges);
                collabMap.put("BaseQuote", baseQuotation);
                collabMap.put("Deadline", deadline);
                collabMap.put("WaitingTime", bufferWait);

                mDatabase.setValue(collabMap);
                mDatabase.setValue(collabMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        //notification
                        HashMap<String, String> notificationMap = new HashMap<>();
                        notificationMap.put("Sender",auth.getCurrentUser().getUid());
                        notificationMap.put("Type", "CollabRequest");
                        //push will generate a random token id.

                        mNotificationDatabase.child(owner_id).push().setValue(notificationMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Collaborate.this, "Request for collaboration sent!", Toast.LENGTH_LONG).show();

                            }
                        });







                        Intent here = new Intent(Collaborate.this, MainActivity.class);
                        startActivity(here);
                        finish();
                    }
                });




            }


        });

    }
}
