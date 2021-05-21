package com.mentenseoul.samplecontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    Button button;
    public static Activity activity;

    TextView modeltext;
    TextView listText;
    TextView companyText;
    TextView rankText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        activity = DetailActivity.this;

        button = findViewById(R.id.button);
        modeltext = findViewById(R.id.modelText);
        listText = findViewById(R.id.listText);
        companyText = findViewById(R.id.companyText);
        rankText = findViewById(R.id.rankText);

        Intent intent = getIntent();
        XmlData xmlData = (XmlData) intent.getSerializableExtra("list");

        modeltext.setText(xmlData.getModel());
        listText.setText(xmlData.getName());
        companyText.setText(xmlData.getCompany());
        rankText.setText(xmlData.getRank());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ButtonResultActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("data", xmlData.getRank());
                intent.putExtra("myBundle", bundle);
                startActivity(intent);

            }
        });



    }

}
