package de.undefinedhuman.algovis.settings.types.array;

import de.undefinedhuman.algovis.file.FileWriter;
import de.undefinedhuman.algovis.file.FsFile;
import de.undefinedhuman.algovis.file.LineSplitter;
import de.undefinedhuman.algovis.settings.SettingType;
import de.undefinedhuman.algovis.utils.Tools;

public class IntArraySetting extends ArraySetting {

    public IntArraySetting(String key, int[] value) {
        super(SettingType.IntArray, key, value);
    }

    @Override
    public void load(FsFile parentDir, Object value) {
        if(!(value instanceof LineSplitter)) return;
        LineSplitter splitter = (LineSplitter) value;
        setValue(splitter.getNextIntegerArray());
    }

    @Override
    public void save(FileWriter writer) {
        super.save(writer);
        writer.writeIntegerArray(getIntegerArray());
    }

    @Override
    public String generateString() {
        return Tools.convertArrayToString(getIntegerArray());
    }

    @Override
    public void keyEvent() {
        setValue(Tools.convertStringToIntArray(valueField.getText().split(";")));
    }

}
