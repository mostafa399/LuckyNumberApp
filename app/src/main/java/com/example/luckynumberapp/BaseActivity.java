package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends AppCompatActivity
        implements View.OnClickListener {
    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        b1=findViewById(R.id.NormalRecyclerView);
        b2=findViewById(R.id.RecyclerViewWithCardView);
        b3=findViewById(R.id.RecyclerViewWithSingleItem);
        b4=findViewById(R.id.RecyclerViewWithMultableViewTypes);
        b5=findViewById(R.id.RecyclerViewWithMultableItemSelected);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                case R.id.NormalRecyclerView:startActivity(new Intent(BaseActivity.this, MainActivity.class));
                break;
                case R.id.RecyclerViewWithCardView:startActivity(new Intent(BaseActivity.this, CheckBoxActivity.class));
                break;
                case R.id.RecyclerViewWithSingleItem:startActivity(new Intent(BaseActivity.this, SoundPlayer.class));
                break;
                case R.id.RecyclerViewWithMultableViewTypes:startActivity(new Intent(BaseActivity.this, VideoPlayer.class));
                break;
                case R.id.RecyclerViewWithMultableItemSelected:startActivity(new Intent(BaseActivity.this, FrenchTeacher.class));
                break;
    }
    }
}