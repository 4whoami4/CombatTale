package com.combattale.scenes;

import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.components.ui.Button;
import com.combattale.components.ui.FpsCounter;
import com.combattale.components.ui.Text;
import com.combattale.utils.Component;
import com.combattale.utils.Fonts;
import com.combattale.utils.Scene;

import java.util.ArrayList;

public class MenuScene extends Scene {
    private final ArrayList<String> buttonTexts = new ArrayList<>() {{
        add("Start Game");
        add("Options");
        add("Leaderboard");
        add("Exit");
    }};
    private final ArrayList<Runnable> buttonActions = new ArrayList<>() {{
        add(() -> Game.instance.setScene(new FirstStageScene()));
        add(() -> Game.instance.setScene(new OptionsScene()));
        add(() -> Game.instance.setScene(new LeaderboardScene()));
        add(() -> System.exit(0));
    }};

    private Button createButton(String text, Vector2 offset, Runnable onClickAction) {
        return new Button(text, Fonts.BODY_FONT)
                .withOnClick(onClickAction)
                .withPadding(new Vector2(10, 10))
                .withOffset(offset);
    }

    @Override
    public ArrayList<Component> build() {
        final ArrayList<Component> components = new ArrayList<>() {{
            add(new Text("Combat Tale", Fonts.TITLE_FONT).withOffset(new Vector2(0, 210)));
            add(new FpsCounter());
        }};
        for (int i = 0; i < buttonTexts.size(); i++) {
            components.add(
                    createButton(buttonTexts.get(3 - i), new Vector2(0, i * 60 - 180), buttonActions.get(3 - i))
            );
        }
        return components;
    }
}

