package com.dentist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class NewsRealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_real);
        Intent intent=getIntent();
        ((TextView)findViewById(R.id.title)).setText(intent.getStringExtra("title"));
        ((TextView)findViewById(R.id.news_content)).setText(intent.getStringExtra("content"));

    }

}
