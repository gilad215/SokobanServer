package model.data;


//import com.sun.deploy.util.ArrayUtil;


import java.io.InputStream;

public class MyTextLevelLoader implements LevelLoader {
    @Override
    public Level loadLevel(InputStream input) {
        TXTUtil util=new TXTUtil();
        return util.load(input);
    }
}
