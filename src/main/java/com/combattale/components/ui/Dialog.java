package com.combattale.components.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.combattale.utils.Fonts;
import com.combattale.utils.GuiComponent;
import com.combattale.utils.GuiPosition;

public class Dialog extends GuiComponent {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 180;
    private static final int BORDER = 4;

    private float currentTime = 0;
    private String content;

    public void show(String content, Runnable onEnd) {
        this.content = content;
        this.currentTime = 0;

        int time = (int) (content.length() / 20f);
        runDelayed(onEnd, time);
    }

    public void hide() {
        this.content = null;
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        if (content == null) return;

        Vector2 pos = calcPosition(GuiPosition.CENTER_BOTTOM, WIDTH, HEIGHT).sub(0, HEIGHT * 0.7f);
        currentTime += Gdx.graphics.getDeltaTime();

        int letters = (int) (currentTime * 20);
        String txt = content.substring(0, Math.min(letters, content.length()));

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(pos.x, pos.y, WIDTH, HEIGHT);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                pos.x + BORDER, pos.y + BORDER,
                WIDTH - BORDER * 2, HEIGHT - BORDER * 2
        );
        shapeRenderer.end();

        spriteBatch.begin();
        Fonts.BODY_FONT.draw(
                spriteBatch, txt,
                pos.x + 16, pos.y + HEIGHT - 20,
                WIDTH - 32, Align.left, true
        );
        spriteBatch.end();
    }
}
