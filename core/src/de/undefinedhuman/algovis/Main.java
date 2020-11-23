package de.undefinedhuman.algovis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import de.undefinedhuman.algovis.engine.config.ConfigManager;
import de.undefinedhuman.algovis.engine.config.SettingsManager;
import de.undefinedhuman.algovis.engine.language.LanguageManager;
import de.undefinedhuman.algovis.engine.window.Window;
import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.resources.font.FontManager;
import de.undefinedhuman.algovis.resources.texture.TextureManager;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.utils.ManagerList;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.gui.GuiManager;
import de.undefinedhuman.algovis.utils.Inputs;

public class Main extends Game {

    public static Main instance;

    public static float delta;
    public static int guiScale = 1;

    private ManagerList managerList;

    public Main() {
        if(instance == null) instance = this;
        managerList = new ManagerList();
        managerList.addManager(new Log(), new SettingsManager(), new ConfigManager(), new LanguageManager(), new TextureManager(), new FontManager(), new Inputs(), new GuiManager());
    }

    @Override
    public void create() {
        managerList.init();
        initScreens();
        setScreen(MainScreen.instance);
    }

    @Override
    public void resize(int width, int height) {
        int guiSetting = SettingsManager.instance.guiScale.getInt();
        guiScale = (int) Math.max((guiSetting == 5 ? Math.ceil(width/640f) : guiSetting) / 2, 1);
        managerList.resize(width, height);
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        delta = Gdx.graphics.getDeltaTime() * Variables.DELTA_MULTIPLIER;
        managerList.update(delta);
        clear();
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        managerList.delete();
        Window.instance.delete();
    }

    private void clear() {
        Gdx.gl.glClearColor(0.17f, 0.17f, 0.17f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    private void initScreens() {
        MainScreen.instance = new MainScreen();
    }

}
