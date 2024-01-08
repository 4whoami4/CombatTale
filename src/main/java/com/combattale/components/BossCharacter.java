package com.combattale.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Component;

public class BossCharacter extends Component {
    private static final float SCALE = 0.3f;

    private Texture texture;
    private Vector2 position;

    @Override
    public void create() {
        texture = new Texture("textures/BOSS1STANDING.png");
       position = new Vector2(300, 270);
    }

    @Override
    public void resize(int width, int height) {
        int bossCharacterWidth = (int) (texture.getWidth() * SCALE);
        int bossCharacterHeight = (int) (texture.getHeight() * SCALE);

        int bossCharacterX = (width - bossCharacterWidth) / 2 ; // Adjust the value as needed
        int bossCharacterY = (height - bossCharacterHeight) / 2 + 100;

        position = new Vector2(bossCharacterX, bossCharacterY);
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
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

    @Override
    public void dispose() {
        texture.dispose();
    }
}
