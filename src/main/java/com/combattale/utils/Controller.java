package com.combattale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Controller extends Component {
    public void update(float deltaTime) {
    }

    @Override
    public final void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        update(Gdx.graphics.getDeltaTime());
    }

    public void keyboardEvent(Input input, float deltaTime) {
    }
}
