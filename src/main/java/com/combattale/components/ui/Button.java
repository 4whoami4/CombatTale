package com.combattale.components.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.GuiPosition;

public class Button extends Text {

    private Vector2 padding = Vector2.Zero;
    private Color color = Color.BLUE;
    private Color hoverColor = Color.RED;
    private Runnable onClick;

    public Button(String text, BitmapFont font) {
        super(text, font);
    }

    public Button withPadding(Vector2 padding) {
        this.padding = padding;
        return this;
    }

    public Button withColor(Color color) {
        this.color = color;
        return this;
    }

    public Button withHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        return this;
    }

    public Button withOnClick(Runnable onClick) {
        this.onClick = onClick;
        return this;
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        final Vector2 pos = calcPosition(position, layout.width, layout.height);
        final Vector2 mousePos = getMousePosition();
        final Rectangle rect = new Rectangle(
                pos.x - padding.x + offset.x,
                pos.y - padding.y + offset.y,
                layout.width + padding.x * 2,
                layout.height + padding.y * 2
        );

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        if (rect.contains(mousePos)) {
            shapeRenderer.setColor(hoverColor);
        } else {
            shapeRenderer.setColor(color);
        }
        shapeRenderer.end();

        spriteBatch.begin();
        font.draw(
                spriteBatch,
                layout,
                pos.x + offset.x,
                pos.y + layout.height + offset.y
        );
        spriteBatch.end();
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (!input.isButtonPressed(Input.Buttons.LEFT) || onClick == null) return;

        final Vector2 mousePos = getMousePosition();
        final Vector2 pos = calcPosition(position, layout.width, layout.height);
        final Rectangle rect = new Rectangle(
                pos.x - padding.x + offset.x,
                pos.y - padding.y + offset.y,
                layout.width + padding.x * 2,
                layout.height + padding.y * 2
        );

        if (rect.contains(mousePos)) {
            onClick.run();
        }
    }

    private Vector2 getMousePosition() {
        return new Vector2(
                Gdx.input.getX(),
                screenSize.y - Gdx.input.getY()
        );
    }
}
