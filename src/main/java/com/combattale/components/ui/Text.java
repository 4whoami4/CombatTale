package com.combattale.components.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.GuiComponent;
import com.combattale.utils.GuiPosition;

public class Text extends GuiComponent {
    final GlyphLayout layout = new GlyphLayout();
    final BitmapFont font;

    protected GuiPosition position = GuiPosition.CENTER;
    protected Vector2 offset = Vector2.Zero;
    protected String text;

    public Text(String text, BitmapFont font) {
        this.font = font;
        this.text = text;
        layout.setText(font, text);
    }

    public Text withPosition(GuiPosition position) {
        this.position = position;
        return this;
    }

    public Text withOffset(Vector2 offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        final Vector2 pos = calcPosition(position, layout.width, layout.height);
        spriteBatch.begin();
        font.draw(spriteBatch, text, pos.x + offset.x, pos.y + offset.y);
        spriteBatch.end();
    }
}
