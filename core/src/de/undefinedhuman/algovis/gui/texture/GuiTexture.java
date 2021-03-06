package de.undefinedhuman.algovis.gui.texture;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.undefinedhuman.algovis.Main;
import de.undefinedhuman.algovis.resources.texture.TextureManager;
import de.undefinedhuman.algovis.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class GuiTexture {

    private GuiTemplate template = null;
    private int cornerSize = 0;
    private ArrayList<String> textureNames = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();

    public GuiTexture() {
        addTexture("Unknown.png");
    }

    public GuiTexture(String texture) {
        addTexture(texture);
    }

    public GuiTexture(GuiTemplate template) {
        this.template = template;
        this.cornerSize = template.cornerSize;
        addTexture(template.textures);
    }

    private void addTexture(String... names) {
        TextureManager.instance.addTexture(names);
        textureNames.addAll(Arrays.asList(names));
        for (String name : names) sprites.add(new Sprite(TextureManager.instance.getTexture(name)));
    }

    public void resize(int x, int y, int width, int height) {
        if (template == null) sprites.get(0).setBounds(x, y, width, height);
        else {
            this.cornerSize = template.cornerSize * Main.guiScale;
            int centerWidth = Math.max(0, width - cornerSize * 2);
            int centerHeight = Math.max(0, height - cornerSize * 2);
            int index, e, r, o, a;

            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 2; j++) {
                    e = Utils.isEqual(i, j);
                    r = i ^ j;
                    o = i | j;
                    a = i & j;
                    index = (3 - (i + j)) * e + 2 * r * i;
                    sprites.get(index).setBounds(x + (centerWidth + cornerSize) * i, y + (centerHeight + cornerSize) * j, cornerSize, cornerSize);
                    sprites.get(4 + index).setBounds(
                            x + cornerSize * o + centerWidth * a,
                            y + cornerSize * (e | j) + centerHeight * (r & j),
                            cornerSize * e + centerWidth * r,
                            cornerSize * r + centerHeight * e);
                }

            sprites.get(8).setBounds(x + cornerSize, y + cornerSize, centerWidth, centerHeight);
        }
    }

    public void render(SpriteBatch batch, Color color, float alpha) {
        for (Sprite sprite : sprites) {
            sprite.setColor(color);
            sprite.draw(batch, alpha);
        }
    }

    public void setColor(Color color) {
        for (Sprite sprite : sprites) sprite.setColor(color);
    }

    public void setTexture(String texture) {
        if (template != null) template = null;
        for (String name : textureNames) TextureManager.instance.removeTexture(name);
        this.textureNames.clear();
        this.sprites.clear();
        addTexture(texture);
    }

    public int getCornerSize() { return cornerSize; }

    public int getBaseCornerSize() { return template == null ? 0 : template.cornerSize; }

    public Vector2 getOffset() { return new Vector2(cornerSize, cornerSize); }

    public void delete() {
        for (String s : textureNames) TextureManager.instance.removeTexture(s);
        textureNames.clear();
        sprites.clear();
    }

    public GuiTemplate getTemplate() {
        return template;
    }

}
