package com.combattale.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.components.ui.Button;
import com.combattale.components.ui.Text;
import com.combattale.utils.Component;
import com.combattale.utils.Fonts;
import com.combattale.utils.Scene;

import java.util.ArrayList;

public class GameOverScene extends Scene {
    private final ArrayList<String> buttonTexts = new ArrayList<>() {{
        add("Retry");
        add("Exit");
    }};
    private final ArrayList<Runnable> buttonActions = new ArrayList<>() {{
        add(() -> Game.instance.setScene(new FirstStageScene()));
        add(() -> Game.instance.setScene(new MenuScene()));
    }};

    private Button createButton(String text, Vector2 offset, Runnable onClickAction) {
        return new Button(text, Fonts.BODY_FONT)
                .withOnClick(onClickAction)
                .withPadding(new Vector2(10, 10))
                .withTextColor(Color.WHITE)
                .withTextHoverColor(Color.GREEN)
                .withColor(Color.BLACK)
                .withHoverColor(Color.BLACK)
                .withOffset(offset);
    }

    @Override
    public ArrayList<Component> build() {
        final ArrayList<Component> components = new ArrayList<>();
        components.add(
                new Text("GameOver", Fonts.TITLE_FONT).withOffset(new Vector2(0, 20))
        );
        for (int i = 0; i < buttonTexts.size(); i++) {
            components.add(
                    createButton(buttonTexts.get(i), new Vector2(i * 100 - 50, -100), buttonActions.get(i))
            );
        }
        return components;
    }
}
