package com.team1.csc425_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Donate extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
    }

    public void process(View view)
    {
        Intent intent=null, chooser=null;

        if(view.getId()==R.id.donateMapButton1)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:43.023624,-83.69435?q=43.023624,-83.69435"));
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
        if(view.getId()==R.id.donateMapButton2)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:43.006097,-83.686161?q=43.006097,-83.686161"));
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
        if(view.getId()==R.id.donateMapButton3)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:43.008456,-83.666178?q=43.008456,-83.666178"));
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
    }
}
