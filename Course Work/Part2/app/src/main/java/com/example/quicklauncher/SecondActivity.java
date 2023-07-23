package com.example.quicklauncher;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getIntent().hasExtra("TEXT_VALUE")) {
            TextView contentTextView = (TextView) findViewById(R.id.contentTextView);
            String content = getIntent().getExtras().getString("TEXT_VALUE");
            contentTextView.setText(content);
        }
    }
}