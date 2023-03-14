package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CheckBoxActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox box1,box2;
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        box1=findViewById(R.id.checkBox);
        box2=findViewById(R.id.checkBox2);
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);

        tv2=findViewById(R.id.TimeTv);
        tv1=findViewById(R.id.dataTv);
        //RadioGroup
        RadioGroup g = findViewById(R.id.radioGroup);
        //ImageButton
       ImageButton timePicker =findViewById(R.id.timePicker);
       ImageButton imageButton=findViewById(R.id.button_image);
        //Spinner
        showSppiner();
        imageButton.setOnClickListener(v -> {
//            DialogFragment dialogFragment=new DatePickerFragment();
//            dialogFragment.show(getSupportFragmentManager(),"Pick Date Now");
            Calendar mcurrentTime = Calendar.getInstance();
            int year = mcurrentTime.get(Calendar.YEAR);
            int month = mcurrentTime.get(Calendar.MONTH);
            int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mTimePicker;
            mTimePicker=new DatePickerDialog(CheckBoxActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tv1.setText(year+"/"+month+"/"+dayOfMonth);
                }
            },year,month,day);
            mTimePicker.show();
        });
        g.setOnCheckedChangeListener((radioGroup, checked) -> {
            RadioButton radioButton=findViewById(checked);
            Toast.makeText(CheckBoxActivity.this, "Selected" + radioButton.getText(), Toast.LENGTH_SHORT).show();
        });

        timePicker.setOnClickListener(view -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker=new TimePickerDialog(CheckBoxActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    tv2.setText(hourOfDay+":"+minute);
                }
            },hour,minute,true);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });


    }
    @Override
    public void onClick(View view) {
        if (box1.isChecked()){
            Toast.makeText(this, "tomato", Toast.LENGTH_SHORT).show();
        }
        if (box2.isChecked()){
            Toast.makeText(this, "Cheese", Toast.LENGTH_SHORT).show();
        }


    }
    private void showSppiner(){
        Spinner spinner=findViewById(R.id.spinner);
        String [] courses={"java","kotlin","c++","dataStructure"};
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CheckBoxActivity.this, ""+courses[i], Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}