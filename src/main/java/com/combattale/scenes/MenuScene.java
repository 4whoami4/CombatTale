package com.combattale.scenes;

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
        }};
    }
}
