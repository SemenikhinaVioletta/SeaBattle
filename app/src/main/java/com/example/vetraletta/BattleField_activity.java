package com.example.vetraletta;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BattleField_activity extends MainActivity {
    public static View seaView;

    final int indexCyberBF = 0;
    final int indexHumanBF = 1;
    BattleField[] bf = new BattleField[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_battle);

        makeSeaField();

        bf[indexCyberBF] = new CyberBattleField();
        bf[indexHumanBF] = new HumanBattleField();

        //bf[indexCyberBF].placeShips();
        //bf[indexCyberBF].draw();


        //Button button2 = findViewById(R.id.end);
        //Button button1 = findViewById(R.id.endPlacement);

        //button2.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent(BattleField_activity.this, MainActivity.class);
        //        startActivity(intent);
        //    }
        //});
    }

    //отрисовка таблицы
    private void makeSeaField() {
        TableLayout tableLayout = findViewById(R.id.seaField);
        tableLayout.setBackgroundColor(Color.RED);
        for (int i = 0; i <= 10; i += 1) { //BattleField.height - 1
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams
                    (TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT);
            rowParams.weight = 10;

            TableRow tRow = new TableRow(this);
            tRow.setMinimumHeight(107);
            tRow.setBackgroundColor(Color.YELLOW);
            tRow.setLayoutParams(rowParams);
            tableLayout.addView(tRow);

            for(int j = 0; j < 10; j += 1){
                TableRow.LayoutParams colParams = new TableRow.LayoutParams
                        (TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT);
                colParams.weight = 10;
                colParams.setMargins(j > 0 ? 10 : 0, i > 0 ? 10 : 0, 0, 0);

                TextView tView = new TextView(this);
                tView.setMinimumWidth(107);
                tView.setMinimumHeight(107);
                tView.setLayoutParams(colParams);
                if(i == 0 || j == 0){
                    tView.setBackgroundColor(Color.BLUE);
                } else {
                    tView.setBackgroundColor(Color.GREEN);
                }
                tView.setId(BattleField.idXY(j, i));
                tRow.addView(tView);
            }
        }

        //seaView.findViewById(R.id.seaField);
    }
}