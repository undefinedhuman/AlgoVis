package de.undefinedhuman.algovis.file;

public interface Serializable {
    void load(FileReader reader);
    void save(FileWriter writer);
}
