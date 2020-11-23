package de.undefinedhuman.algovis.gui.texture;

import com.badlogic.gdx.math.Vector2;
import de.undefinedhuman.algovis.file.FileReader;
import de.undefinedhuman.algovis.file.FsFile;
import de.undefinedhuman.algovis.file.Paths;
import de.undefinedhuman.algovis.resources.texture.TextureManager;

public enum GuiTemplate {

    BIG_BUTTON("Big Button"),
    HOTBAR("Hotbar"),
    PANEL("Panel"),
    SCROLL_PANEL("Scroll Panel"),
    SMALL_PANEL("Small Panel"),
    SMALL_BUTTON("Small Button"),
    SLOT("Slot");

    public String templateName;
    public int cornerSize;
    public String[] textures = new String[9];

    GuiTemplate(String templateName) {
        this.templateName = templateName;
    }

    public void load() {
        FileReader reader = new FsFile(Paths.GUI_PATH, "template/" + templateName + "/settings.gui", false).getFileReader(false);
        reader.nextLine();
        this.cornerSize = reader.getNextInt();
        reader.close();
        for (int i = 0; i < 9; i++)
            textures[i] = Paths.GUI_PATH + "template/" + templateName + "/" + i + ".png";
        TextureManager.instance.addTexture(textures);
    }

    public Vector2 getOffset() {
        return new Vector2(cornerSize, cornerSize);
    }

    public void delete() {
        TextureManager.instance.removeTexture(textures);
    }

}
