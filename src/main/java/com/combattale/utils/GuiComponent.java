package com.combattale.utils;

import com.badlogic.gdx.math.Vector2;

public abstract class GuiComponent extends Component {
    protected Vector2 calcPosition(GuiPosition position, float width, float height) {
        return switch (position) {
            case LEFT_TOP -> new Vector2(0, screenSize.y);
            case CENTER_TOP -> new Vector2(screenSize.x * 0.5f - width * 0.5f, screenSize.y);
            case RIGHT_TOP -> new Vector2(screenSize.x - width, screenSize.y);
            case LEFT_CENTER -> new Vector2(0, screenSize.y * 0.5f + height * 0.5f);
            case CENTER -> new Vector2(screenSize.x * 0.5f - width * 0.5f, screenSize.y * 0.5f + height * 0.5f);
            case RIGHT_CENTER -> new Vector2(screenSize.x - width, screenSize.y * 0.5f + height * 0.5f);
            case LEFT_BOTTOM -> new Vector2(0, height);
            case CENTER_BOTTOM -> new Vector2(screenSize.x * 0.5f - width * 0.5f, height);
            case RIGHT_BOTTOM -> new Vector2(screenSize.x - width, height);
        };
    }
}
