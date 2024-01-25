package com.combattale.components.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Fonts;
import com.combattale.utils.GuiPosition;

public class HitText extends Text {

    private static final float SPEED = 3f;
    private boolean entering = true;
    private boolean isEnabled = true;
    private float alpha = 0f;
    private float posY = -50f;

    public HitText() {
        super("", Fonts.HIT_FONT);
        withPosition(GuiPosition.CENTER_TOP);
        withOffset(new Vector2(0, posY));
    }

    public void show(String text) {
        layout.setText(Fonts.HIT_FONT, text);
        this.text = text;
        isEnabled = true;
        reset();
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (!isEnabled) return;
        Fonts.HIT_FONT.setColor(1, 1, 1, Math.clamp(alpha, 0, 1));
        super.render(spriteBatch, shapeRenderer);

        if (entering) {
            alpha += Gdx.graphics.getDeltaTime() * SPEED;
            if (alpha < 1f) posY -= Gdx.graphics.getDeltaTime() * 10 * SPEED;
        } else {
            alpha -= Gdx.graphics.getDeltaTime() * SPEED;
            if (alpha > 0f) posY += Gdx.graphics.getDeltaTime() * 10 * SPEED;
        }

        if (alpha > 1.5f * SPEED) {
            entering = false;
            alpha = 1f;
        } else if (alpha < 0f) {
            isEnabled = false;
            reset();
        }

        withOffset(new Vector2(0, posY));
    }

    private void reset() {
        entering = true;
        posY = -50f;
        alpha = 0f;
    }
}
