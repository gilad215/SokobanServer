package soko;

import java.io.IOException;

public interface LevelSaver {
    void save(Object obj, String fileName) throws IOException;
}
