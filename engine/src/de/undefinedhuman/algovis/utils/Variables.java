package de.undefinedhuman.algovis.utils;

import com.badlogic.gdx.graphics.Color;

public class Variables {
    // Engine
    public static final String NAME = "AlgoVis";

    public static final float GAME_CAMERA_ZOOM = 3f;
    public static final float DELTA_MULTIPLIER = 1.0f;
    public static final String SEPARATOR = ";";
    public static final String FILE_SEPARATOR = "/";
    public static final String DEFAULT_LANGUAGE = "eu_DE";

    public static final boolean DEBUG = false;
    public static final Color HITBOX_COLOR = new Color(0.41568628f, 0.3529412f, 0.8039216f, 0.4f);

    // User interface
    public static final int BASE_WINDOW_WIDTH = 1280;
    public static final int BASE_WINDOW_HEIGHT = 720;

    // Algorithms
    public static final int AMOUNT_OF_ELEMENTS = 25;
    public static final float SPACE = (AMOUNT_OF_ELEMENTS * 0.01f) / AMOUNT_OF_ELEMENTS;

    public static final Color DARK_GREY = new Color(0.17f, 0.17f, 0.17f, 1);
    public static final Color LIGHT_GREY = new Color(0.22f, 0.22f, 0.22f, 1);

    public static final Color PURPLE = new Color(144/255f, 128/255f, 255f/255f, 1);
    public static final Color BLUE = new Color(44/255f, 151/255f, 222/255f, 1);
    public static final Color GREEN = new Color(44/255f, 191/255f, 109/255f, 1);

    public static final Color LIGHT_PINK = new Color(227f/255f, 120f/255f, 132f/255f, 1);
    public static final Color DARK_PINK = new Color(218f/255f, 68f/255f, 85f/255f, 1);
}
