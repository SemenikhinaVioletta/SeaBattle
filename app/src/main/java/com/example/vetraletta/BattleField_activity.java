package com.example.vetraletta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BattleField_activity extends MainActivity {
    @SuppressLint("StaticFieldLeak")

    final int indexCyberBF = 0;
    final int indexHumanBF = 1;
    BattleField[] bf = new BattleField[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_battle);


        makeSeaField();

        bf[indexCyberBF] = new CyberBattleField(this);
        bf[indexHumanBF] = new HumanBattleField(this);

        bf[indexCyberBF].placeShips();
        bf[indexHumanBF].placeShips();
        bf[indexHumanBF].draw();


        Button button2 = findViewById(R.id.end);
        Button button1 = findViewById(R.id.endPlacement);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleField_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game();
            }
        });
    }

    //отрисовка таблицы
    private void makeSeaField() {
        TableLayout tableLayout = findViewById(R.id.seaField);
        tableLayout.setBackgroundColor(Color.WHITE);

        for (int i = 0; i <= BattleField.height; i++) {
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams
                    (TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT);
            rowParams.weight = 10;

            TableRow tRow = new TableRow(this);
            tRow.setBackgroundColor(Color.YELLOW);
            tRow.setLayoutParams(rowParams);
            tableLayout.addView(tRow);

            for (int j = 0; j <= BattleField.width; j++) {
                TableRow.LayoutParams colParams = new TableRow.LayoutParams
                        (50,
                                TableRow.LayoutParams.MATCH_PARENT);
                colParams.weight = 10;
                colParams.setMargins(j > 0 ? 10 : 0, i > 0 ? 10 : 0, 0, 0);

                TextView tView = new TextView(this);
                tView.setLayoutParams(colParams);
                if (i == 0 && j == 0) {
                    tView.setBackgroundColor(Color.MAGENTA);
                } else if (i == 0 || j == 0) {
                    String rc = Integer.toString(i == 0 ? j : i);
                    tView.setTextSize(20);
                    tView.setBackgroundColor(Color.YELLOW);
                    tView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tView.setText(rc);
                } else {
                    tView.setBackgroundColor(Color.YELLOW);
                }
                tView.setId(BattleField.idXY(j, i));
                if (i > 0 && j > 0) {
                    tView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tView.setBackgroundColor(Color.GRAY);
                        }
                    });
                }
                tRow.addView(tView);
            }
        }
    }

    public void game() {
        TextView textView = (TextView) findViewById(R.id.topSeaTextView);
        int d = (int) (Math.random() * 1), i = 0, j = 0;
        while (i < BattleField.totalShipCount && j < BattleField.totalShipCount) {
            if (d == 0) {
                textView.setText("Ход человека");
                i = BattleField.totalShipCount;
            } else {
                textView.setText("Ход компьютера");
                j = BattleField.totalShipCount;
            }
        }
    }
}