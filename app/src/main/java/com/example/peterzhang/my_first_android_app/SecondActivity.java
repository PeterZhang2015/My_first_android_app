package com.example.peterzhang.my_first_android_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    String message_from_main_activity = "";

    String message_from_third_activity = "";

    public static final int TEXT_REQUEST = 1;

    public static final String EXTRA_MESSAGE =
            "com.example.peterzhang.my_first_android_app.extra.MESSAGE";

    public static final String EXTRA_REPLY =
            "com.example.peterzhang.my_first_android_app.extra.REPLY";

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message =intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        message_from_main_activity = message;
        TextView received_text_view = (TextView) findViewById(R.id.received_msg_from_main_activility);
        received_text_view.setText(message);

        TextView replyTextView = (TextView) findViewById(R.id.received_msg_from_third_activity);
        replyTextView.setVisibility(View.INVISIBLE);


        TextView labelReplyTextView = (TextView) findViewById(R.id.label_received_reply_message_from_the_third_activity);
        labelReplyTextView.setVisibility(View.INVISIBLE);



    }

    public void launchThirdActivility(View view) {

        Log.d(LOG_TAG, "Pass Button clicked!");

        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message_from_main_activity);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =
                        data.getStringExtra(ThirdActivity.EXTRA_REPLY);

                message_from_third_activity = reply;

                TextView labelReplyTextView = (TextView) findViewById(R.id.label_received_reply_message_from_the_third_activity);
                labelReplyTextView.setVisibility(View.VISIBLE);

                TextView replyTextView = (TextView) findViewById(R.id.received_msg_from_third_activity);
                replyTextView.setVisibility(View.VISIBLE);
                replyTextView.setText(reply);

            }
        }
    }

    public void returnMainActivility(View view) {

        Log.d(LOG_TAG, "Pass Reply Button clicked!");

        TextView textView = (TextView) findViewById(R.id.reply_msg_from_third_activity);

        Intent replyIntent = new Intent(this, MainActivity.class);

        replyIntent.putExtra(EXTRA_REPLY, message_from_third_activity);

        setResult(RESULT_OK, replyIntent);
        finish();
    }

}
