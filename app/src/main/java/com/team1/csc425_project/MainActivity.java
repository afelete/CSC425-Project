package com.team1.csc425_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button resourceButton = (Button) findViewById(R.id.resourceButton);
        resourceButton.setOnClickListener(this);
        Button donateButton = (Button) findViewById(R.id.donateButton);
        donateButton.setOnClickListener(this);
        Button volunteerButton = (Button) findViewById(R.id.volunteerButton);
        volunteerButton.setOnClickListener(this);
        Button newsButton = (Button) findViewById(R.id.newsButton);
        newsButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.resourceButton) {
            Intent intent = new Intent(v.getContext(), GetResources.class);
            startActivity(intent);

        } else if (v.getId() == R.id.donateButton) {
            Intent intent = new Intent(v.getContext(), Donate.class);
            startActivity(intent);

        } else if (v.getId() == R.id.volunteerButton) {
            Intent intent = new Intent(v.getContext(), Volunteer.class);
            startActivity(intent);
        }

         else if (v.getId() == R.id.newsButton) {
            Intent intent = new Intent(v.getContext(), News.class);
            startActivity(intent);
        }


    }
}


