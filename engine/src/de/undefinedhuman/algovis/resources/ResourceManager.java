package de.undefinedhuman.algovis.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import de.undefinedhuman.algovis.file.Paths;
import de.undefinedhuman.algovis.log.Log;

public class ResourceManager {

    public static Texture loadTexture(String path) {
        Texture texture = null;
        try { texture = new Texture(Gdx.files.internal(path));
        } catch (Exception ex) { Log.error("Error while loading texture: " + path + "\n" + ex.getMessage()); }
        return texture != null ? texture : loadTexture("Unknown.png");
    }

    public static BitmapFont loadFont(String name) {
        return loadBitmapFont(Paths.GUI_PATH + "font/" + name + ".fnt");
    }

    public static BitmapFont loadBitmapFont(String path) {
        BitmapFont bitmapFont = null;
        try {
            bitmapFont = new BitmapFont(Gdx.files.internal(path), false);
        } catch (Exception ex) {
            Log.error("Error while loading BitmapFont: " + path + "\n" + ex.getMessage());
        }
        return bitmapFont;
    }

    public static ShaderProgram loadShader(String vertexName, String fragmentName) {
        ShaderProgram shaderProgram = new ShaderProgram(Gdx.files.internal("shader/" + vertexName + ".glsl").readString(), Gdx.files.internal("shader/" + fragmentName + ".glsl").readString());
        if (!shaderProgram.isCompiled()) Log.error("Error while compiling shader: " + "\n" + shaderProgram.getLog());
        if (shaderProgram.isCompiled()) Log.info("Error while loading music: " + vertexName + ", " + fragmentName);
        return shaderProgram;
    }

    public static FileHandle loadDir(String path) {
        return Gdx.files.internal(path);
    }

    public static FileHandle loadFile(String path, String name) {
        return Gdx.files.internal(path + name);
    }

}
