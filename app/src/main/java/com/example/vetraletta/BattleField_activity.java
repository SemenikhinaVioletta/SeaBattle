package com.example.vetraletta;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BattleField_activity extends MainActivity {
    public static View seaView;

    final int indexCyberBF = 0;
    final int indexHumanBF = 1;
    BattleField bf[] = new BattleField[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_battle);

        makeSeaField();

        bf[indexCyberBF] = new CyberBattleField();
        bf[indexHumanBF] = new HumanBattleField();

        bf[indexCyberBF].placeShips();
        bf[indexCyberBF].draw();


        Button button2 = findViewById(R.id.end);
        Button button1 = findViewById(R.id.endPlacement);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleField_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //отрисовка таблицы
    private void makeSeaField() {
        TableLayout tableLayout = findViewById(R.id.seaField);
        for (int i = -1; i <= BattleField.height - 1; i++) {
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams
                    (TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            layoutParams.setMargins(5, 5, 5, 5);

            tableRow.setLayoutParams(layoutParams);
            tableLayout.addView(tableRow);
            tableRow.setBackgroundColor(Color.GRAY);

            for (int j = -1; j <= BattleField.width - 1; j++) {
                TableRow tableRow1 = new TableRow(this);
                TableRow.LayoutParams layoutParams1 = new TableRow.LayoutParams
                        (TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT);
                layoutParams1.weight = 10;

                layoutParams1.setMargins(5, 5, 5, 5);
                tableRow1.setLayoutParams(layoutParams1);
                tableRow.addView(tableRow1);

                if (i > 0 && j > 1) {
                    TableRow tableRow2 = new TableRow(this);
                    TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams
                            (TableRow.LayoutParams.MATCH_PARENT,
                                    TableRow.LayoutParams.MATCH_PARENT);
                    layoutParams2.weight = 10;

                    layoutParams2.setMargins(50, 50, 50, 50);
                    tableRow2.setLayoutParams(layoutParams2);
                    tableRow1.addView(tableRow2);
                } else {
                    int c;
                    String s = "";
                    if (i == -1 && j > -1) {
                        c = j + 1;
                        s = Integer.toString(c) + " ";
                    }
                    if (j == -1 && i > -1) {
                        c = i + 1;
                        s = Integer.toString(c);
                    }
                    TextView textView = new TextView(BattleField_activity.this);
                    tableRow1.addView(textView);
                    textView.setText(s);
                    textView.setId(BattleField.idXY(j, i));
                    textView.setTextSize(20);
                }
                if (i == -1 || j == -1) {
                    tableRow1.setBackgroundColor(Color.YELLOW);
                } else tableRow1.setBackgroundColor(Color.argb(100, 0, 50, 200));

            }
        }

        //seaView.findViewById(R.id.seaField);
    }
}