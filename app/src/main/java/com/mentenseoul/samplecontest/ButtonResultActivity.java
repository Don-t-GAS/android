package com.mentenseoul.samplecontest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonResultActivity extends AppCompatActivity {
    Button button2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_result);

//        ImageView imageView = findViewById(R.id.imageView);
//        imageView.setImageResource(R.drawable.polarbear);
        textView3 = findViewById(R.id.textView3);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");
        Log.d("DATA", bundle.getString("data"));
        if(bundle.getString("data").equals("1")){
            textView3.setText("+ 8 point");
        } else if (bundle.getString("data").equals("2")){
            textView3.setText("+ 6 point");
        } else if (bundle.getString("data").equals("3")){
            textView3.setText("+ 4 point");
        } else if ((bundle.getString("data").equals("4"))){
            textView3.setText("+ 2 point");
        } else {
            textView3.setText("+ 1 point");
        }

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