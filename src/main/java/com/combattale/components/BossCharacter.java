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
    }

    @Override
    public void resize(int width, int height) {
        int bossCharacterX = (width - getWidth()) / 2;
        int bossCharacterY = (height - getHeight()) / 2 + 80;

        position = new Vector2(bossCharacterX, bossCharacterY);
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (position == null) return;
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

    public int getWidth() {
        return (int) (texture.getWidth() * SCALE);
    }

    public int getHeight() {
        return (int) (texture.getHeight() * SCALE);
    }

    public void setState(BossState state) {
        switch (state) {
            case DEAD -> texture = new Texture("textures/BOSS1DEAD.png");
            case FIGHTING -> texture = new Texture("textures/BOSS1FIGHT.png");
            case STANDING -> texture = new Texture("textures/BOSS1STANDING.png");
            case TALKING -> texture = new Texture("textures/BOSS1TALKING.png");
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public enum BossState {
        DEAD,
        FIGHTING,
        STANDING,
        TALKING
    }
}
