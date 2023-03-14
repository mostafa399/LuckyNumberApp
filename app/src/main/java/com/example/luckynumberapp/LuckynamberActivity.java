package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckynamberActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luckynamber);
        TextView lucky = findViewById(R.id.tlucky2);
        TextView textView = findViewById(R.id.tlucky1);
        Button button = findViewById(R.id.bLucky);
        String username =getIntent().getStringExtra("name");
        textView.setText(username);
        int randomNum=generateRandomNumber();
        lucky.setText(""+randomNum);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(username,randomNum);

            }
        });
    }
    public int generateRandomNumber(){
        int upper_limit=1000;
        //int randomNumberGenerated=new Random().nextInt(upper_limit);
        return new Random().nextInt(upper_limit);
    }
    public void shareData(String username,int randomNumber){
        Intent x=new Intent(Intent.ACTION_SEND);
        x.setType("text/plain");
        x.putExtra(Intent.EXTRA_SUBJECT,username);
        x.putExtra(Intent.EXTRA_TEXT,randomNumber);
        startActivity(Intent.createChooser(x,"choose a platform"));

    }
}