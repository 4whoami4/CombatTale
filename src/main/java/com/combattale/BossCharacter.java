package com.combattale;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Element;

public class BossCharacter extends Element {
    private static final float SCALE = 0.5f;

    private Texture texture;
    private Vector2 position;

    @Override
    public void create() {
       texture = new Texture("textures/BOSS1STANDING.png");
       position = new Vector2(300, 270);
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
    public void dispose() {
        texture.dispose();
    }
}
