package model.data;


import java.io.IOException;
import java.io.InputStream;


public class MyObjectLevelLoader implements LevelLoader {
    @Override
    public Level loadLevel(InputStream input) {

        Level l = null;
        OBJUtil Serialize=new OBJUtil();
        try {
            l = (Level) Serialize.deserialize(input);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return l;
    }
}




