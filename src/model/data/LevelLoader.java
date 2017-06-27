package model.data;


import java.io.InputStream;

/**
 * A. The separation was done by creating a Level class that represents the information,
 *    The class MyTextLevelLoader creates and generates the board and an object of Level
 *    by implementing the interface <<LevelLoader>>
 * B. The separation preserves the Open/Closed principle because we can implement the interface
 *    LevelLoader and get different functionalities.
 * C.
 * D. We are using InputStream in order to be able to load from different types of sources.
 */
public interface LevelLoader {
    public Level loadLevel(InputStream input);
}
