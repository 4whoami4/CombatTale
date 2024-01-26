package com.combattale.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.components.MiniGameBorder;
import com.combattale.components.PlayerHeart;
import com.combattale.components.SafeZone;
import com.combattale.components.ui.Health;
import com.combattale.components.ui.Score;
import com.combattale.utils.Controller;
import com.combattale.utils.Scene;

public class PlayerController extends Controller {
    private PlayerHeart playerHeart;
    private MiniGameBorder border;
    private SafeZone safeZone;
    private Health health;
    private Score score;

    private Vector2 position;
    private Rectangle limits;
    public boolean canMove = false;
    private float time = 0;

    @Override
    public void create() {
        final Scene currentScene = Game.instance.getActiveScene();
        playerHeart = currentScene.getComponent(PlayerHeart.class);
        border = currentScene.getComponent(MiniGameBorder.class);
        safeZone = currentScene.getComponent(SafeZone.class);
        health = currentScene.getComponent(Health.class);
        score = currentScene.getComponent(Score.class);
    }

    @Override
    public void update(float deltaTime) {
        if (!safeZone.isInZone(position) && canMove) {
            health.decrease(1);
        }
        if (canMove) {
            time += deltaTime;
            score.setScore(time);
        }
    }

    public void hide() {
        canMove = false;
        playerHeart.hide();
    }

    public void show() {
        canMove = true;
        playerHeart.show();
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

        position = new Vector2(heartX, heartY);
        playerHeart.updatePosition(heartX, heartY);
    }
}
