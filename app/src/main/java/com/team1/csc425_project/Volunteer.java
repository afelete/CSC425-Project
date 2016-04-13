package com.team1.csc425_project;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.telephony.TelephonyManager;


public class Volunteer extends AppCompatActivity {

    private EditText nameEditText; //where user name is stored
    private EditText birthdateEditText; //where user phone number is stored
    private EditText addressEditText; //where user address is stored
    private EditText zipEditText; //where user zip code is stored
    private String telephone;
    private final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
    private boolean havePermission=false;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    havePermission=true;
                    return;
                }else{
                    havePermission=false;
                }
                }


            // other 'case' lines to check for other
            // permissions this app might request
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        //set the values for each of the EditText variables
        nameEditText = (EditText) findViewById(R.id.nameEditText); //get values
        birthdateEditText = (EditText) findViewById(R.id.birthdateEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        zipEditText = (EditText) findViewById(R.id.zipEditText);

        //request permission for telephony from user
        //this is a new feature in android 6.0, which is why it would run on my phone -David

        //check to see if app already has this permission and ask if it doesn't
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},
                    MY_PERMISSIONS_REQUEST_READ_SMS);
        }

        //Get Devices phone number

        Log.d("tele","do we have permission "+havePermission);
        if (havePermission){
            Log.d("tele","is this even executing?");


            // permission was granted, yay! Do the
            TelephonyManager phoneInfo = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            telephone = phoneInfo.getLine1Number();
            //is telephone actually set?
            Log.d("tele", "telephone first set = " + telephone);
        }
        //moved into onRequestPermissionResult
        //TelephonyManager phoneInfo = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //telephone = phoneInfo.getLine1Number();

        //Declare the button and watch for the user to click it
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(submitListener);

        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(resetListener);
    }

    private final OnClickListener submitListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //Convert EditText views to string variables
            String name = nameEditText.getText().toString();
            String dob = birthdateEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String zip = zipEditText.getText().toString();

            //on click, we want to start up an email app and add the contents
            //of the form to it

            //is telephone actually set?
            Log.d("tele","telephone = "+telephone);
            String form = name + System.lineSeparator() + dob + System.lineSeparator() +
                    address + System.lineSeparator() + zip + System.lineSeparator() + telephone;

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setType("text/plain");
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "wikeegan@umflint.edu" });
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Volunteering");
            emailIntent.putExtra(Intent.EXTRA_TEXT, form);


            String title = "Choose";
            Intent chooser = Intent.createChooser(emailIntent, title);

            // Verify the intent will resolve to at least one activity
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }


        }
    };

    private final OnClickListener resetListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            nameEditText.setText(null);
            birthdateEditText.setText(null);
            addressEditText.setText(null);
            zipEditText.setText(null);

        }
    };

}