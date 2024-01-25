package com.combattale.scenes;

import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.components.ui.*;
import com.combattale.utils.Component;
import com.combattale.utils.Fonts;
import com.combattale.utils.Scene;
import com.combattale.utils.Storage;

import java.util.ArrayList;

public class OptionsScene extends Scene {
    @Override
    public ArrayList<Component> build() {
        return new ArrayList<>() {{
            add(
                    new Text("Options", Fonts.TITLE_FONT)
                            .withOffset(new Vector2(0, 210))
            );
            add(new DifficultySelector());
            add(new VolumeSlider());
            add(
                    new Button("Back", Fonts.BODY_FONT)
                            .withPadding(new Vector2(10, 10))
                            .withOffset(new Vector2(0, -70))
                            .withOnClick(() -> goBack())
            );
            add(new FpsCounter());
        }};
    }

    private void goBack() {
        Storage.commit();
        Game.instance.setScene(new MenuScene());
    }
}
