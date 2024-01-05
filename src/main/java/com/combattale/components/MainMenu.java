/*package com.combattale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.GuiElement;
import com.combattale.utils.GuiPosition;

public class MainMenu extends GuiElement {
    private BitmapFont font;
    private GlyphLayout title;

    @Override
    public void create() {
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/DeterminationSansWebRegular-369X.ttf")
        );
        final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 72;

        font = generator.generateFont(parameter);
        title = new GlyphLayout(font, "Combat Tale");

        generator.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        drawText(batch, title, font, GuiPosition.CENTER_TOP, new Vector2(0, -120));
        drawButton(batch, font, GuiPosition.CENTER, Vector2.Zero, 200, 50);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
*/
package com.combattale.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.combattale.utils.GuiComponent;
import com.combattale.utils.GuiPosition;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MainMenu extends GuiComponent {
    private Rectangle[] buttonBounds;

    private BitmapFont font;
    private GlyphLayout title;
    private String[] menuItems = {"Start", "Options", "Exit"};
    private int currentSelection = -1;


    @Override
    public void create() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/DeterminationSansWebRegular-369X.ttf")
              );
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);
        title = new GlyphLayout(font, "Combat Tale");
        generator.dispose();

        buttonBounds = new Rectangle[menuItems.length];
        for (int i = 0; i < menuItems.length; i++) {
            buttonBounds[i] = new Rectangle(0, -150 - (i * 60), 200, 50); // Adjust as necessary
        }
    }


    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        // Render the title
        drawText(spriteBatch, title, font, GuiPosition.CENTER_TOP, new Vector2(0, -100));

        // Render the menu items as buttons
        for (int i = 0; i < menuItems.length; i++) {
            boolean isSelected = (i == currentSelection);

            // Adjust these values as necessary for positioning and sizing
            Vector2 buttonPosition = new Vector2(0, -150 - (i * 60)); // Example positioning
            float buttonWidth = 200;
            float buttonHeight = 50;

            drawButton(spriteBatch, font, GuiPosition.CENTER, buttonPosition, buttonWidth, buttonHeight, menuItems[i], isSelected);
        }
    }

    private void drawButton(SpriteBatch batch, BitmapFont font, GuiPosition position, Vector2 offset, float width, float height, String text, boolean isSelected) {
        // Calculate the position of the button
        Vector2 pos = calcPosition(position, width, height);

        // Set up a ShapeRenderer for drawing rectangles (buttons)
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw the button
        // Change the color based on whether the button is selected
        if (isSelected) {
            shapeRenderer.setColor(Color.GREEN);
        } else {
            shapeRenderer.setColor(Color.GRAY);
        }
        shapeRenderer.rect(pos.x + offset.x, pos.y + offset.y, width, height);
        shapeRenderer.end();

        GlyphLayout layout = new GlyphLayout(font, text);
        float textX = pos.x + offset.x + (width / 2) - (layout.width / 2);
        float textY = pos.y + offset.y + (height / 2) + (font.getLineHeight() / 2);
        font.draw(batch, layout, textX, textY);
    }




    public void update() {
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        for (int i = 0; i < buttonBounds.length; i++) {
            if (buttonBounds[i].contains(mousePos.x, Gdx.graphics.getHeight() - mousePos.y)) {
                currentSelection = i;
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    selectMenuItem();
                }
                break;
            } else {
                currentSelection = -1;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            currentSelection--;
            if (currentSelection < 0) currentSelection = menuItems.length - 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentSelection++;
            if (currentSelection >= menuItems.length) currentSelection = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            selectMenuItem();
        }
    }

    private void selectMenuItem() {
        if (currentSelection >= 0) {
            switch (menuItems[currentSelection]) {
                case "Start":
                    // Start the game
                    break;
                case "Options":
                    // Open options menu
                    break;
                case "Exit":
                    Gdx.app.exit();
                    break;
            }
        }
    }

    @Override
    public void dispose() {
        if (font != null) {
            font.dispose();
        }
    }
}