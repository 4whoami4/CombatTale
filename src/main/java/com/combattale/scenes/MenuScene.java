package com.combattale.scenes;

import com.combattale.components.*;
import com.combattale.utils.Component;
import com.combattale.utils.Scene;

import java.util.ArrayList;

public class MenuScene extends Scene {

    @Override
    public ArrayList<Component> getComponents() {
        return new ArrayList<>() {{
            add(new MainMenu());
        }};
    }
}
