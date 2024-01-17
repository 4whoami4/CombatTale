package com.combattale.components.ui;

import com.combattale.Game;
import com.combattale.scenes.GameOverScene;
import com.combattale.utils.Fonts;

public class Health extends Text {
    private int health = 100;

    public Health() {
        super("100", Fonts.BUTTON_FONT);
    }

    public void decrease(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            Game.instance.setScene(new GameOverScene());
        }
        layout.setText(Fonts.BUTTON_FONT, String.valueOf(health));
    }

    public void increase(int amount) {
        health += amount;
        if (health > 100) health = 100;
        layout.setText(Fonts.BUTTON_FONT, String.valueOf(health));
    }
}
