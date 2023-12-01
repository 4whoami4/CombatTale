package com.combattale;

import nl.saxion.app.SaxionApp;

public class Main {
    static final int WINDOW_WIDTH = 1024;
    static final int WINDOW_HEIGHT = 640;
    static final int REFRESH_RATE = 16;

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new Game(), WINDOW_WIDTH, WINDOW_HEIGHT, REFRESH_RATE);
    }
}
