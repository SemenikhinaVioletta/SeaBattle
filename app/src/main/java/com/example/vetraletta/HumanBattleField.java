package com.example.vetraletta;

public class HumanBattleField extends BattleField {
    public HumanBattleField(BattleField_activity parent) {
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
        do {
            hitX = (int) (Math.random() * width);
            hitY = (int) (Math.random() * height);
        } while (!canBeHit());
        endOfGame = hit();
        draw();
    }

    @Override
    public void draw() {
        super.draw();
        for (Ship s : ships) s.draw(true);
        // Ещё надо подписфвать, что ход человека
    }
}
