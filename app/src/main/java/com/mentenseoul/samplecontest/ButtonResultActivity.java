package com.mentenseoul.samplecontest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ButtonResultActivity extends AppCompatActivity {
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_result);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.polarbear);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                DetailActivity mDetailActivity = (DetailActivity) DetailActivity.activity;
                mDetailActivity.finish();
            }
        });
    }
}