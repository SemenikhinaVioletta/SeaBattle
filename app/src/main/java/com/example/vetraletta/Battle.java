package com.example.vetraletta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Battle extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        Button b1 = findViewById(R.id.endCOMP);

        if (BattleField.l == 18) {
            TextView tv = findViewById(R.id.Hello);
            tv.setText("Вы победили");
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BattleField_activity.d = 1;
                    BattleField.l += 1;
                    Intent intent = new Intent(Battle.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else {b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BattleField_activity.d = 1;
                BattleField.l += 1;
                Intent intent = new Intent(Battle.this, Battle2.class);
                startActivity(intent);
            }
        });}
    }
}

