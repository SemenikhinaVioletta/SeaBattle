package com.example.vetraletta;

import android.graphics.Color;
import android.widget.TextView;

import java.util.Arrays;

public class Ship extends BattleField_activity{
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

            if (s.hit(x - 1, y - 1) >= 0) return true;
            if (s.hit(x, y - 1) >= 0) return true;
            if (s.hit(x + 1, y - 1) >= 0) return true;
            if (s.hit(x, y - 1) >= 0) return true;
            if (s.hit(x, y + 1) >= 0) return true;
            if (s.hit(x - 1, y + 1) >= 0) return true;
            if (s.hit(x, y + 1) >= 0) return true;
            if (s.hit(x + 1, y + 1) >= 0) return true;
        }
        return false;
    }

    public void draw(boolean showBody) {
        TextView c;
        boolean s = showBody || !afloat();

        for (int i = 0; y < body.length; i++) {
            c = (TextView) findViewById(BattleField.idXY(getX(i), getY(i)));
            c.setTextColor(Color.RED);
            if (s || body[i] == knockedOut) c.setBackgroundColor(Color.GREEN);
            c.setText(String.format("%c", body[i] == knockedOut ? knockedOut : empty));
        }
    }
}
