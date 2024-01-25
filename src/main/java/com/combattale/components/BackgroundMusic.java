package com.combattale.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.combattale.Game;
import com.combattale.utils.Component;

public class BackgroundMusic extends Component {
    private Sound backgroundThemeSong;

    @Override
    public void create() {
        backgroundThemeSong = Gdx.audio.newSound(Gdx.files.internal("Sounds/megalovania.mp3"));
        backgroundThemeSong.loop(Game.instance.masterVolume);
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

