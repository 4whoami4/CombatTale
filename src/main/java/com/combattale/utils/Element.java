package com.combattale.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Element {
    public void create() {
    }

    public void resize(int width, int height) {
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
