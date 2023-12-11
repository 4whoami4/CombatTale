package com.combattale.utils;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GuiElement extends Element {
    protected Vector2 screenSize;

    protected void drawText(SpriteBatch batch, String text, BitmapFont font, GuiPosition position, Vector2 offset) {
        final GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        drawText(batch, layout, font, position, offset);
    }

    protected void drawText(SpriteBatch batch, String text, BitmapFont font, GuiPosition position) {
        drawText(batch, text, font, position, Vector2.Zero);
    }

    protected void drawText(SpriteBatch batch, GlyphLayout layout, BitmapFont font, GuiPosition position, Vector2 offset) {
        Vector2 xy = switch (position) {
            case LEFT_TOP -> new Vector2(0, screenSize.y);
            case CENTER_TOP -> new Vector2(screenSize.x * 0.5f - layout.width * 0.5f, screenSize.y);
            case RIGHT_TOP -> new Vector2(screenSize.x - layout.width, screenSize.y);
            case LEFT_CENTER -> new Vector2(0, screenSize.y * 0.5f + layout.height * 0.5f);
            case CENTER ->
                    new Vector2(screenSize.x * 0.5f - layout.width * 0.5f, screenSize.y * 0.5f + layout.height * 0.5f);
            case RIGHT_CENTER -> new Vector2(screenSize.x - layout.width, screenSize.y * 0.5f + layout.height * 0.5f);
            case LEFT_BOTTOM -> new Vector2(0, layout.height);
            case CENTER_BOTTOM -> new Vector2(screenSize.x * 0.5f - layout.width * 0.5f, layout.height);
            case RIGHT_BOTTOM -> new Vector2(screenSize.x - layout.width, layout.height);
        };
        font.draw(batch, layout, xy.x + offset.x, xy.y + offset.y);
    }

    protected void drawText(SpriteBatch batch, GlyphLayout layout, BitmapFont font, GuiPosition position) {
        drawText(batch, layout, font, position, Vector2.Zero);
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        screenSize = new Vector2(width, height);
    }
}
