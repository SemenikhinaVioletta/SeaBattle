package com.example.vetraletta;

public class BattleField {
    BattleField(){
        ShipsPlacement(BattleField1);
    }

    public static final char ICON_sea = '_'; // незанятое поле

    public static final int NBattleships = 1; //Количество
    public static final int BattleshipSize = 4; //Размер
    public static final char BattleshipIcon = 'B'; //Обозначение

    //Трёхпалубный
    public static final int NCruisers = 2; //Количество
    public static final int CruiserSize = 3; //Размер
    public static final char CruiserIcon = 'C'; //Обозначение

    //Двухпалубный
    public static final int NDestroyers = 3; //Количество
    public static final int DestroyerSize = 2; //Размер
    public static final char DestroyerIcon = 'D'; //Обозначение

    //Однопалубный
    public static final int NSubmarines = 2; //Количество
    public static final int SubmarineSize = 1; //Размер
    public static final char SubmarineIcon = 'S'; //Обозначение

    //Мина
    public static final int NMines = 2; //Количество
    public static final int MineSize = 1; //Размер
    public static final char MineIcon = 'M'; //Обозначение

    //Поле
    public static final int Width = 10; //Ширина
    public static final int Height = 10; //Высота
    public static final int NToTry = 30;

    public static char BattleField1[][] = new char[Height][Width]; //Массив кораблей
    public static char AttackField[][] = new char[Height][Width]; // атакованое поле

    public static boolean NoShipNearby(char bf[][], int x, int y) {
        boolean no = (bf[y][x] == ICON_sea);

        if (no && x > 0) {
            no = (bf[y][x - 1] == ICON_sea);
            if (no && y > 0) no = (bf[y - 1][x - 1] == ICON_sea);
            if (no && y < Height - 1) no = (bf[y + 1][x - 1] == ICON_sea);
        }
        if (no && x < Width - 1) {
            no = (bf[y][x + 1] == ICON_sea);
            if (no && y > 0) no = (bf[y - 1][x + 1] == ICON_sea);
            if (no && y < Height - 1) no = (bf[y + 1][x + 1] == ICON_sea);
        }
        if (no && y > 0) no = (bf[y - 1][x] == ICON_sea);
        if (no && y < Height - 1) no = (bf[y + 1][x] == ICON_sea);

        return no;
    }

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

    public static void ClearBattlefield(char bf[][], char af[][]) {
        for (int y = 0; y < Height; y++) {
            for (int x = 0; x < Width; x++) {
                bf[y][x] = ICON_sea;
            }
        }
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < Width; j++) {
                af[i][j] = ICON_sea;
            }

        }
    }

}
