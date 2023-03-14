package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SoundPlayer extends AppCompatActivity  {
    private SeekBar seekBar;
    private TextView time;
    private MediaPlayer soundPlayer;
    Handler handler=new Handler();
    private double startTime=0;
    private double finalTime=0;
    private final int forwardTime=1000;
    private final int backwardTime=1000;
    private static  int oneTimeOnly=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_player);

        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button back = findViewById(R.id.backward);
        Button forward = findViewById(R.id.forward);
        TextView tittle = findViewById(R.id.songTittle);
        seekBar = findViewById(R.id.seekBar);
        time = findViewById(R.id.smallText);
        soundPlayer = MediaPlayer.create(this, R.raw.mablash);



        seekBar.setProgress(0);
        seekBar.setMax(soundPlayer.getDuration());
        handler.postDelayed(handleSeekBar,1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    soundPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        tittle.setText(getResources().getIdentifier("mablash", "raw", getPackageName()));
        play.setOnClickListener(view -> playMusic());
        pause.setOnClickListener(view -> soundPlayer.pause());
        back.setOnClickListener(view -> {
            int temp = (int) startTime;
            if ((temp + forwardTime) <= finalTime) {
                startTime = startTime + forwardTime;
                soundPlayer.seekTo((int) startTime);
            } else
                Toast.makeText(SoundPlayer.this, "Can’t Jump forward", Toast.LENGTH_SHORT).show();
        });
        forward.setOnClickListener(view -> {
            int temp = (int) startTime;
            if ((temp - backwardTime) >= 0) {
                startTime = startTime - backwardTime;
                soundPlayer.seekTo((int) startTime);

            } else {
                Toast.makeText(SoundPlayer.this, "Can’t Go Back", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @SuppressLint("DefaultLocale")
    public void playMusic(){
        soundPlayer.start();
        finalTime=soundPlayer.getDuration();
        startTime=soundPlayer.getCurrentPosition();
        if (oneTimeOnly==0){
            seekBar.setMax((int) finalTime);
            oneTimeOnly=1;

        }
        time.setText(String.format("%d min,%d sec",
                TimeUnit.MILLISECONDS.toMinutes((long)finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finalTime))));
        seekBar.setProgress((int)startTime);
        handler.postDelayed(UpdateSongTime,100);


    }
    private final Runnable UpdateSongTime=new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            startTime=soundPlayer.getCurrentPosition();
            time.setText(String.format("%d min,%d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long)startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)-
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)startTime))));
            seekBar.setProgress((int) startTime);
            handler.postDelayed(this,100);

        }
    };


    private final Runnable handleSeekBar=new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(soundPlayer.getCurrentPosition());
            handler.postDelayed(this,1000);

        }
    };


}