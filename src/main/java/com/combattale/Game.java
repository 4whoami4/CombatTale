package com.combattale;

import com.combattale.utils.Vector2;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Game implements GameLoop {
    private final PlayerHeart playerHeart = new PlayerHeart();

    @Override
    public void init() {
        playerHeart.position = new Vector2(45, 45);
    }

    @Override
    public void loop() {
        SaxionApp.clear();

        playerHeart.draw();
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (!keyboardEvent.isKeyPressed()) return;

        switch (keyboardEvent.getKeyCode()) {
            case KeyboardEvent.VK_LEFT -> playerHeart.moveX(-25);
            case KeyboardEvent.VK_RIGHT -> playerHeart.moveX(25);
            case KeyboardEvent.VK_UP -> playerHeart.moveY(-25);
            case KeyboardEvent.VK_DOWN -> playerHeart.moveY(25);
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

    }
}
