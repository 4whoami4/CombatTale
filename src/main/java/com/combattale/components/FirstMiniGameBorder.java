package com.combattale.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.combattale.utils.Component;

public class FirstMiniGameBorder extends Component {

    private Rectangle rect;

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (rect == null) return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO: Adjust the position and size of the border
        final int centerX = width / 2 - 200;
        rect = new Rectangle(centerX, 0, 400, 130);
    }
}

