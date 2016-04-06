package com.team1.csc425_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dapless on 4/3/16.
 */
public class BingAsyncTask extends AsyncTask<Integer, Void, postWrap> {

    //private String APILink = "https://api.datamarket.azure.com/Bing/Search/v1/";
    private static String APILink = "https://api.datamarket.azure.com/Bing/SearchWeb/v1/Web?Query=%27flint%20water%20%27&$format=json";
    private String API_KEY = "mmLjU8JA94JMjOonRa/g3enqJ57r0dOgdW1gc51skVA";
    private String[] SECTION = {"image"};
    private int skip=0;//keeps track of how many pages to skip after loadMore is reset after 50

    public News someActivity;
    public NewsRead anotherActivity;
    public BingAsyncTask(News activity) {
        this.someActivity = activity;
    }

    public BingAsyncTask(NewsRead newsRead) {
        this.anotherActivity=newsRead;

    }
    @Override
    protected postWrap doInBackground(Integer... buttonNum) {
        String result = "";
        //For some reason post method doesn't work.
        //Only Get request work for this API.
        //Prepare Post request.

        int buttonPrimitive = buttonNum[0];
        int loadMore=buttonNum[1];



        JSONArray jArray;
        HttpClient httpClient = new DefaultHttpClient();


        //Add all array list
        //ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        //nameValuePairs.add(new BasicNameValuePair("Query", "'xbox'"));
        //nameValuePairs.add(new BasicNameValuePair("Market", "'en-us'"));
        //nameValuePairs.add(new BasicNameValuePair("ImageFilters", "'Size:Small'"));
        //String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");


        //Log.e("Get link result ", APILink + SECTION[0] + "?" + paramsString);
        //Build Link

        String nextUrl=APILink;
        Log.d("next50","requesting url "+nextUrl);


        HttpGet httpget = new HttpGet(nextUrl);
        //HttpGet httpget = new HttpGet(APILink + SECTION[0] + "?" + paramsString);
        String auth = API_KEY + ":" + API_KEY;
        String encodedAuth = Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP);
        Log.e("", encodedAuth);
        httpget.addHeader("Authorization", "Basic " + encodedAuth);


        //Execute and get the response.
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream inputStream = null;
            try {
                inputStream = entity.getContent();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Extract link from JSON
        //String to Json
        postWrap pw = new postWrap();
        try {
            Log.d("result","contents of result are "+result);
            JSONObject bingResult =new JSONObject(result);
            //Log.d("json","jObject.results.Title is"+jObject.getJSONArray());
            JSONObject jObject = bingResult.getJSONObject("d");
            //String next50 = jObject.getString("__next")+"$format=json";//instead of using the microsoft next 50 results, which breaks formting, increment after every search
            nextUrl =APILink+"$skip="+skip;

            Log.d("next50","next 50 url is "+nextUrl);
            jArray = jObject.getJSONArray("results");
            //jArray = new JSONArray(result);

            //set bing url to load next 50 results if we are at the 50th result.
            if (postWrap.loadMore>49){
                //BingAsyncTask.APILink=next50;
                //Log.d("next50","url for next 50 results is "+next50);
                skip=skip+postWrap.loadMore;
                postWrap.loadMore=0;
            }

            pw.buttonNum = buttonPrimitive;
            postWrap.jasonarray = jArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("pw","pw.jasonarray is "+postWrap.jasonarray);
        //pw.loadMore=loadMore;
        return pw;
    }



    @Override
    protected void onPostExecute(postWrap _postWrap) {

        //how many articles to move down recieve list from bing
        //used to load new articles
        //for example a value of 10 would load an entire new set of search results.
        //int loadMore=result.loadMore;//replaced by postWrap.loadmore

        //Log.d("display", "during start of postEx title is");
        //Log.d("display",result);
        JSONArray data=postWrap.jasonarray;
        int buttonPressed= _postWrap.buttonNum;

        Log.d("button","when news read is loading buttonNum is");
        Log.d("button",String.valueOf(buttonPressed));

        JSONObject jobject = null;
        String titleOneText = "";

        //load titles
        //skip if on wrong screen, eg no button has been pressed yet
        if (buttonPressed<0){
            for (int i = 0; i < 10; i++) {


                try {
                    Log.d("loadmore","loadmore is "+postWrap.loadMore+" when reloading titles");
                    jobject = data.getJSONObject(i+postWrap.loadMore);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("Title");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                switch (i) {
                    case 0:
                        TextView title1 = (TextView) someActivity.findViewById(R.id.title1);
                        title1.setText(titleOneText);
                        title1.invalidate();
                        Log.d("display", "ui updated for title1");
                        break;
                    case 1:
                        TextView title2 = (TextView) someActivity.findViewById(R.id.title2);
                        title2.setText(titleOneText);
                        title2.invalidate();
                        Log.d("display", "ui updated for title2");
                        break;
                    case 2:
                        TextView title3 = (TextView) someActivity.findViewById(R.id.title3);
                        title3.setText(titleOneText);
                        title3.invalidate();
                        Log.d("display", "ui updated for title3");
                        break;
                    case 3:
                        TextView title4 = (TextView) someActivity.findViewById(R.id.title4);
                        title4.setText(titleOneText);
                        title4.invalidate();
                        Log.d("display", "ui updated for title4");
                        break;
                    case 4:
                        TextView title5 = (TextView) someActivity.findViewById(R.id.title5);
                        title5.setText(titleOneText);
                        title5.invalidate();
                        Log.d("display", "ui updated for title5");
                        break;
                    case 5:
                        TextView title6 = (TextView) someActivity.findViewById(R.id.title6);
                        title6.setText(titleOneText);
                        title6.invalidate();
                        Log.d("display", "ui updated for title6");
                        break;
                    case 6:
                        TextView title7 = (TextView) someActivity.findViewById(R.id.title7);
                        title7.setText(titleOneText);
                        title7.invalidate();
                        Log.d("display", "ui updated for title7");
                        break;
                    case 7:
                        TextView title8 = (TextView) someActivity.findViewById(R.id.title8);
                        title8.setText(titleOneText);
                        title8.invalidate();
                        Log.d("display", "ui updated for title8");
                        break;
                    case 8:
                        TextView title9 = (TextView) someActivity.findViewById(R.id.title9);
                        title9.setText(titleOneText);
                        title9.invalidate();
                        Log.d("display", "ui updated for title9");
                        break;
                    case 9:
                        TextView title10 = (TextView) someActivity.findViewById(R.id.title10);
                        title10.setText(titleOneText);
                        title10.invalidate();
                        Log.d("display", "ui updated for title10");
                        break;
                }
            }

            //load content summary
            for (int c = 0; c < 10; c++) {


                try {
                    Log.d("loadmore","loadmore is "+postWrap.loadMore+" when reloading content summary");

                    jobject = data.getJSONObject(c+postWrap.loadMore);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    titleOneText = jobject.getString("Description");
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
            Log.d("loadmore","loadmore is "+postWrap.loadMore+" when reloading urls");
            switch (buttonPressed){
                case 1:
                    try {


                        jobject = data.getJSONObject(0+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    try {
                        jobject = data.getJSONObject(1+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 3:
                    try {
                        jobject = data.getJSONObject(2+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 4:
                    try {
                        jobject = data.getJSONObject(3+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 5:
                    try {
                        jobject = data.getJSONObject(4+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case 6:
                    try {
                        jobject = data.getJSONObject(5+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 7:
                    try {
                        jobject = data.getJSONObject(6+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 8:
                    try {
                        jobject = data.getJSONObject(7+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 9:
                    try {
                        jobject = data.getJSONObject(8+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case 10:
                    try {
                        jobject = data.getJSONObject(9+postWrap.loadMore);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        titleOneText = jobject.getString("Url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
            }

            //update webview with content determined by url, grabbed in switch statement.
            WebView newsWebView = (WebView)anotherActivity.findViewById(R.id.newsWebView);
            newsWebView.getSettings().setJavaScriptEnabled(true);

            newsWebView.setWebViewClient(new WebViewClient());
            newsWebView.loadUrl(titleOneText);

            try {
                jobject = data.getJSONObject(buttonPressed-1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                titleOneText = jobject.getString("Title");
            } catch (JSONException e) {
                e.printStackTrace();
            }

}
}
}
