package com.combattale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.combattale.utils.Element;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
    private final ArrayList<Element> elements = new ArrayList<>() {{
        add(new PlayerHeart());
    }};

    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        elements.forEach(Element::create);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        elements.forEach((e) -> e.render(batch));
        batch.end();
        elements.forEach((e) -> e.keyboardEvent(Gdx.input, Gdx.graphics.getDeltaTime()));
    }

    public void resize(int width, int height) {
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
