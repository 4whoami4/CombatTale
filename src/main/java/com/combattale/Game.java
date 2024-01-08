package com.combattale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.combattale.components.FirstMiniGameBorder;
import com.combattale.scenes.FirstStageScene;
import com.combattale.scenes.MenuScene;
import com.combattale.utils.Scene;

public class Game extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Scene activeScene;

    private FirstMiniGameBorder firstGameBorder;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        firstGameBorder = new FirstMiniGameBorder();

        setScene(new FirstStageScene());
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

        int borderX = FirstMiniGameBorder.START_X_POSITION_MINIGAME_BORDER; // Adjust the X position as needed
        int borderY = FirstMiniGameBorder.START_Y_POSITION_MINIGAME_BORDER; // Adjust the Y position as needed
        int borderWidth = FirstMiniGameBorder.WIDTH_POSITION_MINIGAME_BORDER; // Adjust the width as needed
        int borderHeight = FirstMiniGameBorder.HEIGHT_POSITION_MINIGAME_BORDER; // Adjust the height as needed
        int borderThickness = 4; // Adjust the thickness as needed

        for (int i = 0; i < borderThickness; i++) {
            int adjustedX = borderX - i;
            int adjustedY = borderY - i;
            int adjustedWidth = borderWidth + i * 2;
            int adjustedHeight = borderHeight + i * 2;
            Color borderColor = Color.WHITE; // Adjust the color as needed

            firstGameBorder.render(adjustedX, adjustedY, adjustedWidth, adjustedHeight, borderColor);
        }
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

    private void setScene(Scene scene) {
        if (activeScene != null) {
            activeScene.dispose();
        }
        activeScene = scene;
        scene.create();
    }
}
