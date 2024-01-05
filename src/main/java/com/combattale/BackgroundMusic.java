package com.combattale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.combattale.utils.Element;

public class BackgroundMusic extends Element {
    private Sound backgroundThemeSong;

    @Override
    public void create() {
        backgroundThemeSong = Gdx.audio.newSound(Gdx.files.internal("Sounds/megalovania.mp3"));
        backgroundThemeSong.loop();
    }

    @Override
    public void pause() {
        backgroundThemeSong.pause();
    }

    @Override
    public void resume() {
        backgroundThemeSong.resume();
    }

    @Override
    public void dispose() {
        backgroundThemeSong.dispose();
    }
}

