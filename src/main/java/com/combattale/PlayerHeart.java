package com.combattale;

import com.badlogic.gdx.Gdx;
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
        texture = new Texture("textures/Undertale.png");
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        int playerHeartWidth = (int) (texture.getWidth() * SCALE);
        int playerHeartHeight = (int) (texture.getHeight() * SCALE);

        int playerHeartPositionX = (width - playerHeartWidth) / 2 ; // Adjust the value as needed
        int playerHeartPositionY = (height - playerHeartHeight) / 2 - 150; // Adjust the value as needed

        position = new Vector2(playerHeartPositionX, playerHeartPositionY);

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
