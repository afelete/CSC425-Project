package com.team1.csc425_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class News extends AppCompatActivity implements View.OnClickListener{


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private CardView c;
    private View v;

    String articleURL1 = "http://www.google.com";
    String articleURL2 = "http://www.yahoo.com";
    String articleURL3 = "http://www.bing.com";
    String articleURL4 = "http://www.google.com";
    String articleURL5 = "http://www.google.com";
    String articleURL6 = "http://www.google.com";
    String articleURL7 = "http://www.google.com";
    String articleURL8 = "http://www.google.com";
    String articleURL9 = "http://www.google.com";
    String articleURL10 = "http://www.google.com";

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





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_menu, menu);
        return true;
    }
    public void onClick(View v) {
        Bundle newsBundle = new Bundle();

        if (v.getId() == R.id.news_button1) {
            Intent intent = new Intent(v.getContext(), NewsWeb.class);

            newsBundle.putString("urlString", articleURL1);
            intent.putExtras(newsBundle);
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
