package com.combattale.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.combattale.Game;
import com.combattale.utils.Component;
import com.combattale.utils.Difficulty;
import com.combattale.utils.Scene;

public class FlyingTarget extends Component {

    private MiniGameBorder border;
    private float position = 1;
    private float size = 0;
    public boolean isPaused = false;
    public boolean isVisible = false;

    @Override
    public void create() {
        final Scene currentScene = Game.instance.getActiveScene();
        border = currentScene.getComponent(MiniGameBorder.class);
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (!isVisible) return;

        if (!isPaused) {
            position -= Gdx.graphics.getDeltaTime() * getSpeed();
            if (position <= 0) position = 1;
        } else {
            size += Gdx.graphics.getDeltaTime() * 8f;
            if (size > 2f) isVisible = false;
        }

        final Rectangle rect = border.getRect();
        final float offsetY = rect.height * size;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        final float color = Math.clamp(1 - size * 0.5f, 0, 1);
        shapeRenderer.setColor(color, color, color, 1f);
        shapeRenderer.rect(
                Gdx.graphics.getWidth() * position, rect.y - offsetY,
                5, rect.height + offsetY * 2
        );
        shapeRenderer.end();
    }

    public void reset() {
        isVisible = true;
        isPaused = false;
        position = 1;
        size = 0;
    }

    public double getDamageMultiplier() {
        final double pos = Math.clamp(1 - Math.abs(position - 0.5) * 2, 0, 1);
        final boolean isEasy = Game.instance.difficulty == Difficulty.EASY;
        if (pos < .85)
            return 0;
        else if (pos < .94)
            return isEasy ? 0.3 : 0.2;
        else if (pos < .97)
            return isEasy ? 0.6 : 0.5;
        return isEasy ? 1.1 : 1;
    }

    private float getSpeed() {
        return switch (Game.instance.difficulty) {
            case Difficulty.EASY -> 0.5f;
            case Difficulty.NORMAL -> 0.7f;
            case Difficulty.EXTREME -> 1f;
        };
    }
}
