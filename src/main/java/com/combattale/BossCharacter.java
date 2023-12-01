package com.combattale;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BossCharacter {
    private Texture bossTexture;
    private Vector2 position;

    private float scale;

    public BossCharacter() {
        scale = 0.5f; // Adjust the scale factor as needed

        bossTexture = new Texture("skeleton.png"); // Replace with your boss character texture path
        position = new Vector2(300, 270); // Set the initial position of the boss character
    }

    public void update(float deltaTime) {
        // Add any logic here to update the boss character's behavior
    }

    public void render(SpriteBatch batch) {

        batch.draw(bossTexture, position.x, position.y, bossTexture.getWidth() * scale, bossTexture.getHeight() * scale);
    }

    public void dispose() {
        bossTexture.dispose();
    }
}
