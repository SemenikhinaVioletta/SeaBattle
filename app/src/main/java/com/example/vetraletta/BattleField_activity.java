package com.example.vetraletta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BattleField_activity extends MainActivity {
    @SuppressLint("StaticFieldLeak")

    static final Object sMonitor = new Object();

    final int indexCyberBF = 0;
    final int indexHumanBF = 1;
    public static int onClickId;
    BattleField[] bf = new BattleField[2];
    boolean startGameMessage, endOfGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_battle);

        Button button2 = findViewById(R.id.end);
        Button button1 = findViewById(R.id.endPlacement);

        makeSeaField();

        bf[indexCyberBF] = new CyberBattleField(this);
        bf[indexHumanBF] = new HumanBattleField(this);

        bf[indexCyberBF].placeShips();
        bf[indexHumanBF].placeShips();
        bf[indexHumanBF].draw();

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
                startGameMessage = true;
                synchronized (sMonitor) {
                    sMonitor.notify();
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                startGameMessage = false;
                synchronized (sMonitor) {
                    try {
                        sMonitor.wait();
                    } catch (InterruptedException e) {
                    }
                }
                if (startGameMessage) {
                    game();
                }
            }
        }).start();

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
                    tView.setText(rc);
                } else {
                    tView.setBackgroundColor(Color.YELLOW);
                }
                tView.setId(BattleField.idXY(j, i));
                tView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                if (i > 0 && j > 0) {
                    tView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onClickId = tView.getId();
                            synchronized (sMonitor) {
                                sMonitor.notify();
                            }
                        }
                    });
                }
                tRow.addView(tView);
            }
        }
    }


    public void game() {
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {*/
        int d = Math.random() < 0.5 ? indexCyberBF : indexHumanBF;
        Button button1 = findViewById(R.id.endPlacement);
        TextView textView = (TextView) findViewById(R.id.topSeaTextView);
        TextView textView2 = (TextView) findViewById(R.id.Hello);

        d = d == indexCyberBF ? indexHumanBF : indexCyberBF;
        if (d == indexCyberBF) {
            textView.setText("Ход человека");
        } else {
            textView.setText("Ход компьютора");
        }

        bf[d].draw();
        bf[d].waitingForHit();
                   /* //bf[d].waitingForHit();
                    try {
                        th.join();
                    } catch (InterruptedException e) {}*/

           /* }
        });*/
        if (endOfGame) {
            if (d == indexCyberBF) {
                textView2.setText("Победа человека");
            } else  {
                textView2.setText("Победа компьютора");
            }
            Intent intent = new Intent(BattleField_activity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}