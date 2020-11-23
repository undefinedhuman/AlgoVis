package de.undefinedhuman.algovis.engine.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import de.undefinedhuman.algovis.utils.Manager;

public class CameraManager extends Manager {

    public static CameraManager instance;

    public static OrthographicCamera camera = new OrthographicCamera();

    public CameraManager() {
        if(instance == null) instance = this;
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

}
