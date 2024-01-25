package com.combattale.components.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Fonts;
import com.combattale.utils.GuiPosition;

public class FpsCounter extends Text {
    public FpsCounter() {
        super("", Fonts.BODY_FONT);
        withPosition(GuiPosition.LEFT_TOP);
        withOffset(new Vector2(10, -10));
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        text = "FPS: " + Gdx.graphics.getFramesPerSecond();
        super.render(spriteBatch, shapeRenderer);
    }
}
