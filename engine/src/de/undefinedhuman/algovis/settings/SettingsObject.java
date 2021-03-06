package de.undefinedhuman.algovis.settings;

import de.undefinedhuman.algovis.log.Log;

import java.util.HashMap;
import java.util.Set;

public class SettingsObject {

    private HashMap<String, Object> settings = new HashMap<>();

    public void addSetting(String key, Object value) {
        this.settings.put(key, value);
    }

    public boolean containsKey(String key) {
        return settings.containsKey(key);
    }

    public Object get(String key) {
        return settings.get(key);
    }

    public void log(SettingsObject settingsObject) {
        Log.info(settingsObject.getSettings().size());
        for(String key : settingsObject.getSettings().keySet()) {
            Log.info("=" + key + "=");
            if(settingsObject.getSettings().get(key) instanceof SettingsObject) log((SettingsObject) settingsObject.getSettings().get(key));
            else Log.info(settingsObject.getSettings().get(key).toString());
            Log.info("===");
        }
    }

    public Set<String> keySet() {
        return settings.keySet();
    }

    public HashMap<String, Object> getSettings() {
        return settings;
    }

    public void delete() {
        for(Object value : settings.values()) {
            if(!(value instanceof SettingsObject)) continue;
            ((SettingsObject) value).delete();
        }
        settings.clear();
    }

}
