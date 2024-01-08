package com.combattale.scenes;

import com.combattale.components.BackgroundMusic;
import com.combattale.components.BossCharacter;
import com.combattale.components.FirstMiniGameBorder;
import com.combattale.components.PlayerHeart;
import com.combattale.utils.Component;
import com.combattale.utils.Scene;

import java.util.ArrayList;

public class FirstStageScene extends Scene {
    @Override
    public ArrayList<Component> getComponents() {
        return new ArrayList<>() {{
            add(new PlayerHeart());
            add(new BossCharacter());
            add(new FirstMiniGameBorder());
            add(new BackgroundMusic());
        }};
    }
}
