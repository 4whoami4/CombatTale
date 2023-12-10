package com.combattale;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Element;

import java.util.Objects;

public class PlayerHeart extends Element {

    public Vector2 position;
    private Texture texture;

    private float scale;

    @Override
    public void create() {
        position = new Vector2(365, 100);
        texture = new Texture(Objects.requireNonNull(getClass().getClassLoader().getResource("Undertale.png")).getPath());
        scale = 0.2f;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y,texture.getWidth()*scale,texture.getHeight()*scale);
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if(input.isKeyPressed(Keys.RIGHT)) moveX(300 * deltaTime);
        if(input.isKeyPressed(Keys.LEFT)) moveX(-300 * deltaTime);
        if(input.isKeyPressed(Keys.UP)) moveY(300 * deltaTime);
        if(input.isKeyPressed(Keys.DOWN)) moveY(-300 * deltaTime);
    }

    void moveX(float delta) {
        position.x += delta;
    }

    void moveY(float delta) {
        position.y += delta;
    }
}
