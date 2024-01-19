package com.combattale.scenes;

import com.combattale.components.*;
import com.combattale.components.ui.*;
import com.combattale.controllers.*;
import com.combattale.utils.*;

import java.util.ArrayList;

public class FirstStageScene extends Scene {
    @Override
    public ArrayList<Component> build() {
        return new ArrayList<>() {{
            add(new BackgroundMusic());
            add(new MiniGameBorder());
            add(new SafeZone());
            add(new BossCharacter());
            add(new BossController());
            add(new PlayerHeart());
            add(new PlayerController());
            add(new Health());
            add(new Dialog());
        }};
    }
}
