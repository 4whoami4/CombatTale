package com.combattale.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.components.MiniGameBorder;
import com.combattale.components.PlayerHeart;
import com.combattale.components.SafeZone;
import com.combattale.components.ui.Health;
import com.combattale.scenes.FirstStageScene;
import com.combattale.utils.Controller;

public class PlayerController extends Controller {
    private PlayerHeart playerHeart;
    private MiniGameBorder border;
    private SafeZone safeZone;
    private Health health;

    public Vector2 position;
    private Rectangle limits;
    public boolean canMove = false;

    @Override
    public void create() {
        playerHeart = FirstStageScene.getComponent(PlayerHeart.class);
        border = FirstStageScene.getComponent(MiniGameBorder.class);
        safeZone = FirstStageScene.getComponent(SafeZone.class);
        health = FirstStageScene.getComponent(Health.class);
    }

    @Override
    public void update(float deltaTime) {
        if (!safeZone.isInZone(position)) {
            health.decrease(1);
        }
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (position == null) return;
        if (canMove) movePlayer(input, deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        int playerHeartPositionX = (width - playerHeart.getWidth()) / 2;
        int playerHeartPositionY = (height - playerHeart.getHeight()) / 2 - 150;

        position = new Vector2(playerHeartPositionX, playerHeartPositionY);
        limits = border.getRect();
    }

    private void movePlayer(Input input, float deltaTime) {
        if (position == null) return;

        float heartX = position.x;
        float heartY = position.y;

        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            heartX += 300 * deltaTime;
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            heartX -= 300 * deltaTime;
        }
        if (input.isKeyPressed(Input.Keys.UP)) {
            heartY += 300 * deltaTime;
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            heartY -= 300 * deltaTime;
        }

        float minX = limits.x, maxX = minX + limits.width - playerHeart.getWidth();
        float minY = limits.y, maxY = minY + limits.height - playerHeart.getHeight();

        if (heartX < minX) {
            heartX = minX;
        } else if (heartX > maxX) {
            heartX = maxX;
        }

        if (heartY < minY) {
            heartY = minY;
        } else if (heartY > maxY) {
            heartY = maxY;
        }

        playerHeart.updatePosition(heartX, heartY);
    }
}
