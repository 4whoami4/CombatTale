package com.combattale.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class Scene extends Component {
    private static Scene instance;
    private ArrayList<Component> components = new ArrayList<>();

    protected Scene() {
        instance = this;
    }

    public abstract ArrayList<Component> getComponents();
    public static <T extends Component> T getComponent(Class<T> type) {
        for (Component c : instance.components) {
            if (c.getClass().equals(type))
                return (T) c;
        }
        throw new NoSuchElementException("Component not found");
    }

    @Override
    public void create() {
        components = getComponents();
        components.forEach(Component::create);
    }

    @Override
    public void resize(int width, int height) {
        components.forEach((e) -> e.resize(width, height));
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        components.forEach((e) -> e.render(spriteBatch, shapeRenderer));
    }

    @Override
    public void pause() {
        components.forEach(Component::pause);
    }

    @Override
    public void resume() {
        components.forEach(Component::resume);
    }

    @Override
    public void dispose() {
        components.forEach(Component::dispose);
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        components.forEach((e) -> e.keyboardEvent(input, deltaTime));
    }
}
