package com.combattale;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    static final int WINDOW_WIDTH = 1024;
    static final int WINDOW_HEIGHT = 640;
    static final String WINDOW_NAME = "Combat Tale";

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = WINDOW_WIDTH;
        config.height = WINDOW_HEIGHT;
        config.title = WINDOW_NAME;
        new LwjglApplication(new Game(), config);
    }
}
