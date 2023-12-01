package com.combattale;

import com.combattale.utils.Vector2;
import com.combattale.utils.Resources;
import nl.saxion.app.SaxionApp;

public class PlayerHeart {
    static final int SIZE = 30;

    public Vector2 position;

    final private String spritePath;

    public PlayerHeart() {
        spritePath = Resources.getPath(this, "Undertale.png");
    }

    void moveX(int delta) {
        position.x += delta;
    }

    void moveY(int delta) {
        position.y += delta;
    }

    public void draw() {
        SaxionApp.drawImage(
                spritePath,
                position.x,
                position.y,
                SIZE,
                SIZE
        );
    }
}
