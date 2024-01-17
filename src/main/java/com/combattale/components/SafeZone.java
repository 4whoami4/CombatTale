package com.combattale.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Component;
import com.combattale.utils.TimedList;

public class SafeZone extends Component {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 130;
    private static final boolean SHOW = true;

    private static final TimedList<Rectangle> sizes = new TimedList<>() {{
        add(0f, new Rectangle(0, 0, WIDTH, HEIGHT));
        add(2f, new Rectangle(0, 0, WIDTH, 50));
        add(4f, new Rectangle(0, HEIGHT - 50, WIDTH, 50));
        add(6f, new Rectangle(0, 0, WIDTH, HEIGHT));
    }};

    private Vector2 position;
    private Rectangle rectangle;
    private double currentTime = 0;

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        int borderThickness = 2;

        currentTime += Gdx.graphics.getDeltaTime();
        Rectangle size = sizes.get(currentTime);
        rectangle = new Rectangle(
                position.x + size.x,
                position.y + size.y,
                size.width,
                size.height
        );

        if (!SHOW) return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(
                rectangle.x,
                rectangle.y,
                rectangle.width,
                rectangle.height
        );
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                rectangle.x + borderThickness,
                rectangle.y + borderThickness,
                rectangle.width - borderThickness * 2,
                rectangle.height - borderThickness * 2
        );
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        position = new Vector2(
                width * 0.5f - WIDTH * 0.5f,
                height * 0.5f - HEIGHT * 0.5f - 150
        );
    }

    public boolean isInZone(Vector2 position) {
        return rectangle.contains(position);
    }
}
