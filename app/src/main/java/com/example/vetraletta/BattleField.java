package com.example.vetraletta;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TableRow;
import java.util.Arrays;

public class BattleField {
    static final int width = 10;
    static final int height = 10;

    static final int missileBoatCount = 4;
    static final int submarineCount = 3;
    static final int destroyerCount = 2;
    static final int cruiserCount = 1;
    static final int totalShipCount =
            missileBoatCount + submarineCount + destroyerCount + cruiserCount;

    static final char alreadyHit = '.';
    static final char openSea = ' ';

    Ship[] ships;
    char[][] sea;

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
        return true;
    }

    public void draw() {
        for (int y = 0; y < height; y++) {
            int idY = (y + 2) * 10;

            for (int x = 0; x < width; x++) {
                int idXY = idY + x + 2;
                TableRow tr = (TableRow) findViewById(idXY);

            }
        }
    }
}

public class CyberBattleField extends BattleField {

    @Override
    public boolean placeShips() {
        int k, j;

        // srand(time(NULL));
        super.placeShips();
        for (k = ships.length - 1; k >= 0; k--) {
            do {
                do {
                    ships[k].moveTo(
                            (int) (Math.random() * width), (int) (Math.random() * height));
                    ships[k].turnOn(Math.random() < 0.5 ? Ship.horizontal : Ship.vertical);
                } while (!ships[k].checkInField(width, height));

                for (j = k + 1; j < ships.length; j++)
                    if (ships[k].inTouchOf(ships[j])) break;
            } while (j < ships.length);
            ships[k].build();
        }

        return true;
    }

    @Override
    public void draw() {
        super.draw();
        for (int i = 0; i < ships.length; i++)
            ships[i].draw(true);
    }
}

public class Ship {
    public static final int missileBoat = 1;
    public static final int submarine = 2;
    public static final int destroyer = 3;
    public static final int cruiser = 4;

    public static final int horizontal = 0;
    public static final int vertical = 1;

    static final char empty = ' ';
    static final char inOrder = 'O';
    static final char knockedOut = 'X';

    int x, y, direction;
    char[] body;

    public Ship(int shipType) {
        x = 0;
        y = 0;
        direction = horizontal;
        body = new char[shipType];
        Arrays.fill(body, empty);
    }

    public void build() {
        Arrays.fill(body, inOrder);
    }

    public boolean afloat() {
        for (char c : body) if (c == inOrder) return true;
        return false;
    }

    public int hit(int x, int y) {
        for (int i = 0; i < body.length; i++) {
            if (direction == horizontal) {
                if (this.y != y) break;
                if (this.x + i == x) return i;
            } else {
                if (this.x != x) break;
                if (this.y + i == y) return i;
            }
        }
        return -1;
    }

    public boolean knockOut(int i) {
        body[i] = knockedOut;
        return afloat();
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void turnOn(int dir) {
        direction = dir;
    }

    public int getX(int i) {
        return direction == horizontal ? x + i : x;
    }

    public int getY(int i) {
        return direction == vertical ? y + i : y;
    }

    public boolean checkInField(int width, int height) {
        if (direction == horizontal) {
            return y < height && x + body.length <= width;
        } else
            return y + body.length <= height && x < width;
    }

    public int type() {
        return body.length;
    }

    public int length() {
        return body.length;
    }

    public boolean crossTo(Ship s) {
        for (int i = 0; i < body.length; i++)
            if (s.hit(getX(i), getY(i)) >= 0) return true;
        return false;
    }

    public boolean inTouchOf(Ship s) {
        for (int i = 0; i < body.length; i++) {
            int x = getX(i);
            int y = getY(i);
            if (s.hit(x - 1, y - 1) < 0) return true;
            if (s.hit(x, y - 1) < 0) return true;
            if (s.hit(x + 1, y - 1) < 0) return true;
            if (s.hit(x, y - 1) < 0) return true;
            if (s.hit(x, y + 1) < 0) return true;
            if (s.hit(x - 1, y + 1) < 0) return true;
            if (s.hit(x, y + 1) < 0) return true;
            if (s.hit(x + 1, y + 1) < 0) return true;
        }
        return false;
    }

    public void draw(boolean hiden) {
    }
}
