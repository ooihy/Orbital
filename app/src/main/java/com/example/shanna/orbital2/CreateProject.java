package com.example.shanna.orbital2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateProject extends AppCompatActivity {

    private EditText mEditTextTitle;
    private EditText mEditTextAbout;
    private EditText mEditTextPay;
    private EditText mEditTextDuration;
    private EditText mEditTextDateOfListing;

    private Button mBtnPost;

    private String projectStatus = "Open"; //by default, all projects are set as open
                                            //To be changed to closed and in progress when the deal is closed
                                            //Change to completed when the job is completed
                                            //Search for projects will then need to filter by this
                                            //status so that it will only display projects that are
                                            // current
    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        mEditTextTitle = findViewById(R.id.editTextTitle);
        mEditTextAbout = findViewById(R.id.editTextAbout);
        mEditTextPay = findViewById(R.id.editTextPay);
        mEditTextDuration = findViewById(R.id.editTextDuration);
        mEditTextDateOfListing = findViewById(R.id.editTextDateOfListing);
        mBtnPost = findViewById(R.id.buttonPost);

        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = mEditTextTitle.getText().toString().trim(); //get from textbox
                final String about = mEditTextAbout.getText().toString().trim(); //get from textbox
                final String pay = mEditTextPay.getText().toString().trim(); //get from textbox
                final String duration = mEditTextDuration.getText().toString().trim(); //get from textbox
                final String dateOfListing = mEditTextDateOfListing.getText().toString().trim(); //get from textbox

                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                String current_uid = mCurrentUser.getUid();
                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("Projects").child(title);

                // check if changes is empty
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(CreateProject.this, "Enter project title", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if quotation is empty
                if (TextUtils.isEmpty(about)) {
                    Toast.makeText(CreateProject.this, "Enter short description about project.", Toast.LENGTH_LONG).show();
                    return;
                }

                // check if phone number is of valid length
                if (about.length() > 100) {
                    Toast.makeText(CreateProject.this, "You have exceeded the 100 characters limit for project description.", Toast.LENGTH_LONG).show();
                    return;
                }

                // check if phone number is empty
                if (TextUtils.isEmpty(pay)) {
                    Toast.makeText(CreateProject.this, "Enter pay range.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if password is empty
                if (TextUtils.isEmpty(duration)) {
                    Toast.makeText(CreateProject.this, "Enter duration requirement.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check if date of listing is empty
                if (TextUtils.isEmpty(dateOfListing)) {
                    Toast.makeText(CreateProject.this, "Enter Today's date.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // project data
                final HashMap<String, Object> projectMap = new HashMap<>();

                projectMap.put("Owner", FirebaseAuth.getInstance().getCurrentUser().getUid());
                projectMap.put("Title", title);
                projectMap.put("About", about);
                projectMap.put("Pay", pay);
                projectMap.put("Duration", duration);
                projectMap.put("DateOfListing", dateOfListing);
                projectMap.put("ProjectStatus", projectStatus);
                projectMap.put("Partner", "");
                projectMap.put("MaxChanges", "");
                projectMap.put("BaseQuote", "");
                projectMap.put("Deadline", "");
                projectMap.put("WaitingTime", "");
                projectMap.put("CollaborationForms", new ArrayList<String>());

                mUserDatabase.setValue(projectMap);

                Toast.makeText(CreateProject.this, "Project successfully listed!", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}
