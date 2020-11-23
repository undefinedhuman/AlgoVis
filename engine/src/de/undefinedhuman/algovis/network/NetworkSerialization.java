package de.undefinedhuman.algovis.network;

import de.undefinedhuman.algovis.file.LineSplitter;
import de.undefinedhuman.algovis.file.LineWriter;

public interface NetworkSerialization {
    void send(LineWriter writer);
    void receive(LineSplitter splitter);
}
