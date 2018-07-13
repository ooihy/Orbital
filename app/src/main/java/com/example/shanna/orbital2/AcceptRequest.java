package com.example.shanna.orbital2;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.app.PendingIntent.getActivity;
import static android.app.PendingIntent.readPendingIntentOrNullFromParcel;

public class AcceptRequest extends AppCompatActivity {

    private TextView mTextViewTitle;
    private TextView mTextViewChanges;
    private TextView mTextViewQuote;
    private TextView mTextViewDeadline;
    private TextView mTextViewWait;

    private Button mBtnReject;
    private Button mBtnAccept;

    private DatabaseReference mDatabase;

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
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String owner_id = current_user.getUid();

        final String project_title = getIntent().getStringExtra("project_title");
        final String CollabID = getIntent().getStringExtra("Sender");
      //  Toast.makeText(AcceptRequest.this, "sender id is ->" + CollabID, Toast.LENGTH_SHORT).show();
        onNewIntent(getIntent());

    }


    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if(extras != null){
            if(extras.containsKey("Sender")) {
                // extract the extra-data in the Notification
                String msg = extras.getString("Sender");
                Toast.makeText(AcceptRequest.this, "sender id is ->" + msg, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(AcceptRequest.this, "sender id is -> null ", Toast.LENGTH_SHORT).show();
            }

        }


    }



/*
        Toast.makeText(AcceptRequest.this, "sender id is" + CollabReqSender, Toast.LENGTH_SHORT).show();

        // display agreement details
        mDatabase.child("Users").child(owner_id)
                                .child("Projects")
                                .child(project_title)
                                .child("CollaborationForms")
                                .child(CollabReqSender)
                                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTextViewTitle.setText(dataSnapshot.child("Title").getValue().toString());
                mTextViewChanges.setText(dataSnapshot.child("MaxChanges").getValue().toString());
                mTextViewQuote.setText(dataSnapshot.child("BaseQuote").getValue().toString());
                mTextViewDeadline.setText(dataSnapshot.child("Deadline").getValue().toString());
                mTextViewWait.setText(dataSnapshot.child("WaitingTime").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        /*

        // collab rejected, delete agreement info from owner_id
        mBtnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store collab agreements in owner profile
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("Partner").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("MaxChanges").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("BaseQuote").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("Deadline").setValue("");
                FirebaseDatabase.getInstance().getReference().child(owner_id).child("WaitingTime").setValue("");

                Toast.makeText(AcceptRequest.this, "Collaboration cancelled.", Toast.LENGTH_SHORT).show();
                //send notification to other person that project rejected. maybe ask them to resubmit!

                Intent intent = new Intent(AcceptRequest.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Users").child(owner_id).child("Projects").child(project_title).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String partner_id = dataSnapshot.child("Partner").getValue().toString();

                        // project data
                        final HashMap<String, Object> projectMap = new HashMap<>();
                        projectMap.put("Owner", partner_id);
                        projectMap.put("Title", dataSnapshot.child("Title").getValue().toString());
                        projectMap.put("About", dataSnapshot.child("About").getValue().toString());
                        projectMap.put("Pay", dataSnapshot.child("Pay").getValue().toString());
                        projectMap.put("Duration", dataSnapshot.child("Duration").getValue().toString());
                        projectMap.put("Partner", owner_id);
                        projectMap.put("MaxChanges", dataSnapshot.child("MaxChanges").getValue().toString());
                        projectMap.put("BaseQuote", dataSnapshot.child("BaseQuote").getValue().toString());
                        projectMap.put("Deadline", dataSnapshot.child("Deadline").getValue().toString());
                        projectMap.put("WaitingTime", dataSnapshot.child("Waiting").getValue().toString());

                        // add project info to partner (aka person who first submitted collab request)
                        mDatabase.child("Users").child(partner_id).child("Projects")
                                .child(dataSnapshot.child("Title").getValue().toString())
                                .updateChildren(projectMap);

                        Toast.makeText(AcceptRequest.this, "Collaboration successful!", Toast.LENGTH_SHORT).show();
                        //send notification to other person that project accepted, can proceed with work.

                        Intent intent = new Intent(AcceptRequest.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        */

}

