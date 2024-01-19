package com.combattale.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;

public abstract class Component {
    public void create() {
    }

    public void resize(int width, int height) {
    }

    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }

    protected void runDelayed(Runnable runnable, float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runnable.run();
            }
        }, delay);
    }
}
