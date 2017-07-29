package com.example.joni.feed;

import android.app.Activity;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        String date = dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }

    public void sendMessage(View v) {

        EditText mEdit = (EditText) findViewById(R.id.editText);
        String feedMsg = mEdit.getText().toString();
        if (feedMsg.equals("")) {
            // Do not send the message
        } else {
            View linLayout = findViewById(R.id.linLayout);

            TextView newMsg = new TextView(this);

            newMsg.setText("Joni just created a group called " + feedMsg + "!\n" + getDate());
            newMsg.setTextSize(20);
            newMsg.setBackgroundColor(Color.parseColor("#F655F080"));
            newMsg.setPadding(20, 20, 20, 20);
            newMsg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            ((LinearLayout) linLayout).addView(newMsg);
            mEdit.setText(""); // Clears the text
            hideSoftKeyboard(MainActivity.this); // Hides the keyboard

            Toast msgToast = Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT);
            msgToast.show(); // Shows the notification about the successful message
        }

    }
}
