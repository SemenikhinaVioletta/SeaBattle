package com.example.vetraletta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class Battle2 extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle2);
        TableLayout tableLayout = findViewById(R.id.seaFieldBattle2);
        TableLayout tableLayout1 = findViewById(R.id.seaField);
        tableLayout = tableLayout1;
        Button button = findViewById(R.id.endPlacementBattle2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Battle2.this, Battle.class);
                startActivity(intent);
            }
        });
    }
}
