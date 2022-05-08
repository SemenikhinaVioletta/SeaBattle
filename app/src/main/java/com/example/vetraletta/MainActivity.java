package com.example.vetraletta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.start);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer integer = new Integer(MainActivity.this, BattleField_activity.class);
                startActivity(integer);
            }
        });*/
    }
}
