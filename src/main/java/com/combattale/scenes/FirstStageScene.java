package com.combattale.scenes;

import com.combattale.components.*;
import com.combattale.components.ui.Health;
import com.combattale.utils.Component;
import com.combattale.utils.Scene;

import java.util.ArrayList;

public class FirstStageScene extends Scene {
    @Override
    public ArrayList<Component> getComponents() {
        return new ArrayList<>() {{
            add(new BackgroundMusic());
            add(new MiniGameBorder());
            add(new SafeZone());
            add(new BossCharacter());
            add(new PlayerHeart());
            add(new Health());
        }};
    }
}
