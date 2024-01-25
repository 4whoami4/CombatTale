package com.combattale.scenes;

import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.components.ui.Button;
import com.combattale.components.ui.InputField;
import com.combattale.components.ui.Text;
import com.combattale.utils.Component;
import com.combattale.utils.Fonts;
import com.combattale.utils.Scene;
import com.combattale.utils.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VictoryScreen extends Scene {

    private final String scoreText;
    private final int score;
    private String name = "";

    public VictoryScreen(int score) {
        this.score = score;
        this.scoreText = "Score: " + score;
    }

    private void saveScore() {
        if (name.isBlank()) return;

        final Map<String, Integer> scores = new HashMap<>(Storage.getMap("scores"));
        final int current = scores.getOrDefault(name, 0);
        if (score > current) scores.put(name, score);

        Storage.setMap("scores", scores);
        Storage.commit();

        Game.instance.setScene(new MenuScene());
    }

    @Override
    public ArrayList<Component> build() {
        return new ArrayList<>() {{
            add(new Text("Victory", Fonts.TITLE_FONT).withOffset(new Vector2(0, 70)));
            add(new Text(scoreText, Fonts.BODY_FONT));
            add(
                new InputField("Name")
                    .withOnChange((value) -> name = value)
                    .withOffset(new Vector2(0, -35))
            );
            add(
                new Button("Save", Fonts.BODY_FONT)
                    .withOnClick(() -> saveScore())
                    .withOffset(new Vector2(0, -100))
            );
        }};
    }
}
