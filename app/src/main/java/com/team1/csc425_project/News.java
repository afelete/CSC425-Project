package com.team1.csc425_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class News extends AppCompatActivity implements View.OnClickListener{

     private GoogleApiClient client;
    private CardView c;
    private View v;

    String articleURL1 = "http://www.painfullreset.us/fetch.php";
    String articleURL2 = "http://www.yahoo.com";
    String articleURL3 = "http://www.bing.com";
    String articleURL4 = "http://www.google.com";
    String articleURL5 = "http://www.google.com";
    String articleURL6 = "http://www.google.com";
    String articleURL7 = "http://www.google.com";
    String articleURL8 = "http://www.google.com";
    String articleURL9 = "http://www.google.com";
    String articleURL10 = "http://www.google.com";

   // String globalTitle = "notitle";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        //open news article on tap
      Button news1 = (Button) findViewById(R.id.news_button1);
        news1.setOnClickListener(this);


        Button news2 = (Button) findViewById(R.id.news_button2);
        news2.setOnClickListener(this);

        Button news3 = (Button) findViewById(R.id.news_button3);
        news3.setOnClickListener(this);

        Button news4 = (Button) findViewById(R.id.news_button4);
        news4.setOnClickListener(this);

        Button news5 = (Button) findViewById(R.id.news_button5);
        news5.setOnClickListener(this);

        Button news6 = (Button) findViewById(R.id.news_button6);
        news6.setOnClickListener(this);

        Button news7 = (Button) findViewById(R.id.news_button7);
        news7.setOnClickListener(this);

        Button news8 = (Button) findViewById(R.id.news_button8);
        news8.setOnClickListener(this);

        Button news9 = (Button) findViewById(R.id.news_button9);
        news9.setOnClickListener(this);

        Button news10 = (Button) findViewById(R.id.news_button10);
        news10.setOnClickListener(this);

        //connect to webserver

        /*
        Thread netThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    try {
                        URL url1 = new URL("http://painfullreset.us/fetch.php");
                        String content = "fail";//create and initialize, fail meaning either the request hasn't been made yet
                                                //or it was unsucsesful.
                        boolean fiveFail = false;//if the conection fails more than 5 times set this to true
                        int numAttempts = 0;
                        loop: while (fiveFail != true){
                            Log.d("json","printing the contents of 'content'(in loop)");
                            Log.d("json",content);

                            content = connect(url1);//fetch content from server
                            if (content == "fail"){
                                numAttempts = numAttempts+1;
                            }
                            if(content == "error"){
                                Log.d("connection","unkown error");
                                break loop;
                            }
                            if (numAttempts>5){
                                fiveFail = true;
                            }
                            if(content!="fail"){
                                Log.d("connection","connection to server sucsess");
                                break loop;
                            }
                        }
                        //section dedicated to processing result from server
                        //results are stored in a json object, from which info can be extracted.

                        Log.d("json","connection complete, entering data processing stage");
                        Log.d("json","printing the contents of 'content'");
                        Log.d("json",content);

                        String title="no title";

                        JSONArray jArray = new JSONArray(content);

                        JSONObject jobject = jArray.getJSONObject(0);
                        title = jobject.getString("title");

                        //globalTitle=title;

                        Log.d("json","title is");
                        Log.d("json",title);



                        //display information
                        /*
                        TextView contentPreview1 = new TextView(getApplicationContext());
                        contentPreview1=(TextView)findViewById(R.id.content1);
                        contentPreview1.setText("test");
                        contentPreview1.invalidate();
                        */
                        /*can't do this from this thread i think
                        TextView title1 = new TextView(getApplicationContext());
                        title1=(TextView)findViewById(R.id.title1);
                        title1.setText(title);
                        title1.invalidate();
                        */

                        //contentPreview1.setText(title);

                        //articleURL1 = content;
                        //Toast t = Toast.makeText(getApplicationContext(),recieveText.optString("title"),Toast.LENGTH_SHORT);
                        //t.show();

        /*
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.d("connection","connection problem");

                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/

        //netThread.start();

        //setup crap while network thread is doing work
        TextView title1 = new TextView(getApplicationContext());
        title1=(TextView)findViewById(R.id.title1);

        URL url1 = null;
        try {
            url1 = new URL("http://painfullreset.us/fetch.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Integer noButtonPress=-1;
        DownloadFilesTask update = new DownloadFilesTask(this);
        update.execute(noButtonPress);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_menu, menu);
        return true;
    }
    public void onClick(View v) {
        Bundle newsBundle = new Bundle();

        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //Integer button=null;
        if (v.getId() == R.id.news_button1) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);

            newsBundle.putInt("buttonNum", 1);
            intent.putExtras(newsBundle);
           // editor.putInt("buttonPressed", 1);
            //editor.commit();
            //button=0;
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button2) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL2);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button3) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL3);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button4) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button5) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button6) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button7) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button8) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button9) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
        if (v.getId() == R.id.news_button10) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);
            startActivity(intent);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
            startActivity(intent);

        }
    }



}
