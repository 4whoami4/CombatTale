package com.combattale.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.combattale.components.ui.Button;
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
        add(() -> System.out.println("Start Game clicked"));
        add(() -> System.out.println("Options clicked"));
        add(() -> System.out.println("Leaderboard clicked"));
        add(() -> System.out.println("Exit clicked"));
    }};

    private Button createButton(String text, Vector2 offset, Runnable onClickAction) {
        return new Button(text, Fonts.BUTTON_FONT)
                .withOnClick(onClickAction)
                .withPadding(new Vector2(10, 10))
                .withTextColor(Color.CHARTREUSE)
                .withTextHoverColor(Color.CORAL)
                .withOffset(offset);
    }

    @Override
    public ArrayList<Component> getComponents() {
        final ArrayList<Component> components = new ArrayList<>();
        components.add(
                new Text("Main Menu", Fonts.TITLE_FONT).withOffset(new Vector2(0, 210))
        );
        for (int i = 0; i < buttonTexts.size(); i++) {
            components.add(
                    createButton(buttonTexts.get(3 - i), new Vector2(0, i * 60 - 180), buttonActions.get(3 - i))
            );
        }
        return components;
    }
}
