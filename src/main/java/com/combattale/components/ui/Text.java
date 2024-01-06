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

    private GuiPosition position = GuiPosition.CENTER;
    private Vector2 offset = Vector2.Zero;

    public Text(String text, BitmapFont font) {
        this.font = font;
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
        font.draw(spriteBatch, layout, pos.x + offset.x, pos.y + offset.y);
    }
}
