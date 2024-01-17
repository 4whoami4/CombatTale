package com.combattale.components.ui;

import com.combattale.utils.Fonts;

public class Health extends Text {
    private int health = 100;

    public Health() {
        super("100", Fonts.BUTTON_FONT);
    }

    public void decrease(int amount) {
        health -= amount;
        layout.setText(Fonts.BUTTON_FONT, String.valueOf(health));
    }

    public void increase(int amount) {
        health += amount;
        layout.setText(Fonts.BUTTON_FONT, String.valueOf(health));
    }
}
