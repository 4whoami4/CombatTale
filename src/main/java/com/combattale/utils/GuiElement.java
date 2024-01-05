package com.combattale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class GuiElement extends Element {

    protected void drawText(SpriteBatch batch, String text, BitmapFont font, GuiPosition position, Vector2 offset) {
        final GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        drawText(batch, layout, font, position, offset);
    }

    protected void drawText(SpriteBatch batch, String text, BitmapFont font, GuiPosition position) {
        drawText(batch, text, font, position, Vector2.Zero);
    }

    protected void drawText(SpriteBatch batch, GlyphLayout layout, BitmapFont font, GuiPosition position, Vector2 offset) {
        final Vector2 pos = calcPosition(position, layout.width, layout.height);
        font.draw(batch, layout, pos.x + offset.x, pos.y + offset.y);
    }

    protected void drawText(SpriteBatch batch, GlyphLayout layout, BitmapFont font, GuiPosition position) {
        drawText(batch, layout, font, position, Vector2.Zero);
    }

    protected void drawButton(SpriteBatch batch, BitmapFont font, GuiPosition position, Vector2 offset, float width, float height) {
        final Vector2 pos = calcPosition(position, width, height);
        final Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        drawText(batch, "(" + mousePos.x + "," + mousePos.y +")", font, GuiPosition.CENTER);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (mousePos.x > pos.x + offset.x && mousePos.x < pos.x + offset.x + width && mousePos.y > pos.y + offset.y && mousePos.y < pos.y + offset.y + height) {
            shapeRenderer.setColor(0.7f, 0.7f, 0.7f, 1);
        } else {
            shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        }

        shapeRenderer.rect(pos.x + offset.x, pos.y + offset.y, width, height);
        shapeRenderer.end();
    }

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
