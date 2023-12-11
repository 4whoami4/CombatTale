package com.combattale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import com.combattale.utils.Element;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
    private final ArrayList<Element> elements = new ArrayList<>() {{
        add(new PlayerHeart());
        add(new BossCharacter());
    }};

    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        batch = new SpriteBatch();
        elements.forEach(Element::create);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        elements.forEach((e) -> e.render(batch));
        batch.end();
        elements.forEach((e) -> e.keyboardEvent(Gdx.input, Gdx.graphics.getDeltaTime()));
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        elements.forEach((e) -> e.resize(width, height));
    }

    public void pause() {
        elements.forEach(Element::pause);
    }

    public void resume() {
        elements.forEach(Element::resume);
    }

    @Override
    public void dispose() {
        elements.forEach(Element::dispose);
        batch.dispose();
    }
}
