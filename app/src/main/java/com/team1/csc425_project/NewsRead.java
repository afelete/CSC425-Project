package com.team1.csc425_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsRead extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_news_read);
        setContentView(R.layout.activity_news_web);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        Integer loadMore=0;
        Integer buttonNum=0;

        //recieve which button was pressed
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            buttonNum = extras.getInt("buttonNum");
            loadMore = extras.getInt("loadMore");
        }

        BingAsyncTask bdl = new BingAsyncTask(this);
        Log.d("array", "the contents of buttonNum before passing it into dl.execute()");
        Log.d("array", buttonNum.toString());
        bdl.execute(buttonNum,loadMore);//download content for main body and title

        //create floating action button with share icon

        //set fab to be clickable
        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab_news_read);

        //declare instance of my custum listener
        MyOnClickListener listener = new MyOnClickListener(buttonNum);

        fab1.setOnClickListener(listener);

    }

    @Override
    public void onClick(View v) {

    }


    public class MyOnClickListener implements View.OnClickListener {
        int buttonPressed;
        public MyOnClickListener(int buttonPressed){
            this.buttonPressed=buttonPressed;
        }

        @Override
        public void onClick(View v) {
            String url=null;
            if (v.getId() == R.id.fab_news_read) {
                postWrap pw = new postWrap();
                JSONArray getUrl = pw.jasonarray;
                try {
                    JSONObject getUrl2 = getUrl.getJSONObject(buttonPressed-1);
                    url=getUrl2.getString("Url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = url;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }

        }
    }
}

