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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_battle);


        TableLayout tableLayout = (TableLayout) findViewById(R.id.seaField);
        //BattleField bf = new BattleField();

        //Отрисовка поля~
        for (int i = 0; i <= BattleField.Height; i++) {
            int id = i * 10;
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams
                    (TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            layoutParams.setMargins(5, 5, 5, 5);

            tableRow.setLayoutParams(layoutParams);
            tableRow.setId(id);
            tableLayout.addView(tableRow);
            tableRow.setBackgroundColor(Color.GRAY);

            for (int j = 1; j <= BattleField.Width + 1; j++) {
                int id2 = id + j;
                TableRow tableRow1 = new TableRow(this);
                TableRow.LayoutParams layoutParams1 = new TableRow.LayoutParams
                        (TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT);
                layoutParams1.weight = 10;

                layoutParams1.setMargins(5, 5, 5, 5);
                tableRow1.setLayoutParams(layoutParams1);
                tableRow1.setId(id2);
                tableRow.addView(tableRow1);

                if (i > 0 && j > 1) {
                    TableRow tableRow2 = new TableRow(this);
                    TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams
                            (TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT);
                    layoutParams2.weight = 10;

                    layoutParams2.setMargins(50, 50, 50, 50);
                    tableRow2.setLayoutParams(layoutParams2);
                    tableRow2.setId(j);
                    tableRow1.addView(tableRow2);
                } else {
                    int c;
                    String s = "";
                    if (i == 0 && j > 1) {
                        c = j - 1;
                        s = Integer.toString(c);
                    }
                    if (j == 1 && i > 0){
                        c = i;
                        s = Integer.toString(c);
                    }
                    TextView textView = new TextView(BattleField_activity.this);
                    tableRow1.addView(textView);
                    textView.setText(s);
                    textView.setTextSize(20);
                }
                if (i == 0 || j == 1) {
                    tableRow1.setBackgroundColor(Color.YELLOW);
                } else tableRow1.setBackgroundColor(Color.argb(100, 0, 50, 200));

            }
        }


        char bf[][] = BattleField.BattleField1;
        char af[][] = BattleField.AttackField;
        BattleField.ClearBattlefield(bf, af);
        BattleField.ShipsPlacement(bf);

        //super.onStart();
        for (int i = 0; i < BattleField.Height; i++) {
            int id = (i + 1) * 10;
            for (int j = 0; j < BattleField.Height; j++) {
                int id1 = id + j + 2;
                if (bf[i][j] != BattleField.ICON_sea) {
                    TableRow tr = (TableRow) findViewById(id1);
                    tr.setBackgroundColor(Color.GREEN);
                    if (bf[i][j] == BattleField.MineIcon) {
                        tr.setBackgroundColor(Color.BLACK);
                    }
                }
            }
        }//~Отрисовка поля

        Button button1 = (Button) findViewById(R.id.endPlacement);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleField_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.end);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleField_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}