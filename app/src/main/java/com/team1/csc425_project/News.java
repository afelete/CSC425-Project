package com.team1.csc425_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ScrollView;
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


    //private int loadMore=0;//how many new stories to load when 'loadmore' button is pressed
     private GoogleApiClient client;
    private CardView c;
    private View v;
/*
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
*/
   // String globalTitle = "notitle";

/*
    protected void onResume(Bundle savedInstanceState){
        postWrap pw=new postWrap();
        loadMore=pw.loadMore;
        Log.d("loadMore","loadmore reset during resume to "+loadMore);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        postWrap pw=new postWrap();


        //change action bar text
        //getActionBar().setTitle("Latest News");




        //set news preview cards to be clickable
        CardView card1 = (CardView)findViewById(R.id.card1);
        card1.setOnClickListener(this);

        CardView card2 = (CardView)findViewById(R.id.card2);
        card2.setOnClickListener(this);

        CardView card3 = (CardView)findViewById(R.id.card3);
        card3.setOnClickListener(this);

        CardView card4 = (CardView)findViewById(R.id.card4);
        card4.setOnClickListener(this);

        CardView card5 = (CardView)findViewById(R.id.card5);
        card5.setOnClickListener(this);

        CardView card6 = (CardView)findViewById(R.id.card6);
        card6.setOnClickListener(this);

        CardView card7 = (CardView)findViewById(R.id.card7);
        card7.setOnClickListener(this);

        CardView card8 = (CardView)findViewById(R.id.card8);
        card8.setOnClickListener(this);

        CardView card9 = (CardView)findViewById(R.id.card9);
        card9.setOnClickListener(this);

        CardView card10 = (CardView)findViewById(R.id.card10);
        card10.setOnClickListener(this);


        Button loadMoreButton = (Button) findViewById(R.id.loadMore);
        loadMoreButton.setOnClickListener(this);
        /*
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
        */
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
        //DownloadFilesTask update = new DownloadFilesTask(this);
        BingAsyncTask update =new BingAsyncTask(this);

        update.execute(noButtonPress,0);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.news_menu, menu);
        return true;
    }
    public void onClick(View v) {

        if (v.getId() == R.id.loadMore) {
            postWrap.loadMore=postWrap.loadMore+10;
            Log.d("loadMore","loadMore is "+postWrap.loadMore);
            BingAsyncTask update =new BingAsyncTask(this);

            update.execute(-1, postWrap.loadMore);

            int unreadTitleColor =getResources().getColor(R.color.unread_title_text);
            int unreadSummaryColor = getResources().getColor(R.color.unread_summary_text);
            
            //unmark all as read
            TextView summary1 = new TextView(this);
            summary1 = (TextView)findViewById(R.id.content1);
            summary1.setTextColor(unreadSummaryColor);
            
            TextView title1 = new TextView(this);   
            title1=(TextView)findViewById(R.id.title1);
            title1.setTextColor(unreadTitleColor);            

            
            TextView summary2 = new TextView(this);
            summary2 = (TextView)findViewById(R.id.content2);
            summary2.setTextColor(unreadSummaryColor);

            TextView title2 = new TextView(this);
            title2=(TextView)findViewById(R.id.title2);
            title2.setTextColor(unreadTitleColor);
            

            TextView summary3 = new TextView(this);
            summary3 = (TextView)findViewById(R.id.content3);
            summary3.setTextColor(unreadSummaryColor);

            TextView title3 = new TextView(this);
            title3=(TextView)findViewById(R.id.title3);
            title3.setTextColor(unreadTitleColor);



            TextView summary4 = new TextView(this);
            summary4 = (TextView)findViewById(R.id.content4);
            summary4.setTextColor(unreadSummaryColor);

            TextView title4 = new TextView(this);
            title4=(TextView)findViewById(R.id.title4);
            title4.setTextColor(unreadTitleColor);



            TextView summary5 = new TextView(this);
            summary5 = (TextView)findViewById(R.id.content5);
            summary5.setTextColor(unreadSummaryColor);

            TextView title5 = new TextView(this);
            title5=(TextView)findViewById(R.id.title5);
            title5.setTextColor(unreadTitleColor);



            TextView summary6 = new TextView(this);
            summary6 = (TextView)findViewById(R.id.content6);
            summary6.setTextColor(unreadSummaryColor);

            TextView title6 = new TextView(this);
            title6=(TextView)findViewById(R.id.title6);
            title6.setTextColor(unreadTitleColor);



            TextView summary7 = new TextView(this);
            summary7 = (TextView)findViewById(R.id.content7);
            summary7.setTextColor(unreadSummaryColor);

            TextView title7 = new TextView(this);
            title7=(TextView)findViewById(R.id.title7);
            title7.setTextColor(unreadTitleColor);



            TextView summary8 = new TextView(this);
            summary8 = (TextView)findViewById(R.id.content8);
            summary8.setTextColor(unreadSummaryColor);

            TextView title8 = new TextView(this);
            title8=(TextView)findViewById(R.id.title8);
            title8.setTextColor(unreadTitleColor);



            TextView summary9 = new TextView(this);
            summary9 = (TextView)findViewById(R.id.content9);
            summary9.setTextColor(unreadSummaryColor);

            TextView title9 = new TextView(this);
            title9=(TextView)findViewById(R.id.title9);
            title9.setTextColor(unreadTitleColor);



            TextView summary10 = new TextView(this);
            summary10 = (TextView)findViewById(R.id.content10);
            summary10.setTextColor(unreadSummaryColor);

            TextView title10 = new TextView(this);
            title10=(TextView)findViewById(R.id.title10);
            title10.setTextColor(unreadTitleColor);

            //reset scroll view to top of activity
            ScrollView scroll = new ScrollView(this);
            scroll=(ScrollView)findViewById(R.id.scrollNews);
            scroll.scrollTo(0,0);




        }

        Bundle newsBundle = new Bundle();
        int fadedTitleColor = getResources().getColor(R.color.marked_as_read_title_text);
        int fadedSummaryColor = getResources().getColor(R.color.marked_as_read_summary_text);
        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //Integer button=null;
        if (v.getId() == R.id.card1) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);

            newsBundle.putInt("buttonNum", 1);
            newsBundle.putInt("loadMore",postWrap.loadMore);
            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title1 =new TextView(this);
            title1 = (TextView)findViewById(R.id.title1);
            title1.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary1 = new TextView(this);
            summary1 = (TextView)findViewById(R.id.content1);
            summary1.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card2) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 2);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title2 =new TextView(this);
            title2 = (TextView)findViewById(R.id.title2);
            title2.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary2 = new TextView(this);
            summary2 = (TextView)findViewById(R.id.content2);
            summary2.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card3) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 3);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title3 =new TextView(this);
            title3 = (TextView)findViewById(R.id.title3);
            title3.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary3 = new TextView(this);
            summary3 = (TextView)findViewById(R.id.content3);
            summary3.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card4) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 4);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title4 =new TextView(this);
            title4 = (TextView)findViewById(R.id.title4);
            title4.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary4 = new TextView(this);
            summary4 = (TextView)findViewById(R.id.content4);
            summary4.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card5) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 5);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title5 =new TextView(this);
            title5 = (TextView)findViewById(R.id.title5);
            title5.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary5 = new TextView(this);
            summary5 = (TextView)findViewById(R.id.content5);
            summary5.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card6) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 6);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title6 =new TextView(this);
            title6 = (TextView)findViewById(R.id.title6);
            title6.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary6 = new TextView(this);
            summary6 = (TextView)findViewById(R.id.content6);
            summary6.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card7) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 7);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title7 =new TextView(this);
            title7 = (TextView)findViewById(R.id.title7);
            title7.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary7 = new TextView(this);
            summary7 = (TextView)findViewById(R.id.content7);
            summary7.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card8) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 8);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title8 =new TextView(this);
            title8 = (TextView)findViewById(R.id.title8);
            title8.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary8 = new TextView(this);
            summary8 = (TextView)findViewById(R.id.content8);
            summary8.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card9) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 9);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title9 =new TextView(this);
            title9 = (TextView)findViewById(R.id.title9);
            title9.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary9 = new TextView(this);
            summary9 = (TextView)findViewById(R.id.content9);
            summary9.setTextColor(fadedSummaryColor);

        }
        if (v.getId() == R.id.card10) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);
            newsBundle.putInt("buttonNum", 10);
            newsBundle.putInt("loadMore",postWrap.loadMore);

            intent.putExtras(newsBundle);
            startActivity(intent);

            //mark text as read
            TextView title10 =new TextView(this);
            title10 = (TextView)findViewById(R.id.title10);
            title10.setTextColor(fadedTitleColor);

            //mark text as read
            TextView summary10 = new TextView(this);
            summary10 = (TextView)findViewById(R.id.content10);
            summary10.setTextColor(fadedSummaryColor);

        }
    }



}
