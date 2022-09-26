package com.example.vetraletta;


import android.graphics.Color;
import android.widget.TextView;


public class BattleField extends BattleField_activity {
    static final int width = 10;
    static final int height = 10;

    static final int missileBoatCount = 4;
    static final int submarineCount = 3;
    static final int destroyerCount = 2;
    static final int cruiserCount = 1;
    static final int totalShipCount =
            missileBoatCount + submarineCount + destroyerCount + cruiserCount;

    static final char alreadyHit = '*';
    static final char openSea = ' ';

    Ship[] ships;
    char[][] sea;

    int hitX, hitY;

    public BattleField() {
        int i = 0;

        ships = new Ship[totalShipCount];
        for (int j = 0; j < missileBoatCount; j++)
            ships[i++] = new Ship(Ship.missileBoat);
        for (int j = 0; j < submarineCount; j++)
            ships[i++] = new Ship(Ship.submarine);
        for (int j = 0; j < destroyerCount; j++)
            ships[i++] = new Ship(Ship.destroyer);
        for (int j = 0; j < cruiserCount; j++)
            ships[i++] = new Ship(Ship.cruiser);
        sea = new char[height][width];
    }

    public void eraseSea() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                sea[i][j] = openSea;
    }

    public boolean placeShips() {
        eraseSea();
        return false;
    }

    public boolean canBeHit() {
        return sea[hitY][hitX] != alreadyHit;
    }

    public boolean hit() {
        sea[hitY][hitX] = alreadyHit;
        for (Ship s : ships) {
            int i = s.hit(hitX, hitY);

            if (i < 0) continue;
            if (!s.knockOut(i)) return false;
            break;
        }

        for (Ship s : ships) if (s.afloat()) return false;
        return true;
    }

    public void waitingForHit() {
        draw();
        // Thread.sleep(1000);
    }

    public static int idXY(int x, int y) {
        return (y + 2) * width + x + 2;
    }

    public void draw() {
        TextView c;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                c = (TextView) findViewById(idXY(x, y));
                c.setBackgroundColor(Color.BLUE);
                c.setTextColor(Color.RED);
                c.setText(String.format("%c", sea[y][x]));
            }
        }
    }
}

