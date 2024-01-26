package com.combattale.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Component;

public class PlayerHeart extends Component {
    private static final float SCALE = 0.2f;

    private boolean isVisible = true;
    private Texture texture;
    private Vector2 position;

    @Override
    public void create() {
        texture = new Texture("textures/Undertale.png");
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (position == null || !isVisible) return;
        spriteBatch.begin();
        spriteBatch.draw(
                texture,
                position.x,
                position.y,
                texture.getWidth() * SCALE,
                texture.getHeight() * SCALE
        );
        spriteBatch.end();
    }

    public void hide() {
        isVisible = false;
    }

    public void show() {
        isVisible = true;
    }

    public void updatePosition(float x, float y) {
        position = new Vector2(x, y);
    }

    public int getWidth() {
        return (int) (texture.getWidth() * SCALE);
    }

    public int getHeight() {
        return (int) (texture.getHeight() * SCALE);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
