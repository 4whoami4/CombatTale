package com.combattale.components.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.utils.*;

import java.util.ArrayList;

public class DifficultySelector extends GuiComponent {
    private final Text title = new Text("Difficulty", Fonts.SUBTITLE_FONT)
            .withOffset(new Vector2(-115, 130));
    private final ArrayList<Button> buttons = new ArrayList<>() {{
        add(createButton("Easy", -1, Difficulty.EASY));
        add(createButton("Normal", 0, Difficulty.NORMAL));
        add(createButton("EXTREME", 1, Difficulty.EXTREME));
    }};
    private final ArrayList<Difficulty> difficulties = new ArrayList<>() {{
        add(Difficulty.EASY);
        add(Difficulty.NORMAL);
        add(Difficulty.EXTREME);
    }};

    @Override
    public void create() {
        title.create();
        buttons.forEach(Component::create);
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        title.render(spriteBatch, shapeRenderer);
        for (int i = 0; i < 3; i++) {
            final Button button = buttons.get(i);
            if (Game.instance.difficulty == difficulties.get(i)) {
                button.withTextColor(Color.GREEN);
            } else {
                button.withTextColor(Color.WHITE);
            }
            button.render(spriteBatch, shapeRenderer);
        }
    }

    @Override
    public void resize(int width, int height) {
        title.resize(width, height);
        buttons.forEach(Component::resume);
    }

    private Button createButton(String text, int i, Difficulty difficulty) {
        return new Button(text, Fonts.BODY_FONT)
                .withOnClick(() -> setDifficulty(difficulty))
                .withOffset(new Vector2(i * 150, 80))
                .withPadding(new Vector2(10, 10));
    }

    private void setDifficulty(Difficulty difficulty) {
        Storage.setInt("difficulty", switch (difficulty) {
            case EASY -> 0;
            case NORMAL -> 1;
            case EXTREME -> 2;
        });
        Game.instance.difficulty = difficulty;
    }
}
