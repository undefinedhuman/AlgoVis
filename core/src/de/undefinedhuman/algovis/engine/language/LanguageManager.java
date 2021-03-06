package de.undefinedhuman.algovis.engine.language;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import de.undefinedhuman.algovis.file.Paths;
import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.utils.Manager;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.engine.config.SettingsManager;

import java.util.HashMap;

public class LanguageManager extends Manager {

    public static LanguageManager instance;

    private HashMap<String, String> languageList = new HashMap<>();
    private String languageName;

    public LanguageManager() {
        if (instance == null) instance = this;
    }

    @Override
    public void init() {
        languageName = SettingsManager.instance.language.getString();
        if (!loadLanguage(languageName)) {
            loadLanguage(Variables.DEFAULT_LANGUAGE);
            languageName = Variables.DEFAULT_LANGUAGE;
        }
    }

    public boolean loadLanguage(String languageName) {

        try {

            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal(Paths.LANGUAGE_PATH + "languages.xml").reader("UTF-8"));
            Array<XmlReader.Element> languages = root.getChildrenByName("language");
            for (int i = 0; i < languages.size; ++i) {
                XmlReader.Element language = languages.get(i);
                if (!language.hasAttribute("name") || !language.getAttribute("name").equals(languageName)) continue;
                languages.clear();
                Array<XmlReader.Element> strings = language.getChildrenByName("string");
                for (int j = 0; j < strings.size; ++j) {
                    XmlReader.Element string = strings.get(j);
                    languageList.put(string.getAttribute("key"), string.getAttribute("value").replace("&lt;br /&gt;&lt;br /&gt;", "\n"));
                }
                Log.info("Loaded language " + languageName + " successfully!");
                return true;
            }

        } catch (Exception ex) {
            Log.error("Error while loading language " + languageName);
            Log.error(ex.getMessage());
        }

        return false;

    }

    @Override
    public void delete() {
        languageList.clear();
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getString(String key, Replacement... replacements) {
        if (languageList == null) return languageName + " not found!";
        String s = languageList.get(key);
        if (s == null) return key + " not found!";
        for (Replacement replacement : replacements) s = s.replaceAll(replacement.getName(), replacement.getValue());
        return s;
    }

}
