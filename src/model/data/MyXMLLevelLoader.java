package model.data;


import java.io.IOException;
import java.io.InputStream;

public class MyXMLLevelLoader implements LevelLoader {
    @Override
    public Level loadLevel(InputStream input) {
        Level l = null;
        XMLUtil XMLLoad=new XMLUtil();
        try {
            l =(Level)XMLLoad.decode(input);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return l;
    }
}
