package com.combattale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class GuiComponent extends Component {
    protected Vector2 calcPosition(GuiPosition position, float width, float height) {
        final int screenWidth = Gdx.graphics.getWidth(), screenHeight = Gdx.graphics.getHeight();
        return switch (position) {
            case LEFT_TOP -> new Vector2(0, screenHeight);
            case CENTER_TOP -> new Vector2(screenWidth * 0.5f - width * 0.5f, screenHeight);
            case RIGHT_TOP -> new Vector2(screenWidth - width, screenHeight);
            case LEFT_CENTER -> new Vector2(0, screenHeight * 0.5f + height * 0.5f);
            case CENTER -> new Vector2(screenWidth * 0.5f - width * 0.5f, screenHeight * 0.5f + height * 0.5f);
            case RIGHT_CENTER -> new Vector2(screenWidth - width, screenHeight * 0.5f + height * 0.5f);
            case LEFT_BOTTOM -> new Vector2(0, height);
            case CENTER_BOTTOM -> new Vector2(screenWidth * 0.5f - width * 0.5f, height);
            case RIGHT_BOTTOM -> new Vector2(screenWidth - width, height);
        };
    }
}
