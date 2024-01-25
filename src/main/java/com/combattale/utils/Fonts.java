package com.combattale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;

public class Fonts {
    public static final BitmapFont TITLE_FONT;
    public static final BitmapFont HIT_FONT;
    public static final BitmapFont BUTTON_FONT;

    static {
        TITLE_FONT = createFont("DeterminationSansWebRegular-369X", new FreeTypeFontParameter() {{
            size = 92;
        }});
        HIT_FONT = createFont("DeterminationSansWebRegular-369X", new FreeTypeFontParameter() {{
            size = 86;
            borderColor = Color.WHITE;
            borderWidth = 4;
            color = Color.BLACK;

        }});
        BUTTON_FONT = createFont("DeterminationSansWebRegular-369X", new FreeTypeFontParameter() {{
            size = 24;
        }});
    }

    private static BitmapFont createFont(String fontName, FreeTypeFontParameter params) {
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/" + fontName + ".ttf")
        );
        final BitmapFont font = generator.generateFont(params);
        generator.dispose();
        return font;
    }
}
