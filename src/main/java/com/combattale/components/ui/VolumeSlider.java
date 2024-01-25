package com.combattale.components.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.utils.Fonts;
import com.combattale.utils.GuiComponent;
import com.combattale.utils.GuiPosition;
import com.combattale.utils.Storage;

public class VolumeSlider extends GuiComponent {
    private final Text title = new Text("Volume", Fonts.SUBTITLE_FONT)
            .withOffset(new Vector2(-140, 40));
    private final Button plusButton = new Button("+", Fonts.BODY_FONT)
            .withColor(Color.WHITE)
            .withHoverColor(Color.WHITE)
            .withTextColor(Color.BLACK)
            .withOffset(new Vector2(160, -10))
            .withPadding(new Vector2(10, 6))
            .withOnClick(this::increaseVolume);
    private final Button minusButton = new Button("-", Fonts.BODY_FONT)
            .withColor(Color.WHITE)
            .withHoverColor(Color.WHITE)
            .withTextColor(Color.BLACK)
            .withOffset(new Vector2(-160, -10))
            .withPadding(new Vector2(10, 6))
            .withOnClick(this::decreaseVolume);

    @Override
    public void create() {
        title.create();
        plusButton.create();
        minusButton.create();
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        title.render(spriteBatch, shapeRenderer);
        plusButton.render(spriteBatch, shapeRenderer);
        minusButton.render(spriteBatch, shapeRenderer);

        final Vector2 pos = calcPosition(GuiPosition.CENTER, 274, 26);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        for (int i = 0; i < 20; i++) {
            if (i * 0.05f > Game.instance.masterVolume - 0.01f)
                shapeRenderer.setColor(Color.GRAY);
            shapeRenderer.rect(pos.x + 14 * i, pos.y - 22, 8, 26);
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        title.resize(width, height);
        plusButton.resize(width, height);
        minusButton.resize(width, height);
    }

    private void increaseVolume() {
        float volume = Game.instance.masterVolume;
        volume = Math.clamp(volume + 0.05f, 0, 1);
        Game.instance.masterVolume = volume;
        Storage.setDouble("volume", volume);
    }

    private void decreaseVolume() {
        float volume = Game.instance.masterVolume;
        volume = Math.clamp(volume - 0.05f, 0, 1);
        Game.instance.masterVolume = volume;
        Storage.setDouble("volume", volume);
    }
}
