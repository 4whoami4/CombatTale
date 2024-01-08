package com.combattale.scenes;

import com.badlogic.gdx.math.Vector2;
import com.combattale.components.ui.Button;
import com.combattale.components.ui.Text;
import com.combattale.utils.Component;
import com.combattale.utils.Fonts;
import com.combattale.utils.Scene;

import java.util.ArrayList;

public class MenuScene extends Scene {

    final

    @Override
    public ArrayList<Component> getComponents() {
        return new ArrayList<>() {{
            add(new Text("Main Menu", Fonts.TITLE_FONT));
            add(
                    new Button("Test", Fonts.BUTTON_FONT)
                            .withOnClick(() -> System.out.println("Clicked!"))
                            .withPadding(new Vector2(10, 10))
                            .withOffset(new Vector2(0, -100))
            );
        }};
    }
}
