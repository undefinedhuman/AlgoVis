package de.undefinedhuman.algovis.engine.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.Main;

public class Window {

    public static Window instance;

    public Window() {
        new LwjglApplication(new Main(), createLWJGLConfig());
    }

    private LwjglApplicationConfiguration createLWJGLConfig() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Variables.NAME;
        config.width = 1280;
        config.height = 720;
        return config;
    }

    public void delete() {
        Gdx.app.exit();
    }

}