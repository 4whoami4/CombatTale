package com.combattale;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Element;

public class PlayerHeart extends Element {
    private static final float SCALE = 0.2f;

    public Vector2 position;
    private Texture texture;

    @Override
    public void create() {
        position = new Vector2(365, 100);
        texture = new Texture("Undertale.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
            texture,
            position.x,
            position.y,
            texture.getWidth() * SCALE,
            texture.getHeight() * SCALE
        );
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (input.isKeyPressed(Keys.RIGHT)) moveX(300 * deltaTime);
        if (input.isKeyPressed(Keys.LEFT)) moveX(-300 * deltaTime);
        if (input.isKeyPressed(Keys.UP)) moveY(300 * deltaTime);
        if (input.isKeyPressed(Keys.DOWN)) moveY(-300 * deltaTime);
    }

    void moveX(float delta) {
        position.x += delta;
    }

    void moveY(float delta) {
        position.y += delta;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
