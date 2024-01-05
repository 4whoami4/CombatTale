package com.combattale.components;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class FirstMiniGameBorder {
    private final ShapeRenderer firstGameBorder;

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

