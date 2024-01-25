package com.combattale.components.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Fonts;
import com.combattale.utils.GuiComponent;
import com.combattale.utils.GuiPosition;

public class Score extends GuiComponent {
    private final GlyphLayout layout = new GlyphLayout();
    private final BitmapFont font = Fonts.BODY_FONT;
    private int score = 1000;

    @Override
    public void create() {
        layout.setText(font, String.valueOf(score));
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        Vector2 pos = calcPosition(GuiPosition.RIGHT_BOTTOM, 160, layout.height);
        float fill = 128 * score / 1000f;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        shapeRenderer.rect(pos.x - 53 - 12, pos.y + 14, 128, 21);
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.rect(pos.x - 53 - 12 + (128 - fill), pos.y + 14, fill, 21);
        shapeRenderer.end();

        spriteBatch.begin();
        font.draw(spriteBatch, "SCORE", pos.x + 107 - 32, pos.y + 32);
        font.draw(spriteBatch, layout, pos.x + 107 - 32 - 16 - layout.width, pos.y + 32);
        spriteBatch.end();
    }

    public void setScore(float time) {
        score = 1000 - (int) Math.floor(time);
        layout.setText(font, String.valueOf(score));
    }

    public int getScore() {
        return score;
    }
}
