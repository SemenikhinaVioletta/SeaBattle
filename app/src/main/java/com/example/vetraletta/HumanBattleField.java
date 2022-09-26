package com.example.vetraletta;

public class HumanBattleField extends BattleField {
    public HumanBattleField(BattleField_activity parent) {
        super(parent);
    }

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
