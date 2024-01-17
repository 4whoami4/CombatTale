package com.combattale.components;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.components.ui.Health;
import com.combattale.scenes.FirstStageScene;
import com.combattale.utils.Component;

public class PlayerHeart extends Component {
    private static final float SCALE = 0.2f;

    private MiniGameBorder border;
    private Health health;

    private Rectangle limits;
    public Vector2 position;
    private Texture texture;

    @Override
    public void create() {
        texture = new Texture("textures/Undertale.png");
        border = FirstStageScene.getComponent(MiniGameBorder.class);
        health = FirstStageScene.getComponent(Health.class);
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

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (position == null) return;

        // Get the current position of the player heart
        float heartX = position.x;
        float heartY = position.y;

        // Calculate the new position based on user input
        if (input.isKeyPressed(Keys.RIGHT)) {
            heartX += 300 * deltaTime;
        }
        if (input.isKeyPressed(Keys.LEFT)) {
            heartX -= 300 * deltaTime;
        }
        if (input.isKeyPressed(Keys.UP)) {
            heartY += 300 * deltaTime;
        }
        if (input.isKeyPressed(Keys.DOWN)) {
            heartY -= 300 * deltaTime;
        }

        // Restrict the new position to stay within the border limits
        float minX = limits.x, maxX = minX + limits.width - texture.getWidth() * SCALE;
        float minY = limits.y, maxY = minY + limits.height - texture.getHeight() * SCALE;

        if (heartX < minX) {
            heartX = minX;
        } else if (heartX > maxX) {
            heartX = maxX;
        }

        if (heartY < minY) {
            heartY = minY;
        } else if (heartY > maxY) {
            heartY = maxY;
        }

        // Update the position of the player heart
        position.x = heartX;
        position.y = heartY;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        int playerHeartWidth = (int) (texture.getWidth() * SCALE);
        int playerHeartHeight = (int) (texture.getHeight() * SCALE);

        int playerHeartPositionX = (width - playerHeartWidth) / 2; // Adjust the value as needed
        int playerHeartPositionY = (height - playerHeartHeight) / 2 - 150; // Adjust the value as needed

        position = new Vector2(playerHeartPositionX, playerHeartPositionY);
        limits = border.getRect();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
