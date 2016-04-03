package com.team1.csc425_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class NewsRead extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_news_read);
        setContentView(R.layout.activity_news_web);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Integer buttonNum=0;

        //recieve which button was pressed
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            buttonNum = extras.getInt("buttonNum");
        }

        //DLParams params = new DLParams(buttonNum);
        //DownloadFilesTask dl = new DownloadFilesTask(this);
        BingAsyncTask bdl = new BingAsyncTask(this);
        Log.d("array","the contents of buttonNum before passing it into dl.execute()");
        Log.d("array",buttonNum.toString());
        bdl.execute(buttonNum);//download content for main body and title
    }

}
