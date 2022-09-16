package com.example.vetraletta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class Battle extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        TableLayout tableLayout = findViewById(R.id.seaFieldBattle);
        TableLayout tableLayout1 = findViewById(R.id.seaField);
        tableLayout = tableLayout1;
        Button button = findViewById(R.id.endPlacementBattle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Battle.this, Battle2.class);
                startActivity(intent);
            }
        });
    }
}

