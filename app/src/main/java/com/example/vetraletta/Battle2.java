package com.example.vetraletta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Battle2 extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_activity);
        Button b2 = findViewById(R.id.endMY);

        if (BattleField.l == 18) {
            TextView tv = findViewById(R.id.Hello);
            tv.setText("Победил Comp");
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BattleField_activity.d = 0;
                    BattleField.l += 1;
                    Intent intent = new Intent(Battle2.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else{ b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BattleField_activity.d = 0;
                BattleField.l += 1;
                Intent intent = new Intent(Battle2.this, Battle.class);
                startActivity(intent);
            }
        });}
    }
}
