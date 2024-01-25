package com.combattale.components.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.combattale.utils.Fonts;
import com.combattale.utils.Storage;

import java.util.*;

public class LeadersList extends Text {

    public LeadersList() {
        super("", Fonts.BODY_FONT);
        withOffset(new Vector2(0, 30));
    }

    @Override
    public void create() {
        final Map<String, Integer> scores = new HashMap<>(Storage.getMap("scores"));
        final ArrayList<String> leaders = new ArrayList<>();
        scores.forEach((key, value) -> {
            final String score = String.valueOf(value);
            final int spacesToAdd = 6 - score.length();
            leaders.add(score + " ".repeat(spacesToAdd) + key);
        });
        leaders.sort((s1, s2) -> {
            int num1 = Integer.parseInt(s1.split(" ")[0]);
            int num2 = Integer.parseInt(s2.split(" ")[0]);
            return num2 - num1;
        });
        for (int i = scores.size(); i < 10; i++) leaders.add(" ");
        text = String.join("\n", leaders.subList(0, 10));
        layout.setText(font, text);
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        font.setFixedWidthGlyphs("0123456789 ");
        font.getData().setScale(1.2f);
        super.render(spriteBatch, shapeRenderer);
        font.setFixedWidthGlyphs("");
        font.getData().setScale(1f);
    }
}
