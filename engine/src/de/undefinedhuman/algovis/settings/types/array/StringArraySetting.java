package de.undefinedhuman.algovis.settings.types.array;

import de.undefinedhuman.algovis.file.FileWriter;
import de.undefinedhuman.algovis.file.FsFile;
import de.undefinedhuman.algovis.file.LineSplitter;
import de.undefinedhuman.algovis.settings.SettingType;
import de.undefinedhuman.algovis.utils.Tools;

public class StringArraySetting extends ArraySetting {

    public StringArraySetting(String key, String[] value) {
        super(SettingType.StringArray, key, value);
    }

    @Override
    public void load(FsFile parentDir, Object value) {
        if(!(value instanceof LineSplitter)) return;
        LineSplitter splitter = (LineSplitter) value;
        setValue(splitter.getNextStringArray());
    }

    @Override
    public void save(FileWriter writer) {
        super.save(writer);
        writer.writeStringArray(getStringArray());
    }

    @Override
    public String generateString() {
        return Tools.convertArrayToString(getStringArray());
    }

    @Override
    public void keyEvent() {
        setValue(valueField.getText().split(";"));
    }

}
