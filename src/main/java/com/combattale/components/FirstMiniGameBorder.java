package com.combattale.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.combattale.utils.Component;

public class FirstMiniGameBorder extends Component {private final ShapeRenderer firstGameBorder;

    public static final int START_X_POSITION_MINIGAME_BORDER = 315;
    public static final int START_Y_POSITION_MINIGAME_BORDER = 110;
    public static final int WIDTH_POSITION_MINIGAME_BORDER = 400;
    public static final int HEIGHT_POSITION_MINIGAME_BORDER = 130;


    public FirstMiniGameBorder() {
        firstGameBorder = new ShapeRenderer();
    }

    public void render(int x, int y, int width, int height, Color color) {
        firstGameBorder.begin(ShapeRenderer.ShapeType.Line);
        firstGameBorder.setColor(color);
        firstGameBorder.rect(x, y, width, height);
        firstGameBorder.end();
    }
    public void dispose() {
        firstGameBorder.dispose();
    }
}
