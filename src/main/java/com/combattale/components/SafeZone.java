package com.combattale.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.combattale.Game;
import com.combattale.utils.Component;
import com.combattale.utils.Difficulty;
import com.combattale.utils.TimedList;

public class SafeZone extends Component {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 130;
    private static final boolean SHOW = true;

    private static final TimedList<Rectangle> sizes = new TimedList<>() {{
        add(0f, new Rectangle(0, 0, WIDTH, HEIGHT)); //full green
        add(1.3f, new Rectangle(0, 0, WIDTH, 70)); //green up
        add(2.6f, new Rectangle(0, HEIGHT - 70, WIDTH, 70)); //green down
        add(3.9f, new Rectangle(0, 0, WIDTH - 200, HEIGHT)); // green left
        add(5.2f, new Rectangle(WIDTH - 200, 0, 200, HEIGHT)); // green right


        add(7.2f, new Rectangle(0, 0, WIDTH, HEIGHT)); //full green
        add(8.5f, new Rectangle(0, 0, WIDTH - 200, HEIGHT)); // green left
        add(9.8f, new Rectangle(0, HEIGHT - 70, WIDTH, 70)); //green down
        add(11.1f, new Rectangle(WIDTH - 200, 0, 200, HEIGHT)); // green right
        add(12.4f, new Rectangle(0, 0, WIDTH, 70)); //green up


        add(14.4f, new Rectangle(0, 0, WIDTH, HEIGHT)); //full green
        add(15.7f, new Rectangle(0, 0, WIDTH, 70)); //green up
        add(18f, new Rectangle(0, HEIGHT - 70, WIDTH, 70)); //green down
        add(19.3f, new Rectangle(0, 0, WIDTH - 200, HEIGHT)); // green left
        add(20.6f, new Rectangle(WIDTH - 200, 0, 200, HEIGHT)); // green right

        add(22.6f, new Rectangle(0, 0, WIDTH, HEIGHT)); //full green
        add(23.9f, new Rectangle(0, 0, WIDTH, 70)); //green up
        add(25.2f, new Rectangle(0, HEIGHT - 70, WIDTH, 70)); //green down
        add(26.5f, new Rectangle(0, 0, WIDTH - 200, HEIGHT)); // green left
        add(27.8f, new Rectangle(WIDTH - 200, 0, 200, HEIGHT)); // green right

        add(29.8f, new Rectangle(0, 0, WIDTH, HEIGHT)); //full green
        add(30.9f, new Rectangle(0, 0, WIDTH, 70)); //green up
        add(32.2f, new Rectangle(0, HEIGHT - 70, WIDTH, 70)); //green down
        add(33.5f, new Rectangle(0, 0, WIDTH - 200, HEIGHT)); // green left
        add(34.8f, new Rectangle(WIDTH - 200, 0, 200, HEIGHT)); // green right

        add(36.8f, new Rectangle(0, 0, WIDTH, HEIGHT)); //full green
        add(38.1f, new Rectangle(0, 0, WIDTH, 70)); //green up
        add(41.4f, new Rectangle(0, HEIGHT - 70, WIDTH, 70)); //green down
        add(42.7f, new Rectangle(0, 0, WIDTH - 200, HEIGHT)); // green left
        add(46f, new Rectangle(WIDTH - 200, 0, 200, HEIGHT)); // green right

        add(49f, new Rectangle(0 , 0, 0, 0)); // dead

    }};

    private Vector2 position;
    private Rectangle rectangle;
    private double currentTime = 0;
    public boolean isPaused = true;
    public boolean isVisible = true;

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        int borderThickness = 4;

        if (!isVisible) return;
        if (!isPaused) currentTime += Gdx.graphics.getDeltaTime() * getSpeed();

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
                rectangle.x, rectangle.y,
                rectangle.width, borderThickness
        );
        shapeRenderer.rect(
                rectangle.x, rectangle.y + rectangle.height - borderThickness,
                rectangle.width, borderThickness
        );
        shapeRenderer.rect(
                rectangle.x, rectangle.y,
                borderThickness, rectangle.height
        );
        shapeRenderer.rect(
                rectangle.x + rectangle.width - borderThickness, rectangle.y,
                borderThickness, rectangle.height
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

    public void reset() {
        currentTime = 0;
    }

    private double getSpeed() {
        return switch (Game.instance.difficulty) {
            case Difficulty.EASY -> 0.4;
            case Difficulty.NORMAL ->1;
            case Difficulty.EXTREME -> 1.5;
        };
    }
}
