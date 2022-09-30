package com.example.vetraletta;

import android.os.SystemClock;

public class CyberBattleField extends BattleField {
    public CyberBattleField(BattleField_activity parent) {
        super(parent);
    }

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
    public void waitingForHit() {
        super.waitingForHit();
        draw();
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    synchronized (sMonitor2) {
                        try {
                            sMonitor2.wait();
                        } catch (InterruptedException e) {}
                    }
                    if (onClickId >= 0) {
                        hitX = onClickId % width - 3;
                        hitY = onClickId / width - 3;
                    }
                } while (!canBeHit());
                endOfGame = hit();
                draw();
            }
        }).start();
       /* do {
            hitX = onClickId % width - 2;
            hitY = onClickId / width - 2;
        } while (!canBeHit());
        endOfGame = hit();*/
    }

    @Override
    public void draw() {
        super.draw();
        for (Ship s : ships) s.draw(false);
        // Ещё надо подписфвать, что ход компьютера
    }
}
