package com.combattale.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Component;

public class FirstMiniGameBorder extends Component {

    private static final int WIDTH_MINIGAME_BORDER = 400;
    private static final int HEIGHT_MINIGAME_BORDER = 130;

    private Vector2 position;

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        int borderThickness = 4;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                position.x,
                position.y,
                WIDTH_MINIGAME_BORDER,
                HEIGHT_MINIGAME_BORDER
        );
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                position.x + borderThickness,
                position.y + borderThickness,
                WIDTH_MINIGAME_BORDER - borderThickness * 2,
                HEIGHT_MINIGAME_BORDER - borderThickness * 2
        );
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        position = new Vector2(
                width * 0.5f - WIDTH_MINIGAME_BORDER * 0.5f,
                height * 0.5f - HEIGHT_MINIGAME_BORDER * 0.5f - 150
        );
    }
}
