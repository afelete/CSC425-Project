package com.team1.csc425_project;

import android.util.Log;

/**
 * Created by dapless on 4/2/16.
 */
public class DLParams {
    int button=0;
    DLParams(int button){
        this.button=button;
        Log.d("button", "this.button is ");
        Log.d("button",String.valueOf(this.button));
    }
}
