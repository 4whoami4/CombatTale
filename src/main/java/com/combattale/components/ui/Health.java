package com.combattale.components.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.scenes.GameOverScene;
import com.combattale.utils.Fonts;
import com.combattale.utils.GuiComponent;
import com.combattale.utils.GuiPosition;

public class Health extends GuiComponent {
    private final GlyphLayout layout = new GlyphLayout();
    private final BitmapFont font = Fonts.BODY_FONT;
    private int health = 300;

    @Override
    public void create() {
        layout.setText(font, String.valueOf(health));
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        Vector2 pos = calcPosition(GuiPosition.LEFT_BOTTOM, 160, layout.height);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        shapeRenderer.rect(pos.x + 64, pos.y + 14, 128, 21);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(pos.x + 64, pos.y + 14, 128 * health / 300f, 21);
        shapeRenderer.end();

        spriteBatch.begin();
        font.draw(spriteBatch, "HP", pos.x + 32, pos.y + 32);
        font.draw(spriteBatch, layout, pos.x + 68, pos.y + 32);
        spriteBatch.end();
    }

    public void decrease(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            Game.instance.setScene(new GameOverScene());
        }
        layout.setText(font, String.valueOf(health));
    }

    public void increase(int amount) {
        health += amount;
        if (health > 300) health = 300;
        layout.setText(font, String.valueOf(health));
    }
}
