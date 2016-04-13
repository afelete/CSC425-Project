package com.team1.csc425_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import com.google.android.gms.common.api.GoogleApiClient;
import java.net.MalformedURLException;
import java.net.URL;

public class News extends AppCompatActivity implements View.OnClickListener{


    //private int loadMore=0;//how many new stories to load when 'loadmore' button is pressed
    private GoogleApiClient client;
    private CardView c;
    private View v;
    Integer noButtonPress=-1;//holds which news card has been clicked, -1 if no news card has been clicked.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        BingAsyncPostWrapper pw=new BingAsyncPostWrapper();

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

        BingAsyncTask update =new BingAsyncTask(this);

        update.execute(noButtonPress,0);//access api in background thread



    }

    @Override

    public void onClick(View v) {

        if (v.getId() == R.id.loadMore) {
            BingAsyncPostWrapper.loadMore=BingAsyncPostWrapper.loadMore+10;//load more containts the number of search results to skip, as they have already been used.
            Log.d("loadMore","loadMore is "+BingAsyncPostWrapper.loadMore);
            BingAsyncTask update =new BingAsyncTask(this);

            update.execute(-1, BingAsyncPostWrapper.loadMore);

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
            //after new articles load in.
            ScrollView scroll = new ScrollView(this);
            scroll=(ScrollView)findViewById(R.id.scrollNews);
            scroll.scrollTo(0,0);




        }

        Bundle newsBundle = new Bundle();
        int fadedTitleColor = getResources().getColor(R.color.marked_as_read_title_text);
        int fadedSummaryColor = getResources().getColor(R.color.marked_as_read_summary_text);

        if (v.getId() == R.id.card1) {
            Intent intent = new Intent(v.getContext(), NewsRead.class);

            newsBundle.putInt("buttonNum", 1);
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);
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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
            newsBundle.putInt("loadMore",BingAsyncPostWrapper.loadMore);

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
