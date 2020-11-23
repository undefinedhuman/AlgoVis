package de.undefinedhuman.algovis.settings.panels;

import de.undefinedhuman.algovis.file.FileWriter;
import de.undefinedhuman.algovis.file.FsFile;
import de.undefinedhuman.algovis.settings.Setting;
import de.undefinedhuman.algovis.settings.SettingsList;
import de.undefinedhuman.algovis.settings.SettingsObject;
import de.undefinedhuman.algovis.utils.Tools;

import java.util.ArrayList;

public class PanelObject {

    public String key;

    protected SettingsList settings = new SettingsList();

    public void save(FileWriter writer) {
        writer.writeString("{:" + getKey()).nextLine();
        Tools.saveSettings(writer, settings.getSettings());
        writer.writeString("}").nextLine();
    }

    public PanelObject load(FsFile parentDir, SettingsObject settingsObject) {
        for(Setting setting : this.settings.getSettings())
            setting.loadSetting(parentDir, settingsObject);
        return this;
    }

    public ArrayList<Setting> getSettings() {
        return settings.getSettings();
    }

    public PanelObject setKey(String key) {
        this.key = key;
        return this;
    }

    public String getKey() {
        return key;
    }

    public void delete() {
        settings.delete();
    }

}
