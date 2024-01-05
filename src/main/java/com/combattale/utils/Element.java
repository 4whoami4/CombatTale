package com.combattale.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Element {
    protected Vector2 screenSize;

    public void create() {
    }

    public void resize(int width, int height) {
        screenSize = new Vector2(width, height);
    }

    public void render(SpriteBatch batch) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }

    public void keyboardEvent(Input input, float deltaTime) {
    }
}
