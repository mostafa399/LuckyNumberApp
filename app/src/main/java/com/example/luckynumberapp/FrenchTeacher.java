package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrenchTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_french_teacher);

    }
    public void sayTheColor(View view){
        Button Clicked_Btn= (Button) view;
        MediaPlayer mediaPlayer=MediaPlayer.create(this,getResources()
                .getIdentifier(Clicked_Btn.getTag().toString(),"raw",getPackageName()));
        mediaPlayer.start();


    }
}