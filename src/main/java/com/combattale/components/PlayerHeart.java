package com.combattale.components;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Component;

public class PlayerHeart extends Component {
    private static final float SCALE = 0.2f;

    public Vector2 position;
    private Texture texture;

    private int borderX;
    private int borderY;
    private int borderWidth;
    private int borderHeight;


    @Override
    public void create() {
        texture = new Texture("textures/Undertale.png");

        borderX = 315;
        borderY = 110;
        borderWidth = 400;
        borderHeight = 130;
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
        float minX = borderX;
        float maxX = borderX + borderWidth - texture.getWidth() * SCALE;
        float minY = borderY;
        float maxY = borderY + borderHeight - texture.getHeight() * SCALE;

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

        // Update the border limits based on the new screen dimensions
        borderX = (int) (0.31 * width); // Adjust the value as needed
        borderY = (int) (0.173 * height); // Adjust the value as needed
        borderWidth = (int) (0.385 * width); // Adjust the value as needed
        borderHeight = (int) (0.2 * height); // Adjust the value as needed
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
