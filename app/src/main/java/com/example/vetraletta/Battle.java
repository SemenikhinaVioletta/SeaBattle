package com.example.vetraletta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Battle extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (BattleField.l = 0; BattleField.l < 18; ) {
            int d1 = BattleField_activity.d;

            if (BattleField_activity.d == 0) {
                setContentView(R.layout.my_activity);
                Button btComp = findViewById(R.id.endCOMP);

                btComp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BattleField_activity.d = 1;
                        BattleField.l++;
                    }
                });
            } else {
                setContentView(R.layout.comp_activity);
                Button btMy = findViewById(R.id.endMY);

                btMy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BattleField_activity.d = 0;
                        BattleField.l++;
                    }
                });
            }

            while (d1 == BattleField_activity.d) {
            }
        }

       /* if (BattleField.l >= 18) {
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
        } else {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BattleField_activity.d = 1;
                    BattleField.l += 1;
                    Intent intent = new Intent(Battle.this, Battle2.class);
                    startActivity(intent);
                }
            });
        }
    */

    }
}

