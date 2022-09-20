package com.example.vetraletta;

import java.util.Arrays;

public class BattleField {
    /*
     //проаерка координат
     public static boolean ShipPlacement(char bf[][], int lt, char ic) {
         boolean ok = false;
         int x1, y1;

         //srand(time(NULL));
         for (int t = 0; t < NToTry; t++) {
             int x = (int) (Math.random() * Width);
             int y = (int) (Math.random() * Height);
             int d = (int) (Math.random() * 2);

             ok = (false);
             if (d == 0 && x + lt > Width) continue;
             if (d == 1 && y + lt > Height) continue;

             ok = true;
             x1 = x;
             y1 = y;
             for (int l = 0; ok && l < lt; l++) {
                 ok = NoShipNearby(bf, x1, y1);
                 if (d == 0) x1++;
                 else y1++;
             }

             if (!ok) continue;

             x1 = x;
             y1 = y;
             for (int l = 0; l < lt; l++) {
                 bf[y1][x1] = ic;
                 if (d == 0) x1++;
                 else y1++;
             }
             break;
         }

         return ok;
     }

     //Заполнение поля
     public static boolean ShipsPlacement(char bf[][]) {
         boolean ok = false;

         for (int t = 0; t < NToTry; t++) {
             ok = true;
             ClearBattlefield(BattleField1, AttackField);
             for (int s = 0; ok && s < NBattleships; s++)
                 ok = ShipPlacement(bf, BattleshipSize, BattleshipIcon);
             for (int s = 0; ok && s < NCruisers; s++)
                 ok = ShipPlacement(bf, CruiserSize, CruiserIcon);
             for (int s = 0; ok && s < NDestroyers; s++)
                 ok = ShipPlacement(bf, DestroyerSize, DestroyerIcon);
             for (int s = 0; ok && s < NSubmarines; s++)
                 ok = ShipPlacement(bf, SubmarineSize, SubmarineIcon);
             for (int s = 0; ok && s < NMines; s++)
                 ok = ShipPlacement(bf, MineSize, MineIcon);
             if (ok) break;
         }
         return ok;
     }
 */
    static final int Width = 10;
    static final int Height = 10;

    static final int missileBoatCount = 4;
    static final int submarineCount = 3;
    static final int destroyerCount = 2;
    static final int cruiserCount = 1;
    static final int totalShipCount =
            missileBoatCount + submarineCount + destroyerCount + cruiserCount;

    Ship[] ships;

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
    }

    public boolean placeShips() {
    }
}

public class CyberBattleField extends BattleField {
    @Override
    public boolean placeShips() {

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

    public void draw() {
    }

}
