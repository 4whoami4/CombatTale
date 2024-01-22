package com.combattale.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class Scene extends Component {
    private ArrayList<Component> components = new ArrayList<>();

    public abstract ArrayList<Component> build();

    public <T extends Component> ArrayList<T> getComponents(Class<T> type) {
        ArrayList<T> result = new ArrayList<>();
        for (Component component : components) {
            if (type.isInstance(component)) {
                result.add(type.cast(component));
            }
        }
        return result;
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        throw new NoSuchElementException("Component not found");
    }

    @Override
    public void create() {
        components = build();
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

    public void keyboardEvent(Input input, float deltaTime) {
        components.forEach((e) -> {
            if (e instanceof Controller) {
                ((Controller) e).keyboardEvent(input, deltaTime);
            }
        });
    }
}
