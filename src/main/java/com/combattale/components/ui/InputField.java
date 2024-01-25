package com.combattale.components.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Fonts;

import java.util.function.Consumer;

public class InputField extends Text {
    private final String label;
    private String content = "";
    private int cursor = 0;
    private Consumer<String> onChange;

    public InputField(String label) {
        super(label + ": ", Fonts.BODY_FONT);
        this.label = label;
        InputController controller = new InputController();
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        super.render(spriteBatch, shapeRenderer);

        final Vector2 pos = calcPosition(position, layout.width, layout.height);
        final GlyphLayout l = new GlyphLayout(font, text.substring(0, label.length() + 2 + cursor));

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(pos.x + offset.x + l.width + 1, pos.y + offset.y - 15, 2, 16);
        shapeRenderer.end();
    }

    private void updateText() {
        onChange.accept(content);
        text = label + ": " + content;
        layout.setText(font, text);
    }

    public Text withOnChange(Consumer<String> onChange) {
        this.onChange = onChange;
        return this;
    }

    class InputController implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.LEFT) {
                cursor = Math.max(cursor - 1, 0);
                return true;
            }
            if (keycode == Input.Keys.RIGHT) {
                cursor = Math.min(cursor + 1, content.length());
                return true;
            }
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char c) {
            if (c == 8 && !content.isEmpty()) {
                content = content.substring(0, cursor - 1) + content.substring(cursor);
                cursor = Math.clamp(cursor - 1, 0, content.length());
                updateText();
                return true;
            }
            if (Character.isAlphabetic(c) || Character.isDigit(c) || Character.isSpaceChar(c)) {
                content = content.substring(0, Math.max(0, cursor)) + c + content.substring(cursor);
                cursor = Math.clamp(cursor + 1, 0, content.length());
                updateText();
                return true;
            }
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
