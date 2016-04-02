package com.team1.csc425_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        //set the values for each of the EditText variables
        nameEditText = (EditText) findViewById(R.id.nameEditText); //get values
        birthdateEditText = (EditText) findViewById(R.id.birthdateEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        zipEditText = (EditText) findViewById(R.id.zipEditText);

        //Get Devices phone number
        TelephonyManager phoneInfo = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephone = phoneInfo.getLine1Number();

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
            String form = name + System.lineSeparator() + dob + System.lineSeparator() +
                    address + System.lineSeparator() + zip + System.lineSeparator() + telephone;

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
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