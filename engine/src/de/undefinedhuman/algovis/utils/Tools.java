package de.undefinedhuman.algovis.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import de.undefinedhuman.algovis.file.FileReader;
import de.undefinedhuman.algovis.file.FileWriter;
import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.resources.texture.TextureManager;
import de.undefinedhuman.algovis.settings.Setting;
import de.undefinedhuman.algovis.settings.SettingsObject;
import de.undefinedhuman.algovis.utils.math.Vector4;

import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Tools {

    public static SettingsObject loadSettings(FileReader reader) {
        SettingsObject settingsObject = new SettingsObject();
        while(reader.nextLine() != null) {
            String key = reader.getNextString();
            if(key.startsWith("}")) return settingsObject;
            else if(key.startsWith("{")) settingsObject.addSetting(loadKey(key), loadSettings(reader));
            else settingsObject.addSetting(key, reader.getRemainingRawData());
        }
        return settingsObject;
    }

    private static String loadKey(String key) {
        String[] values = key.split(":");
        if(values.length < 2)
            Log.instance.crash("Can't find key in string: " + key);
        return values[1];
    }

    public static void saveSettings(FileWriter writer, List<Setting> settings) {
        for(Setting setting : settings) {
            setting.save(writer);
            writer.nextLine();
        }
    }

    public static float[] convertStringToFloatArray(String[] stringArray) {
        float[] floatArray = new float[stringArray.length];
        for(int i = 0; i < floatArray.length; i++) floatArray[i] = Float.parseFloat(stringArray[i]);
        return floatArray;
    }

    public static int[] convertStringToIntArray(String[] stringArray) {
        return Stream.of(stringArray).mapToInt(Integer::parseInt).toArray();
    }

    public static String convertArrayToString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String object : array)
            builder.append(object).append(Variables.SEPARATOR);
        return builder.toString();
    }

    public static String convertArrayToString(int[] array) {
        StringBuilder builder = new StringBuilder();
        for (Integer object : array)
            builder.append(object).append(Variables.SEPARATOR);
        return builder.toString();
    }

    public static String convertArrayToString(float[] array) {
        StringBuilder builder = new StringBuilder();
        for (float object : array)
            builder.append(object).append(Variables.SEPARATOR);
        return builder.toString();
    }

    public static String convertArrayToString(Vector2[] array) {
        StringBuilder builder = new StringBuilder();
        for (Vector2 current : array)
            builder.append(current.x).append(Variables.SEPARATOR).append(current.y).append(Variables.SEPARATOR);
        return builder.toString();
    }

    public static void addSettings(JPanel panel, ArrayList<Setting> settings) {
        int currentOffset = 0;
        for (Setting setting : settings) {
            setting.addMenuComponents(panel, new Vector2(0, currentOffset));
            currentOffset += setting.offset;
        }
        resetPanel(panel);
    }

    public static void removeSettings(JPanel panel) {
        panel.removeAll();
        resetPanel(panel);
    }

    public static void resetPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    public static void drawLine(SpriteBatch batch, Vector2 point1, Vector2 point2, int lineWidth, Color color) {
        Vector2 vec = new Vector2(point2).sub(point1);
        TextureRegion texture = TextureManager.instance.getTexture("blank.png");
        Sprite sprite = new Sprite(texture, 0, 0, lineWidth, (int) vec.len());
        sprite.setColor(color);
        sprite.setOrigin(0, 0);
        sprite.setPosition(point1.x, point1.y);
        sprite.setRotation(vec.angle() - 90);
        sprite.draw(batch);
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public static boolean isInRange(int val, int min, int max) {
        return val >= min && val <= max;
    }

    public static boolean isInRange(float val, float min, float max) {
        return val >= min && val <= max;
    }

    public static TextureRegion fixBleeding(TextureRegion region) {
        float fix = 0.1f, x = region.getRegionX(), y = region.getRegionY(), width = region.getRegionWidth(), height = region.getRegionHeight(), invTexWidth = 1f / region.getTexture().getWidth(), invTexHeight = 1f / region.getTexture().getHeight();
        region.setRegion((x + fix) * invTexWidth, (y + fix) * invTexHeight, (x + width - fix) * invTexWidth, (y + height - fix) * invTexHeight);
        return region;
    }

    public static int isEqual(int i, int j) {
        return i == j ? 1 : 0;
    }

    public static String appendSToString(int length) {
        return length > 1 ? "s" : "";
    }

    public static BufferedImage scaleNearest(BufferedImage before, double scale) {
        return scale(before, scale, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }

    private static BufferedImage scale(final BufferedImage before, double scale, int type) {
        int w = before.getWidth(), h = before.getHeight(), w2 = (int) (w * scale), h2 = (int) (h * scale);
        BufferedImage after = new BufferedImage(w2, h2, before.getType());
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, type);
        scaleOp.filter(before, after);
        return after;
    }

    public static int getWorldPositionX(float x, float width) {
        return (int) ((width + x) % width);
    }

    public static Vector4 calculateBounds(Vector2 position, Vector2 offset, Vector2 size) {
        return new Vector4(position.x + offset.x, position.y + offset.y, position.x + offset.x + size.x, position.y + offset.y + size.y);
    }

    public static boolean isDigit(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static Vector2 getTextureSize(TextureRegion texture) {
        return new Vector2(texture.getRegionWidth(), texture.getRegionHeight());
    }


}
