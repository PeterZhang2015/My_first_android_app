package com.example.peterzhang.my_first_android_app;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE =
            "com.example.peterzhang.my_first_android_app.extra.MESSAGE";

    public static final int TEXT_REQUEST = 1;

    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView replyTextView = (TextView) findViewById(R.id.reply_msg_from_third_activity);
        replyTextView.setVisibility(View.INVISIBLE);

        TextView replyTextViewLabel = (TextView) findViewById(R.id.label_reply_from_third_activity);
        replyTextViewLabel.setVisibility(View.INVISIBLE);


    }

    public void launchSecondActivity(View view) {

        Log.d(LOG_TAG, "Button clicked!");

        mMessageEditText = (EditText) findViewById(R.id.editText_main);

        String message = mMessageEditText.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {

                String reply =
                        data.getStringExtra(ThirdActivity.EXTRA_REPLY);

                TextView replyTextView = (TextView) findViewById(R.id.reply_msg_from_third_activity);
                replyTextView.setVisibility(View.VISIBLE);
                replyTextView.setText(reply);

                TextView replyTextViewLabel = (TextView) findViewById(R.id.label_reply_from_third_activity);
                replyTextViewLabel.setVisibility(View.VISIBLE);
            }
        }
    }
}
