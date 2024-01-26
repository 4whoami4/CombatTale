package com.combattale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.combattale.scenes.MenuScene;
import com.combattale.utils.Difficulty;
import com.combattale.utils.Scene;
import com.combattale.utils.Storage;

public class Game extends ApplicationAdapter {
    public static Game instance;

    public float masterVolume = 1f;
    public Difficulty difficulty = Difficulty.NORMAL;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Scene activeScene;

    public Game() {
        if (instance != null) {
            throw new IllegalStateException("Instance of Game already exists!");
        }
        instance = this;
    }

    @Override
    public void create() {
        masterVolume = (float) Storage.getDouble("volume", 1);
        difficulty = switch (Storage.getInt("difficulty", 1)) {
            case 0 -> Difficulty.EASY;
            case 2 -> Difficulty.EXTREME;
            default -> Difficulty.NORMAL;
        };

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        setScene(new MenuScene());
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        activeScene.render(spriteBatch, shapeRenderer);

        activeScene.keyboardEvent(Gdx.input, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        activeScene.resize(width, height);
    }

    @Override
    public void pause() {
        activeScene.pause();
    }

    @Override
    public void resume() {
        activeScene.resume();
    }

    @Override
    public void dispose() {
        activeScene.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public void setScene(Scene scene) {
        if (activeScene != null) {
            activeScene.dispose();
        }
        activeScene = scene;
        activeScene.create();
        activeScene.resize(
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );
    }
}
