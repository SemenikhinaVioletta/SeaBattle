package com.example.vetraletta;

public class HumanBattleField extends BattleField {
    @Override
    public boolean placeShips() {
        super.placeShips();
        return true;
    }

    @Override
    public void draw() {
        super.draw();
        for (Ship s : ships) s.draw(true);
        // Ещё надо подписфвать, что ход человека
    }
}
