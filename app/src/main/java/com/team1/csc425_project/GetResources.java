package com.team1.csc425_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GetResources extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_resources);
    }

    public void process(View view)
    {
        Intent intent=null, chooser=null;

        if(view.getId()==R.id.resourceMapButton1)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            //Sets the coordinates and pinpoints the map
            intent.setData(Uri.parse("geo:43.012776,-83.683700?q=43.012776,-83.683700"));
            //Allows the user to select which maps app they want to use
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
        if(view.getId()==R.id.resourceMapButton2)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            //Sets the coordinates and pinpoints the map
            intent.setData(Uri.parse("geo:43.029870,-83.703023?q=43.029870,-83.703023"));
            //Allows the user to select which maps app they want to use
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
        if(view.getId()==R.id.resourceMapButton3)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            //Sets the coordinates and pinpoints the map
            intent.setData(Uri.parse("geo:43.045331,-83.654912?q=43.045331,-83.654912"));
            //Allows the user to select which maps app they want to use
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
        if(view.getId()==R.id.resourceMapButton4)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            //Sets the coordinates and pinpoints the map
            intent.setData(Uri.parse("geo:43.061347,-83.714771?q=43.061347,-83.714771"));
            //Allows the user to select which maps app they want to use
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
        if(view.getId()==R.id.resourceMapButton5)
        {
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            //Sets the coordinates and pinpoints the map
            intent.setData(Uri.parse("geo:42.988741,-83.672190?q=42.988741,-83.672190"));
            //Allows the user to select which maps app they want to use
            chooser=Intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
        }
    }
}