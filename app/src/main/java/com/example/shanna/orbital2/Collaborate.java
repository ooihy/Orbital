package com.example.shanna.orbital2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Collaborate extends AppCompatActivity {

    private EditText mEditTextChanges;
    private EditText mEditTextQuote;
    private EditText mEditTextDeadline;
    private EditText mEditTextWait;
    private Button mBtnCollaborate;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborate);

        mEditTextChanges = findViewById(R.id.editText6);
        mEditTextQuote = findViewById(R.id.editTextAbout);
        mEditTextDeadline = findViewById(R.id.editTextDuration);
        mEditTextWait = findViewById(R.id.editTextPay);
        mBtnCollaborate = findViewById(R.id.button2);

        final String owner_id = getIntent().getStringExtra("owner_id");

        auth = FirebaseAuth.getInstance();

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

                // store collab agreements in owner profile
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("Partner").setValue(auth.getCurrentUser().getUid());
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("MaxChanges").setValue(numChanges);
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("BaseQuote").setValue(baseQuotation);
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("Deadline").setValue(deadline);
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("WaitingTime").setValue(bufferWait);


               /* projectMap.put("Partner", current_user.getEmail());
                // add data to partner
                final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("users");
                Query query = mRef.orderByChild("Email").equalTo(partner);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        database.updateChildren(projectMap);
                        Toast.makeText(Collaborate.this, "Collaboration successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Collaborate.this, "Collaboration unsuccessful! Please try again.", Toast.LENGTH_LONG).show();
                    }
                });

                */

                Toast.makeText(Collaborate.this, "Collaboration successful!", Toast.LENGTH_SHORT).show();
               // send notification to owner party (pass owner_id here)
                startActivity(new Intent(Collaborate.this, MainActivity.class));
                finish();
            }

        });


    }

}
