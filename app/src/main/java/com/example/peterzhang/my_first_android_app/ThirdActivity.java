package com.example.peterzhang.my_first_android_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private static final String LOG_TAG = ThirdActivity.class.getSimpleName();

    public static final String EXTRA_REPLY =
            "com.example.peterzhang.my_first_android_app.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.received_msg_from_main_activility);

        textView.setText(message);
    }


    public void returnRepyMessageToSecondActivity(View view) {

        Log.d(LOG_TAG, "Reply Button clicked!");

        TextView textView = (TextView) findViewById(R.id.reply_msg_from_third_activity);

        String replyMessage = textView.getText().toString();

        Intent replyIntent = new Intent(this, SecondActivity.class);

        replyIntent.putExtra(EXTRA_REPLY, replyMessage);

        setResult(RESULT_OK, replyIntent);
        finish();

    }
}
