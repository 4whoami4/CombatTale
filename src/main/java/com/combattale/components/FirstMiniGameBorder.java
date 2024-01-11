package com.combattale.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.combattale.utils.Component;

public class FirstMiniGameBorder extends Component {
    public static final int START_X_POSITION_MINIGAME_BORDER = 315;
    public static final int START_Y_POSITION_MINIGAME_BORDER = 110;
    public static final int WIDTH_POSITION_MINIGAME_BORDER = 400;
    public static final int HEIGHT_POSITION_MINIGAME_BORDER = 130;

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        int borderThickness = 4;

        for (int i = 0; i < borderThickness; i++) {
            int adjustedX = FirstMiniGameBorder.START_X_POSITION_MINIGAME_BORDER - i;
            int adjustedY = FirstMiniGameBorder.START_Y_POSITION_MINIGAME_BORDER - i;
            int adjustedWidth = FirstMiniGameBorder.WIDTH_POSITION_MINIGAME_BORDER + i * 2;
            int adjustedHeight = FirstMiniGameBorder.HEIGHT_POSITION_MINIGAME_BORDER + i * 2;

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(adjustedX, adjustedY, adjustedWidth, adjustedHeight);
            shapeRenderer.end();
        }
    }
}
