package com.combattale.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Component;

public class MiniGameBorder extends Component {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 130;

    private Vector2 position;
    private Texture texture;
    public boolean isBoardVisible = false;

    @Override
    public void create() {
        texture = new Texture("textures/hitBoard.png");
    }


    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        int borderThickness = 4;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                position.x,
                position.y,
                WIDTH,
                HEIGHT
        );
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                position.x + borderThickness,
                position.y + borderThickness,
                WIDTH - borderThickness * 2,
                HEIGHT - borderThickness * 2
        );
        shapeRenderer.end();

        if (!isBoardVisible) return;

        spriteBatch.begin();
        spriteBatch.draw(
                texture,
                position.x + borderThickness,
                position.y + borderThickness,
                WIDTH - borderThickness * 2,
                HEIGHT - borderThickness * 2
        );
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        position = new Vector2(
                width * 0.5f - WIDTH * 0.5f,
                height * 0.5f - HEIGHT * 0.5f - 150
        );
    }

    public Rectangle getRect() {
        return new Rectangle(
                position.x + 4,
                position.y + 4,
                WIDTH - 8,
                HEIGHT - 8
        );
    }
}
