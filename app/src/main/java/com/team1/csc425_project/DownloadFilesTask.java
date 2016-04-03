package com.team1.csc425_project;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import static com.team1.csc425_project.News.*;

/**
 * Created by dapless on 4/2/16.
 */
public class DownloadFilesTask extends AsyncTask<Integer, Void, postWrap> {
    String title1 = null;

    public News someActivity;
    public NewsRead anotherActivity;

    public DownloadFilesTask(News activity) {
        this.someActivity = activity;
    }

    public DownloadFilesTask(NewsRead newsRead) {
        this.anotherActivity=newsRead;

    }

    public static String connect(URL url) {

        Log.d("connect", "connection method activated");


        try {
            String serverContent = "";
            String returnText = "";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(300);

            //output the code sent back by the server
            Log.d("connection", "response code:");
            Log.d("connection", String.valueOf(connection.getResponseCode()));

            //create input stream
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader r = new BufferedReader((new InputStreamReader(in)));

            StringBuilder response = new StringBuilder();

            //keep reiving data and appending to string until server finihses sending data.
            while ((serverContent = r.readLine()) != null) {
                response.append(serverContent);

                Log.d("article", serverContent);//output current state of string
                Log.d("article", "appending string to json string");
                returnText = serverContent;
            }

            Log.d("article", "the contents of 'returnText' before return are:");
            Log.d("article", returnText);
            return returnText;//return the completed string.
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            //if the conection fails try again up to 5 times.
            return "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    protected postWrap doInBackground(Integer... buttonNum) {
        Log.d("array","contents of buttonNum[0]");
        Log.d("array", buttonNum[0].toString());
        int buttonPrimitive= buttonNum[0].intValue();


        JSONArray jArray = null;
        try {
            try {
                URL url1 = new URL("http://painfullreset.us/fetch.php");
                String content = "fail";//create and initialize, fail meaning either the request hasn't been made yet
                //or it was unsucsesful.
                boolean fiveFail = false;//if the conection fails more than 5 times set this to true
                int numAttempts = 0;
                loop:
                while (fiveFail != true) {
                    Log.d("json", "printing the contents of 'content'(in loop)");
                    Log.d("json", content);

                    content = connect(url1);//fetch content from server
                    if (content == "fail") {
                        numAttempts = numAttempts + 1;
                    }
                    if (content == "error") {
                        Log.d("connection", "unkown error");
                        break loop;
                    }
                    if (numAttempts > 5) {
                        fiveFail = true;
                    }
                    if (content != "fail") {
                        Log.d("connection", "connection to server sucsess");
                        break loop;
                    }
                }
                //section dedicated to processing result from server
                //results are stored in a json object, from which info can be extracted.

                Log.d("json", "connection complete, entering data processing stage");
                Log.d("json", "printing the contents of 'content'");
                Log.d("json", content);

                title1 = "no title";

                jArray = new JSONArray(content);

                // JSONObject jobject = jArray.getJSONObject(0);
                //title1 = jobject.getString("title");

                //globalTitle=title;

                // Log.d("json", "title is");
                //Log.d("json", title1);


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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("display", "before return to postex title is");
        //Log.d("display", title);


        //save server responses

        /*
        SharedPreferences saved_content =someContext.getSharedPreferences("titles",someContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = saved_content.edit();
        editor.putString("title1", title);
        editor.commit();
        */
        postWrap pw = new postWrap();
        pw.buttonNum = buttonPrimitive;
        pw.jasonarray=jArray;
        return pw;
    }



/*
    @Override
    protected JSONArray doInBackground(DLParams... params) {

        int buttonPressed=params[0].button;
        return null;
    }*/

    protected void onPostExecute(postWrap result) {
        //Log.d("display", "during start of postEx title is");
        //Log.d("display",result);
        JSONArray data=result.jasonarray;
        int buttonPressed= result.buttonNum;

        Log.d("button","when news read is loading buttonNum is");
        Log.d("button",String.valueOf(buttonPressed));

        JSONObject jobject = null;
        String titleOneText = "";

        //load titles
        //skip if on wrong screen, eg no button has been pressed yet
        if (buttonPressed<0){
        for (int i = 0; i < 10; i++) {


            try {
                jobject = data.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                titleOneText = jobject.getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            switch (i) {
                case 0:
                    TextView title1 = new TextView(someActivity);
                    title1 = (TextView) someActivity.findViewById(R.id.title1);
                    title1.setText(titleOneText);
                    title1.invalidate();
                    Log.d("display", "ui updated for title1");
                    break;

                case 1:
                    TextView title2 = new TextView(someActivity);
                    title2 = (TextView) someActivity.findViewById(R.id.title2);
                    title2.setText(titleOneText);
                    title2.invalidate();
                    Log.d("display", "ui updated for title2");
                    break;
                case 2:
                    TextView title3 = new TextView(someActivity);
                    title3 = (TextView) someActivity.findViewById(R.id.title3);
                    title3.setText(titleOneText);
                    title3.invalidate();
                    Log.d("display", "ui updated for title3");
                    break;
                case 3:
                    TextView title4 = new TextView(someActivity);
                    title4 = (TextView) someActivity.findViewById(R.id.title4);
                    title4.setText(titleOneText);
                    title4.invalidate();
                    Log.d("display", "ui updated for title4");
                    break;
                case 4:
                    TextView title5 = new TextView(someActivity);
                    title5 = (TextView) someActivity.findViewById(R.id.title5);
                    title5.setText(titleOneText);
                    title5.invalidate();
                    Log.d("display", "ui updated for title5");
                    break;
                case 5:
                    TextView title6 = new TextView(someActivity);
                    title6 = (TextView) someActivity.findViewById(R.id.title6);
                    title6.setText(titleOneText);
                    title6.invalidate();
                    Log.d("display", "ui updated for title6");
                    break;
                case 6:
                    TextView title7 = new TextView(someActivity);
                    title7 = (TextView) someActivity.findViewById(R.id.title7);
                    title7.setText(titleOneText);
                    title7.invalidate();
                    Log.d("display", "ui updated for title7");
                    break;
                case 7:
                    TextView title8 = new TextView(someActivity);
                    title8 = (TextView) someActivity.findViewById(R.id.title8);
                    title8.setText(titleOneText);
                    title8.invalidate();
                    Log.d("display", "ui updated for title8");
                    break;
                case 8:
                    TextView title9 = new TextView(someActivity);
                    title9 = (TextView) someActivity.findViewById(R.id.title9);
                    title9.setText(titleOneText);
                    title9.invalidate();
                    Log.d("display", "ui updated for title9");
                    break;
                case 9:
                    TextView title10 = new TextView(someActivity);
                    title10 = (TextView) someActivity.findViewById(R.id.title10);
                    title10.setText(titleOneText);
                    title10.invalidate();
                    Log.d("display", "ui updated for title10");
                    break;
            }
        }

                //load content summary
        for (int c = 0; c < 10; c++) {


            try {
                jobject = data.getJSONObject(c);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                titleOneText = jobject.getString("summary");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            switch (c) {
                case 0:
                    TextView contentSum0 = new TextView(someActivity);
                    contentSum0 = (TextView) someActivity.findViewById(R.id.content1);
                    contentSum0.setText(titleOneText);
                    contentSum0.invalidate();
                    Log.d("display", "ui updated for content summary 1");
                    break;

                case 1:
                    TextView contentSum1 = new TextView(someActivity);
                    contentSum1 = (TextView) someActivity.findViewById(R.id.content2);
                    contentSum1.setText(titleOneText);
                    contentSum1.invalidate();
                    Log.d("display", "ui updated for content summary 2");
                    break;

                case 2:
                    TextView contentSum2 = new TextView(someActivity);
                    contentSum2 = (TextView) someActivity.findViewById(R.id.content3);
                    contentSum2.setText(titleOneText);
                    contentSum2.invalidate();
                    Log.d("display", "ui updated for content summary 3");
                    break;

                case 3:
                    TextView contentSum3 = new TextView(someActivity);
                    contentSum3 = (TextView) someActivity.findViewById(R.id.content4);
                    contentSum3.setText(titleOneText);
                    contentSum3.invalidate();
                    Log.d("display", "ui updated for content summary 4");
                    break;

                case 4:
                    TextView contentSum4 = new TextView(someActivity);
                    contentSum4 = (TextView) someActivity.findViewById(R.id.content5);
                    contentSum4.setText(titleOneText);
                    contentSum4.invalidate();
                    Log.d("display", "ui updated for content summary 5");
                    break;

                case 5:
                    TextView contentSum5 = new TextView(someActivity);
                    contentSum5 = (TextView) someActivity.findViewById(R.id.content6);
                    contentSum5.setText(titleOneText);
                    contentSum5.invalidate();
                    Log.d("display", "ui updated for content summary 6");
                    break;

                case 6:
                    TextView contentSum6 = new TextView(someActivity);
                    contentSum6 = (TextView) someActivity.findViewById(R.id.content7);
                    contentSum6.setText(titleOneText);
                    contentSum6.invalidate();
                    Log.d("display", "ui updated for content summary 7");
                    break;

                case 7:
                    TextView contentSum7 = new TextView(someActivity);
                    contentSum7 = (TextView) someActivity.findViewById(R.id.content8);
                    contentSum7.setText(titleOneText);
                    contentSum7.invalidate();
                    Log.d("display", "ui updated for content summary 8");
                    break;

                case 8:
                    TextView contentSum8 = new TextView(someActivity);
                    contentSum8 = (TextView) someActivity.findViewById(R.id.content9);
                    contentSum8.setText(titleOneText);
                    contentSum8.invalidate();
                    Log.d("display", "ui updated for content summary 9");
                    break;

                case 9:
                    TextView contentSum9 = new TextView(someActivity);
                    contentSum9 = (TextView) someActivity.findViewById(R.id.content10);
                    contentSum9.setText(titleOneText);
                    contentSum9.invalidate();
                    Log.d("display", "ui updated for content summary 10");
                    break;


                    }
                }}

        //update news read body
        if(buttonPressed>-1){
        switch (buttonPressed){
            case 1:
                try {
                jobject = data.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                titleOneText = jobject.getString("article");
            } catch (JSONException e) {
                e.printStackTrace();
            }


                TextView newsReadBody1= new TextView(anotherActivity);
                newsReadBody1 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody1.setText(titleOneText);
                newsReadBody1.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 2:
                try {
                    jobject = data.getJSONObject(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody2= new TextView(anotherActivity);
                newsReadBody2 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody2.setText(titleOneText);
                newsReadBody2.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 3:
                try {
                    jobject = data.getJSONObject(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody3= new TextView(anotherActivity);
                newsReadBody3 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody3.setText(titleOneText);
                newsReadBody3.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 4:
                try {
                    jobject = data.getJSONObject(3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody4= new TextView(anotherActivity);
                newsReadBody4 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody4.setText(titleOneText);
                newsReadBody4.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 5:
                try {
                    jobject = data.getJSONObject(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody5= new TextView(anotherActivity);
                newsReadBody5 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody5.setText(titleOneText);
                newsReadBody5.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 6:
                try {
                    jobject = data.getJSONObject(5);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody6= new TextView(anotherActivity);
                newsReadBody6 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody6.setText(titleOneText);
                newsReadBody6.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 7:
                try {
                    jobject = data.getJSONObject(6);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody= new TextView(anotherActivity);
                newsReadBody = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody.setText(titleOneText);
                newsReadBody.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 8:
                try {
                    jobject = data.getJSONObject(7);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody7= new TextView(anotherActivity);
                newsReadBody7 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody7.setText(titleOneText);
                newsReadBody7.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 9:
                try {
                    jobject = data.getJSONObject(8);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody8= new TextView(anotherActivity);
                newsReadBody8 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody8.setText(titleOneText);
                newsReadBody8.invalidate();
                Log.d("display","ui update for news read body");
                break;
            case 10:
                try {
                    jobject = data.getJSONObject(9);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("article");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                TextView newsReadBody9= new TextView(anotherActivity);
                newsReadBody9 = (TextView)anotherActivity.findViewById(R.id.news_read_body);
                newsReadBody9.setText(titleOneText);
                newsReadBody9.invalidate();
                Log.d("display","ui update for news read body");
                break;
            }

            try {
                jobject = data.getJSONObject(buttonPressed-1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                titleOneText = jobject.getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //load the title
            switch (buttonPressed) {
                case 0:
                    TextView title1 = new TextView(anotherActivity);
                    title1 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title1.setText(titleOneText);
                    title1.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;

                case 1:
                    TextView title2 = new TextView(anotherActivity);
                    title2 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title2.setText(titleOneText);
                    title2.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 2:
                    TextView title3 = new TextView(anotherActivity);
                    title3 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title3.setText(titleOneText);
                    title3.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 3:
                    TextView title4 = new TextView(anotherActivity);
                    title4 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title4.setText(titleOneText);
                    title4.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 4:
                    TextView title5 = new TextView(anotherActivity);
                    title5 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title5.setText(titleOneText);
                    title5.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 5:
                    TextView title6 = new TextView(anotherActivity);
                    title6 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title6.setText(titleOneText);
                    title6.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 6:
                    TextView title7 = new TextView(anotherActivity);
                    title7 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title7.setText(titleOneText);
                    title7.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 7:
                    TextView title8 = new TextView(anotherActivity);
                    title8 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title8.setText(titleOneText);
                    title8.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 8:
                    TextView title9 = new TextView(anotherActivity);
                    title9 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title9.setText(titleOneText);
                    title9.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
                case 9:
                    TextView title10 = new TextView(anotherActivity);
                    title10 = (TextView) anotherActivity.findViewById(R.id.news_read_title);
                    title10.setText(titleOneText);
                    title10.invalidate();
                    Log.d("display", "ui updated for news read title");
                    break;
            }
        }
    }
}


