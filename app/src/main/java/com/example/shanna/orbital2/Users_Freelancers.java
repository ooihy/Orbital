package com.example.shanna.orbital2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Users_Freelancers extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mFreelancersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users__freelancers);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_freelancers);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Freelancers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFreelancersList = (RecyclerView)findViewById(R.id.freelancers_list);
    }
}
