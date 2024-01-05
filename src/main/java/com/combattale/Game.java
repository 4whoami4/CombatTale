package com.combattale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import com.combattale.components.*;
import com.combattale.utils.Component;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
    private final ArrayList<Component> components = new ArrayList<>() {{
        add(new PlayerHeart());
        add(new BossCharacter());
        add(new MainMenu());
        add(new BackgroundMusic());
    }};

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FirstMiniGameBorder firstGameBorder;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        batch = new SpriteBatch();
        components.forEach(Component::create);
        firstGameBorder = new FirstMiniGameBorder();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        components.forEach((e) -> e.render(batch));
        batch.end();
        components.forEach((e) -> e.keyboardEvent(Gdx.input, Gdx.graphics.getDeltaTime()));
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        components.forEach((e) -> e.resize(width, height));
    }

    public void pause() {
        components.forEach(Component::pause);
    }

    public void resume() {
        components.forEach(Component::resume);
    }

    @Override
    public void dispose() {
        components.forEach(Component::dispose);
        firstGameBorder.dispose();
        batch.dispose();
    }
}
